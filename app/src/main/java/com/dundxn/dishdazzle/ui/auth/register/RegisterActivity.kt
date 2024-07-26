package com.dundxn.dishdazzle.ui.auth.register

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dundxn.dishdazzle.R
import com.dundxn.dishdazzle.data.room.entity.User
import com.dundxn.dishdazzle.databinding.ActivityRegisterBinding
import com.dundxn.dishdazzle.ui.ViewModelFactory
import com.dundxn.dishdazzle.ui.auth.AuthViewModel
import com.dundxn.dishdazzle.ui.auth.login.LoginActivity
import com.dundxn.dishdazzle.utils.showToast

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    private val viewModel by viewModels<AuthViewModel> {
        ViewModelFactory.getInstance(this.application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.btnRegister.setOnClickListener {
            register()
        }
        binding.tvToLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    private fun register() {
        val email = binding.etRegisterEmail.text.toString()
        val name = binding.etRegisterName.text.toString()
        val password = binding.etRegisterPassword.text.toString()
        val confirmPassword = binding.etRegisterConfirmPassword.text.toString()

        if (email.isNotBlank() && name.isNotBlank() && password.isNotBlank() && confirmPassword.isNotBlank()) {
            if (password == confirmPassword) {
                val user: User = User().apply {
                    this.userEmail = email
                    this.name = name
                    this.password = name
                }


                viewModel.addUser(user, this)

                viewModel.isUserAdded.observe(this) {
                    if (it) {
                        startActivity(Intent(this, LoginActivity::class.java))
                    }
                }

            } else showToast(this, "Password do not match")

        } else showToast(this, "Please fill in all fields")
    }
}