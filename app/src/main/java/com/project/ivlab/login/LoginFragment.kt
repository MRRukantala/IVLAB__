package com.project.ivlab.login

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.project.ivlab.R
import com.project.ivlab.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private val viewModel by viewModels<LoginViewModel>()
    lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_login, container, false
        )

        binding.apply {
            binding.lifecycleOwner = this.lifecycleOwner
            binding.viewModelLogin = viewModel

            daftar.setOnClickListener {
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegister1Fragment())
            }
        }

        binding.btnLogin.setOnClickListener {

            var username = binding.etUserName.text.toString().trim()
            var password = binding.etPassword.text.toString().trim()

            if (username.isEmpty()) {
                binding.etUserName.error = "Username Tidak Boleh Kosong"
                binding.etUserName.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                binding.etPassword.error = "Password dibutuhkan"
                binding.etPassword.requestFocus()
                return@setOnClickListener
            }

            viewModel.loginApi(username, password)
        }

        viewModel.dataUser.observe(viewLifecycleOwner, Observer { it ->
            if (null != it) {
                Toast.makeText(this.requireContext(), "Selamat Datang ${viewModel.dataUser.value?.get(0)?.nama_bumdes}", Toast.LENGTH_LONG).show()
                saveData()
                findNavController()
                    .navigate(LoginFragmentDirections.actionLoginFragmentToBerandaActivity())
            }
            requireActivity().finish()
        })

        return binding.root
    }

    private fun saveData() {
        return requireActivity().getSharedPreferences("my_data_pref", Context.MODE_PRIVATE).edit()
            .putInt("id", viewModel.dataUser.value!![0].id)
            .putString("nama_bumdes", viewModel.dataUser.value!![0].nama_bumdes)
            .putString("desa", viewModel.dataUser.value!![0].desa)
            .putString("email", viewModel.dataUser.value!![0].email)
            .putString("password", viewModel.dataUser.value!![0].password)
            .apply()
    }


}