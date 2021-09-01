package com.project.ivlab.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.project.ivlab.R
import com.project.ivlab.course.CourseAdapter
import com.project.ivlab.databinding.FragmentDetailProductBinding
import com.project.ivlab.product.materiterkait.MTViewModel

class DetailProductFragment : Fragment() {

    lateinit var binding: FragmentDetailProductBinding
    private val viewModel: MTViewModel by lazy {
        ViewModelProvider(this).get(MTViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = requireNotNull(activity).application
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_detail_product, container, false
        )

        binding.lifecycleOwner = this
        binding.courseTerkaitViewModel = viewModel

        val productPropery =
            DetailProductFragmentArgs.fromBundle(requireArguments()).selectedProduct
        val viewModelFactory = DetailProductViewModelFactory(productPropery, application)

        binding.detailProductViewModel =
            ViewModelProvider(this, viewModelFactory).get(DetailProductViewModel::class.java)

        viewModel.valueId(productPropery.id.toInt())


        binding.materiTerkaitRecyclerView.adapter = CourseAdapter(CourseAdapter.OnClickListener{
            //
        })

        return binding.root
    }

}