package com.project.ivlab.home

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.project.ivlab.R
import com.project.ivlab.course.CourseAdapter
import com.project.ivlab.course.CourseFragmentDirections
import com.project.ivlab.databinding.FragmentHomeBinding
import com.project.ivlab.home.kategori.KategoriAdapter
import android.app.Activity
import android.view.inputmethod.InputMethodManager
import androidx.activity.OnBackPressedCallback


class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this)[HomeViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true))
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_home, container, false
        )

        binding.apply {
            binding.textView.text = getString(R.string.innovation_n_through_the_limit)
            binding.textView2.text = getString(R.string.temukan_referensi_dan_insight_disini)
        }

//        binding.imageView3.setOnClickListener {
//            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToResultSearchCourseFragment())
//        }

        binding.search.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if ((event.action == KeyEvent.ACTION_DOWN) &&
                (keyCode == KeyEvent.KEYCODE_ENTER)
            ) {
                // Perform action on key press
                hideKeyboard(requireActivity())
                val action = HomeFragmentDirections.actionHomeFragmentToResultSearchCourseFragment()
                action.setKey(binding.search.text.toString())
                findNavController().navigate(action)
                return@OnKeyListener true;
            }
            return@OnKeyListener false;
        })
        binding.lifecycleOwner = this
        binding.home = viewModel

        binding.recyclerViewCourseHome.adapter = CourseAdapter(
            CourseAdapter.OnClickListener {
                viewModel.displayCourseDetails(it)
            }
        )

        binding.recyclerViewKategoriHome.adapter = KategoriAdapter()

        viewModel.navigatedToSelectedProperty.observe(viewLifecycleOwner, Observer {
            if (null != it) {
                this.findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToCourse()
                )
                this.findNavController().navigate(
                    CourseFragmentDirections.actionCourseFragmentToDetailCourseFragment(it)
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

    fun hideKeyboard(activity: Activity) {
        val imm: InputMethodManager =
            activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }


}

