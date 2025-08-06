package com.example.androidtask.ui.auth.register

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.androidtask.R
import com.example.androidtask.data.auth.RegisterRepository
import com.example.androidtask.databinding.ActivityRegisterBinding
import com.example.androidtask.domain.auth.RegisterUseCase
import com.example.androidtask.ui.auth.otp.OtpActivity

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit  var viewModel : RegisterViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

         val registerUseCase= RegisterUseCase(RegisterRepository())
        val factory= RegisterViewModelFactory(registerUseCase)
        viewModel= ViewModelProvider(this, factory)[RegisterViewModel::class.java]

         binding.signUpButton.setOnClickListener {
             viewModel.validateForm(
                 binding.etFullName.text.toString(),
                 binding.etEmail.text.toString(),
                 binding.etPassword.text.toString(),
                 binding.etPhonenumber.text.toString(),
                 binding.checkbox.isChecked
             )
         }

        viewModel.validationResult.observe(this, Observer { result->
            Toast.makeText(this, result, Toast.LENGTH_SHORT).show()

        })


        binding.signUpButton.setOnClickListener {
            viewModel.registerUser(
                username = binding.etFullName.text.toString(),
                email = binding.etEmail.text.toString(),
                password = binding.etPassword.text.toString()
            )

            lifecycleScope.launchWhenStarted {
                viewModel.registerResult.collect{
                        result->
                    result?.let {
                        Toast.makeText(this@RegisterActivity, it, Toast.LENGTH_SHORT).show()
                    }
                }
            }

            lifecycleScope.launchWhenStarted {
                viewModel.senotpResult.collect{
                    isSent->

                    if(isSent){
                        val intent= Intent(this@RegisterActivity, OtpActivity::class.java)
                        intent.putExtra("email", binding.etEmail.text.toString())
                        startActivity(intent)
                    }
                }
            }

        }
    }
}