package com.project.ivlab.profil.myCourse.detailmycourse

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.project.ivlab.R
import com.project.ivlab.course.materi.MateriAdapter
import com.project.ivlab.course.materi.MateriViewModel
import com.project.ivlab.databinding.FragmentDetailMyCourseBinding


class DetailMyCourse : Fragment() {

    lateinit var binding: FragmentDetailMyCourseBinding
    private val viewModel: MateriViewModel by lazy {
        ViewModelProvider(this).get(MateriViewModel::class.java)
    }

    lateinit var youtubeWebView: WebView

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = requireNotNull(activity).application
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_detail_my_course, container, false
        )
        binding.lifecycleOwner = this
        binding.materiViewModel = viewModel




        youtubeWebView = binding.imageView8


        youtubeWebView.setWebViewClient(object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                return false
            }
        })

        val webSettings = youtubeWebView.settings
        webSettings.javaScriptEnabled = true
        webSettings.loadWithOverviewMode = true
        webSettings.useWideViewPort = true


        val courseProperty = DetailMyCourseArgs.fromBundle(requireArguments()).selectedMyCourse
        val viewModelFactory = DetailMyCourseViewModelFactory(courseProperty, application)

        binding.detailCourseViewModel =
            ViewModelProvider(this, viewModelFactory).get(DetailMyCourseViewModel::class.java)

        viewModel.valueId(courseProperty.id.toInt())

        binding.recyclerViewMateri.adapter =
            MateriAdapter(MateriAdapter.OnClickListener {
                viewModel.displayVideoInView(it)
            })

        viewModel.selectedMateriVideo.observe(viewLifecycleOwner, {
            if (null != it) {
                val link = it.link_video.subSequence(68, 79)
                youtubeWebView.visibility = View.VISIBLE
                youtubeWebView.loadUrl("https://www.youtube.com/embed/$link")
                binding.textView16.text = it.judul_pelatihan
                Toast.makeText(requireContext(), it.judul_pelatihan, Toast.LENGTH_SHORT).show()
            } else {

            }
        })
////            viewModel.displayVideoInVIewCompelete()
//        })


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()
    }
}