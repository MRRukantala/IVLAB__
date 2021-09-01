package com.project.ivlab.onboard.screen

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.project.ivlab.R
import com.project.ivlab.databinding.FragmentSecondOnBoardingBinding

class SecondOnBoardingFragment : Fragment() {

    lateinit var binding: FragmentSecondOnBoardingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_second_on_boarding,
                container,
                false
            )

        binding.finishButton.setOnClickListener {
            findNavController().navigate(R.id.action_onBoardFragment_to_loginFragment)
            onBoardFinished()
        }

        return binding.root
    }

    private fun onBoardFinished() {
        requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE).edit()
            .putBoolean("Finished", true)
            .apply()
    }

}