package com.project.ivlab.course

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.project.ivlab.R
import com.project.ivlab.course.enroll.EnrollViewModel
import com.project.ivlab.course.materi.MateriAdapter
import com.project.ivlab.course.materi.MateriViewModel
import com.project.ivlab.databinding.FragmentDetailCourseBinding
import com.project.ivlab.login.Data

class DetailCourseFragment : Fragment() {

    lateinit var binding: FragmentDetailCourseBinding
    private val viewModel: MateriViewModel by lazy {
        ViewModelProvider(this).get(MateriViewModel::class.java)
    }

    private val viewModelEnroll: EnrollViewModel by lazy {
        ViewModelProvider(this).get(EnrollViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val application = requireNotNull(activity).application
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_detail_course, container, false
        )

        binding.lifecycleOwner = this
        binding.materiViewModel = viewModel

        val courseProperty = DetailCourseFragmentArgs.fromBundle(requireArguments()).selectedCourse
        val viewModelFactory = DetailCourseViewModelFactory(courseProperty, application)

        binding.detailCourseViewModel =
            ViewModelProvider(this, viewModelFactory).get(DetailCourseViewModel::class.java)

        viewModel.valueId(courseProperty.id.toInt())

        binding.recyclerViewMateri.adapter =
            MateriAdapter(MateriAdapter.OnClickListener {
            })

        binding.buttomEnroll.setOnClickListener {
            viewModelEnroll.enrollCourse(getData().id, courseProperty.id.toInt())
        }

        viewModelEnroll.unroll.observe(viewLifecycleOwner, {
            Toast.makeText(this.requireContext(), it.message, Toast.LENGTH_SHORT).show()
        })


        return binding.root
    }

    fun getData(): Data {
        val sharePre = requireActivity()
            .getSharedPreferences("my_data_pref", Context.MODE_PRIVATE)
        return Data(
            sharePre.getInt("id", -1),
            sharePre.getString("nama_bumdes", "Nama Bumdes").toString(),
            sharePre.getString("desa", "Nama Desa").toString(),
            sharePre.getString("email", "email").toString(),
            sharePre.getString("password", "password").toString()
        )
    }
}
