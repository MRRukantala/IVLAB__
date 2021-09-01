package com.project.ivlab.profil.sharing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.project.ivlab.R
import com.project.ivlab.databinding.FragmentMySharingBinding
import com.project.ivlab.profil.sharing.mentoring.MentoringFragment
import com.project.ivlab.profil.sharing.share.ShareFragment

class MySharingFragment : Fragment() {

    lateinit var binding: FragmentMySharingBinding
    val name = arrayOf(
        "Sharing",
        "Mentoring"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_my_sharing, container, false
        )

        val fragmentList = arrayListOf(
            ShareFragment(),
            MentoringFragment()
        )

        val tabLayout = binding.tabLayoutSharing
        val viewPager = binding.viewPagerMySharing

        val adapter = FragmentMySharingAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        binding.viewPagerMySharing.adapter = adapter

        TabLayoutMediator(
            tabLayout,
            viewPager
        ) { tab, position ->
            tab.text = name[position].toString()
        }.attach()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()
    }

}