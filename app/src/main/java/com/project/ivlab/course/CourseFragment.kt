package com.project.ivlab.course

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.project.ivlab.R
import com.project.ivlab.databinding.FragmentCourseBinding

class CourseFragment : Fragment(), TextWatcher {

    lateinit var binding: FragmentCourseBinding
    private val viewModel: CourseViewModel by lazy {
        ViewModelProvider(this).get(CourseViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_course, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.etSearch.addTextChangedListener(this)

            binding.recyclerViewCourse.adapter = CourseAdapter(
                CourseAdapter.OnClickListener {
                    viewModel.displayCourseDetails(it)
                }
            )


            viewModel.navigatedToSelectedProperty.observe(viewLifecycleOwner, Observer {
                if (null != it) {
                    this.findNavController().navigate(
                        CourseFragmentDirections
                            .actionCourseFragmentToDetailCourseFragment(it)
                    )
                    viewModel.displayCourseDetailsCompelete()
                }
            })


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()
    }


    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        Log.e("kdnksd", s.toString())
        if (s.toString().isNotEmpty())
            viewModel.searchCourse(s.toString().trim())
    }

    override fun afterTextChanged(s: Editable?) {
    }


}

