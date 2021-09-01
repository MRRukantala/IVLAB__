package com.project.ivlab.home.search.couse

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.project.ivlab.R
import com.project.ivlab.course.CourseAdapter
import com.project.ivlab.course.CourseFragmentDirections
import com.project.ivlab.course.CourseViewModel
import com.project.ivlab.course.DetailCourseFragment
import com.project.ivlab.databinding.FragmentResultSearchCourseBinding
import com.project.ivlab.home.search.ResultSearchHomeFragmentDirections

class ResultSearchCourseFragment : Fragment() {

    lateinit var binding: FragmentResultSearchCourseBinding
    private val viewModel: CourseViewModel by lazy {
        ViewModelProvider(this).get(CourseViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_result_search_course, container, false
        )
        binding.lifecycleOwner = this
        binding.viewModel = viewModel


        binding.lvResultSearchCourse.adapter = CourseAdapter(
            CourseAdapter.OnClickListener {
                viewModel.displayCourseDetails(it)
            }
        )


        viewModel.navigatedToSelectedProperty.observe(viewLifecycleOwner, {
            if (null != it) {
                this.findNavController().navigate(
                    ResultSearchHomeFragmentDirections.actionResultSearchCourseFragmentToDetailCourseFragment2(it)
                )
//                requireActivity().findNavController(R.id.beranda_nav_host_fragment).navigate(
//                    CourseFragmentDirections
//                        .actionCourseFragmentToDetailCourseFragment(it)
//                )
//                Log.d("TAG", "onCreateView: ")
//                val bundle = Bundle()
//                bundle.putParcelable("selectedCourse", it)
//                val details = DetailCourseFragment()
//                details.arguments = bundle
//                fragmentManager?.beginTransaction()
//                    ?.replace(R.id.beranda_nav_host_fragment, details)
//                    ?.commit()
                viewModel.displayCourseDetailsCompelete()
            }
        })
        viewModel.searchCourse(requireArguments().get("key").toString())

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()
    }

}