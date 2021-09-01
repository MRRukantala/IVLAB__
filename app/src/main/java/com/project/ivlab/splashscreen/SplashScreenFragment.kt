package com.project.ivlab.splashscreen

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.project.ivlab.R
import com.project.ivlab.databinding.FragmentSplashScreenBinding
import com.project.ivlab.isNetworkAvailable
import com.project.ivlab.login.Data
import com.project.ivlab.login.LoginViewModel

class SplashScreenFragment : Fragment() {

    lateinit var binding: FragmentSplashScreenBinding
    private val viewModel by viewModels<LoginViewModel>()

    companion object {
        const val TAG = "SplashFragment"
        const val SIGN_IN_RESULT_CODE = 1001
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_splash_screen, container, false)

        Handler(Looper.getMainLooper()).postDelayed(
            Runnable { checking() }, 2000
        )
        return binding.root
    }

    private fun checking() {
        if (onBoardFinished()) {
            if (loggedIn().id != -1) {
                if (isNetworkAvailable(requireContext()).equals(true)) {
                    findNavController().navigate(SplashScreenFragmentDirections.actionSplashScreenFragmentToBerandaActivity())
                    requireActivity().finish()
                } else {
                    Toast.makeText(requireContext(), "Periksa Koneksi Anda", Toast.LENGTH_SHORT)
                        .show()
                }
            }
            if (isNetworkAvailable(requireContext()).equals(true)) {
                findNavController().navigate(SplashScreenFragmentDirections.actionSplashScreenFragmentToLoginFragment())
            } else {
                Toast.makeText(requireContext(), "Periksa Koneksi Anda", Toast.LENGTH_SHORT).show()
            }
        } else {
            findNavController().navigate(SplashScreenFragmentDirections.actionSplashScreenFragmentToOnBoardFragment())
        }
    }

    private fun onBoardFinished(): Boolean {
        return requireActivity()
            .getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
            .getBoolean("Finished", false)
    }

    fun loggedIn(): Data {
        val sharePre = requireActivity()
            .getSharedPreferences("my_data_pref", Context.MODE_PRIVATE)
        return Data(
            sharePre.getInt("id", -1),
            sharePre.getString("nama_bumdes", "Nama Bumdes").toString(),
            sharePre.getString("desa", "Nama Desa").toString(),
            sharePre.getString("email", "email").toString(),
            sharePre.getString("password", "password").toString()
        )
    }

}