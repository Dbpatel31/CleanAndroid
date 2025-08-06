package com.example.androidtask.ui.language

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.setPadding
import com.example.androidtask.R
import com.example.androidtask.databinding.ActivityLanguageScreenBinding
import com.example.androidtask.ui.auth.login.LoginActivity
import com.example.androidtask.ui.auth.register.RegisterActivity

class LanguageScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLanguageScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLanguageScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSelect.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
        val languages = listOf(
            "English" to "Hi",
            "Hindi" to "नमस्ते",
            "Bengali" to "হ্যালো",
            "Kannada" to "ನಮಸ್ಕಾರ",
            "Punjabi" to "ਸਤ ਸ੍ਰੀ ਅਕਾਲ",
            "Tamil" to "வணக்கம்",
            "Telugu" to "హలో",
            "French" to "Bonjour",
            "Spanish" to "Hola"
        )

        val radioGroup= RadioGroup(this).apply{
            orientation= RadioGroup.VERTICAL
        }

        languages.forEachIndexed { index, pair ->
            val view = layoutInflater.inflate(R.layout.custom_radio_button, radioGroup, false)
            val radioButton = view.findViewById<RadioButton>(R.id.radioButton)
            val textMain = view.findViewById<TextView>(R.id.textMain)
            val textSub = view.findViewById<TextView>(R.id.textSub)

            textMain.text= pair.first
            textSub.text= pair.second
            radioButton.id= View.generateViewId()

            view.setOnClickListener {
                radioGroup.check(radioButton.id)
            }

          radioGroup.addView(view)

        }
        binding.languageContainer.addView(radioGroup)
    }
}