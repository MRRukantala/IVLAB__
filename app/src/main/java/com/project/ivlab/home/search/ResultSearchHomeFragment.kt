package com.project.ivlab.home.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayoutMediator
import com.project.ivlab.R
import com.project.ivlab.databinding.FragmentResultSearchHomeBinding
import com.project.ivlab.home.search.couse.ResultSearchCourseFragment
import com.project.ivlab.home.search.product.ResultSearchProdcutFragment

class ResultSearchHomeFragment : Fragment() {

    lateinit var binding: FragmentResultSearchHomeBinding
    val args: ResultSearchHomeFragmentArgs by navArgs()
    val name = arrayOf(
        "Course",
        "Product"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_result_search_home, container,
            false
        )

        val amount = args.key
        Log.d(TAG, "onCreateView: $amount")
        val fragmentList = arrayListOf(
            ResultSearchCourseFragment(),
            ResultSearchProdcutFragment()
        )


        val tabLayout = binding.tabLayoutResultSearch
        val viewPager = binding.viewPagerResultSearch


        val adapter = FragmentSearchAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle,
            args.key
        )

        binding.viewPagerResultSearch.adapter = adapter

        TabLayoutMediator(
            tabLayout,
            viewPager
        ) { tab, position ->
            tab.text = name[position]
        }.attach()



        return binding.root
    }

    companion object {
        private const val TAG = "ResultSearchHomeFragmen"
    }
}