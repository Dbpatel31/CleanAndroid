package com.example.androidtask.ui.auth.login

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.androidtask.R
import com.example.androidtask.data.auth.LoginRepository
import com.example.androidtask.data.auth.LoginRequest
import com.example.androidtask.databinding.ActivityLoginBinding
import com.example.androidtask.domain.auth.LoginUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
        val loginUseCase= LoginUseCase(LoginRepository())
        val factory= LoginViewModelFactory(loginUseCase)
        loginViewModel= ViewModelProvider(this,factory)[LoginViewModel::class.java]

        binding.singinButton.setOnClickListener {

           loginViewModel.validateForm(
               binding.etLoginEmail.text.toString(),
               binding.etLoginPassword.text.toString()
           )


            loginViewModel.login(binding.etLoginEmail.text.toString(), binding.etLoginPassword.text.toString())

           lifecycleScope.launchWhenStarted {
               loginViewModel.loginResult.collect{
                       result->
                   result?.let {
                       Toast.makeText(this@LoginActivity, it, Toast.LENGTH_SHORT).show()
                   }

           }
        }

        }

        loginViewModel.validationResult.observe(this, Observer{ result->
            Toast.makeText(this, result, Toast.LENGTH_SHORT).show()

        })
    }
}