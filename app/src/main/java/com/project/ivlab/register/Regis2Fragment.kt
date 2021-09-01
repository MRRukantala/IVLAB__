package com.project.ivlab.register

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.project.ivlab.R
import com.project.ivlab.databinding.FragmentRegis2Binding
import kotlinx.android.synthetic.main.fragment_regis2.*


class Regis2Fragment : Fragment() {

    companion object{
        lateinit var binding: FragmentRegis2Binding
    }

    private val viewModel: RegisterViewModel by lazy {
        ViewModelProvider(this)[RegisterViewModel::class.java]
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_regis2, container, false
        )

        binding.viewModel = viewModel


        var args = Regis2FragmentArgs.fromBundle(requireArguments())
        var email = args.email
        var password = args.password
        var bumdes = args.namaBumdes

        binding.btnBuatAkun.setOnClickListener {
            var provinsi = binding.provinsi.text.toString().trim()
            var kabupaten = binding.kabupaten.text.toString().trim()
            var kecamatan = binding.kecamatan.text.toString().trim()
            var desa = binding.desa.text.toString().trim()

            if (provinsi.isEmpty()) {
                binding.provinsi.error = "Form ini Tidak Boleh Kosong"
                binding.provinsi.requestFocus()
                return@setOnClickListener
            }

            if (kabupaten.isEmpty()) {
                binding.kabupaten.error = "Form ini Tidak Boleh Kosong"
                binding.kabupaten.requestFocus()
                return@setOnClickListener
            }

            if (kecamatan.isEmpty()) {
                binding.kecamatan.error = "Form ini Tidak Boleh Kosong"
                binding.kecamatan.requestFocus()
                return@setOnClickListener
            }

            if (desa.isEmpty()) {
                binding.desa.error = "Form ini Tidak Boleh Kosong"
                binding.desa.requestFocus()
                return@setOnClickListener
            }

            viewModel.registrasi(
                bumdes, desa, email, password,
                viewModel.provinsiId.value!!.toInt(),
                viewModel.kabupatenId.value!!.toInt(),
                viewModel.kecamatanId.value!!.toInt()
            )
        }

        viewModel.response.observe(viewLifecycleOwner, {
            Log.v("STATUS", it.status)
            if (it.status.equals("Success")) {
                Toast.makeText(
                    this.requireContext(),
                    it.status + " " + "Silahkan Login",
                    Toast.LENGTH_SHORT
                ).show()
                findNavController().navigate(Regis2FragmentDirections.actionRegis2FragmentToLoginFragment())
                viewModel.successApi()
            } else {
                Toast.makeText(
                    this.requireContext(),
                    it.status + " " + "Ada yang Salah",
                    Toast.LENGTH_SHORT
                ).show()
                viewModel.successApi()
            }
        })

        return binding.root
    }

}