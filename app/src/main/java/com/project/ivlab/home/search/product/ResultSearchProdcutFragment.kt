package com.project.ivlab.home.search.product

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
import androidx.navigation.fragment.findNavController
import com.project.ivlab.R
import com.project.ivlab.databinding.FragmentResultSearchProdcutBinding
import com.project.ivlab.home.search.ResultSearchHomeFragmentDirections
import com.project.ivlab.product.ProdukAdapter
import com.project.ivlab.product.ProdukViewModel

class ResultSearchProdcutFragment : Fragment() {

    lateinit var binding: FragmentResultSearchProdcutBinding
    private val viewModel: ProdukViewModel by lazy {
        ViewModelProvider(this).get(ProdukViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_result_search_prodcut, container, false
        )
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        Log.d(TAG, "onCreateView: ${requireArguments().get("key").toString()}")
        viewModel.searchProduct(requireArguments().get("key").toString())
        binding.lvResultSearchProduct.adapter = ProdukAdapter(
            ProdukAdapter.OnClickListener {
                viewModel.displayProductDetails(it)
            }
        )
        Log.d(TAG, "onCreateView: ")
        viewModel.navigatedToSelectedProperty.observe(
            viewLifecycleOwner, Observer {
                if (null != it) {
                    this.findNavController().navigate(
//                        ProductFragmentDirections.actionProductFragmentToDetailProductFragment(it)
                        ResultSearchHomeFragmentDirections.actionResultSearchCourseFragmentToDetailProductFragment2(it)
                    )
//                    val bundle = Bundle()
//                    bundle.putParcelable("selectedProduct", it)
//                    val details = DetailProductFragment()
//                    details.arguments = bundle
//                    fragmentManager?.beginTransaction()
//                        ?.replace(R.id.beranda_nav_host_fragment, details)
//                        ?.commit()
                    viewModel.displayProdcutDetailsCompelete()
                }


            }
        )



        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()
    }

    companion object {
        private const val TAG = "ResultSearchProdcutFrag"
    }

}