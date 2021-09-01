package com.project.ivlab.register

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.project.ivlab.R
import com.project.ivlab.databinding.FragmentRegister1Binding

class Register1Fragment : Fragment() {

    lateinit var binding: FragmentRegister1Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_register1, container, false
        )


        binding.btnLogin.setOnClickListener {

            var username = binding.etUserName.text.toString().trim()
            var password = binding.etPassword.text.toString().trim()
            var valPassword = binding.etPasswordVer.text.toString().trim()
            var bumdes = binding.etBumdes.text.toString().trim()

            if (username.isEmpty()) {
                binding.etUserName.error = "Email Tidak Boleh Kosong"
                binding.etUserName.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                binding.etPassword.error = "Password dibutuhkan"
                binding.etPassword.requestFocus()
                return@setOnClickListener
            }

            if (valPassword.isEmpty()) {
                binding.etPasswordVer.error = "Password Verifikasi"
                binding.etPasswordVer.requestFocus()
                return@setOnClickListener
            }

            if (bumdes.isEmpty()) {
                binding.etBumdes.error = "Nama BUMDes dibutuhkan"
                binding.etBumdes.requestFocus()
                return@setOnClickListener
            }

            if(valPassword != password){
                binding.etPasswordVer.error = "Password Tidak Sesuai"
                binding.etPasswordVer.requestFocus()
                return@setOnClickListener
            }

            findNavController().navigate(
                Register1FragmentDirections.actionRegister1FragmentToRegis2Fragment(
                    username,
                    password,
                    bumdes
                )
            )
        }

        return binding.root
    }

}