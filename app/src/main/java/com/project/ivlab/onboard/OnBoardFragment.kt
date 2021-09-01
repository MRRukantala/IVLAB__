package com.project.ivlab.onboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.project.ivlab.R
import com.project.ivlab.databinding.FragmentOnBoardBinding
import com.project.ivlab.onboard.screen.FirstOnBoardFragment
import com.project.ivlab.onboard.screen.SecondOnBoardingFragment

class OnBoardFragment : Fragment() {

    lateinit var binding: FragmentOnBoardBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_on_board, container, false
        )

        val fragmentList = arrayListOf(
            FirstOnBoardFragment(),
            SecondOnBoardingFragment()
        )

        val adapter = OnBoardingAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        binding.viewPager.adapter = adapter

        return binding.root
    }

}