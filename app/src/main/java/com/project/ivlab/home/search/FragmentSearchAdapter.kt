package com.project.ivlab.home.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class FragmentSearchAdapter(
    list: ArrayList<Fragment>,
    fm: FragmentManager,
    lifecycle: Lifecycle,
    key: String
) : FragmentStateAdapter(fm, lifecycle) {
    private val fragmentList = list
    private val keyData = key
    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        val bundle = Bundle()
        bundle.putString("key", keyData)
        fragmentList[position].arguments = bundle
        return fragmentList[position]
    }

}