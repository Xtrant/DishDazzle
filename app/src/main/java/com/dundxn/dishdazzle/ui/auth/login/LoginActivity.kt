package com.dundxn.dishdazzle.ui.auth.login


import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dundxn.dishdazzle.R
import com.dundxn.dishdazzle.databinding.ActivityLoginBinding
import com.dundxn.dishdazzle.ui.ViewModelFactory
import com.dundxn.dishdazzle.ui.auth.AuthViewModel
import com.dundxn.dishdazzle.ui.auth.register.RegisterActivity
import com.dundxn.dishdazzle.ui.main.MainActivity

import com.dundxn.dishdazzle.utils.showToast

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    private val viewModel by viewModels<AuthViewModel> {
        ViewModelFactory.getInstance(this.application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.btnLogin.setOnClickListener {
            login()
        }

        binding.tvToRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))

        }
    }

    private fun login() {
        val email = binding.etLoginEmail.text.toString().trim()
        val password = binding.etLoginPassword.text.toString().trim()

        if (email.isNotEmpty() && password.isNotEmpty()) {
            viewModel.getUser(email, password).observe(this) {
                if (it != null) {
                    viewModel.saveSession(it.userEmail, it.name, true)
                    startActivity(Intent(this, MainActivity::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    })
                    @Suppress("DEPRECATION")
                    overridePendingTransition(0, 0)
                } else showToast(this, "Wrong Username or Password")
            }
        } else showToast(this, "Please fill in all fields")
    }
}