package com.project.ivlab.onboard.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.project.ivlab.R
import com.project.ivlab.databinding.FragmentFirstOnBoardBinding

class FirstOnBoardFragment : Fragment() {

    lateinit var binding: FragmentFirstOnBoardBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_first_on_board, container, false)

        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)

        binding.nextButton.setOnClickListener {
            viewPager?.currentItem = 1
        }


        return binding.root
    }

}