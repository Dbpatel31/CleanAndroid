package com.example.androidtask.ui.auth.otp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenStarted
import com.example.androidtask.R
import com.example.androidtask.data.auth.OtpRepository
import com.example.androidtask.databinding.ActivityOtpBinding
import com.example.androidtask.domain.auth.OtpUseCase
import com.example.androidtask.ui.auth.login.LoginActivity
import kotlinx.coroutines.flow.collect

class OtpActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOtpBinding
    private lateinit var otpViewModel: OtpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityOtpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        moveToNextOnInput()
         val email = intent.getStringExtra("email")
        val otpUseCase= OtpUseCase(OtpRepository())
        val factory= OtpViewModelFactory(otpUseCase)
        otpViewModel= ViewModelProvider(this, factory)[OtpViewModel::class.java]

        binding.verifyButton.setOnClickListener {
            val email= email.toString()
            val otpFields = listOf(
                binding.otp1.text.toString(),
                binding.otp2.text.toString(),
                binding.otp3.text.toString(),
                binding.otp4.text.toString(),
                binding.otp5.text.toString(),
                binding.otp6.text.toString()
            )
            if (otpFields.any { it.isBlank() }) {
                Toast.makeText(this, "Please enter all 6 digits of the OTP", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
           val otp= otpFields.joinToString("")
            otpViewModel.verifyOtp(email, otp)

        }

        lifecycleScope.launchWhenStarted {
            otpViewModel.verifyOtp.collect{
                result->
               result?.let {
                   Toast.makeText(this@OtpActivity, it, Toast.LENGTH_SHORT).show()
               }
            }
            otpViewModel.isCheckVerify.collect{
                isSent->
                if (isSent){
                    val intent= Intent(this@OtpActivity, LoginActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }

    fun moveToNextOnInput(){
        val otpFields = listOf(
            binding.otp1, binding.otp2, binding.otp3,
            binding.otp4, binding.otp5, binding.otp6
        )

        for(i in 0 until otpFields.size-1){
            otpFields[i].addTextChangedListener{
                if (it?.length==1) otpFields[i+1].requestFocus()
            }
        }
        for(i in 1 until otpFields.size){
            otpFields[i].setOnKeyListener { _, keyCode, event ->
                if (keyCode == android.view.KeyEvent.KEYCODE_DEL && otpFields[i].text.isEmpty()){
                    otpFields[i-1].requestFocus()
                }
                false
            }
        }
    }
}