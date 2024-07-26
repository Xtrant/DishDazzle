package com.dundxn.dishdazzle.ui.setting

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dundxn.dishdazzle.R
import com.dundxn.dishdazzle.databinding.ActivitySettingBinding
import com.dundxn.dishdazzle.ui.ViewModelFactory
import com.dundxn.dishdazzle.ui.auth.AuthViewModel
import com.dundxn.dishdazzle.ui.auth.login.LoginActivity

class SettingActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingBinding
    private val viewModel by viewModels<AuthViewModel> {
        ViewModelFactory.getInstance(this.application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnLogout.setOnClickListener {
            viewModel.deleteSession()
            startActivity(Intent(this, LoginActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            })
        }

        binding.tvSetting5.setOnClickListener {
            showPopup()
        }
        binding.imageView5.setOnClickListener {
            showPopup()
        }

        supportActionBar?.hide()
    }

    private fun showPopup() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Delete Account")
        builder.setMessage("Are you sure want to delete account")

        // Tombol positif (OK)
        builder.setPositiveButton("OK") { dialogInterface: DialogInterface, i: Int ->
            viewModel.getEmail().observe(this) {
                if (it.isNotEmpty()) {
                    viewModel.deleteUser(it)
                    viewModel.deleteSession()
                    startActivity(Intent(this, LoginActivity::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    })
                    dialogInterface.dismiss() // Tutup pop up
                }
            }
        }

        // Tombol negatif (Batal)
        builder.setNegativeButton("Batal") { dialogInterface: DialogInterface, i: Int ->
            // Aksi yang ingin dilakukan saat tombol Batal diklik
            // Misalnya, menutup pop up tanpa melakukan apapun
            dialogInterface.dismiss() // Tutup pop up
        }

        // Buat dan tampilkan pop up
        val dialog = builder.create()
        dialog.show()
    }
}