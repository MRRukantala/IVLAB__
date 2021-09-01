package com.project.ivlab.profil

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.project.ivlab.R
import com.project.ivlab.databinding.FragmentMyProductBinding
import com.project.ivlab.login.Data
import com.project.ivlab.product.ProdukAdapter

class MyProductFragment : Fragment() {

    lateinit var binding: FragmentMyProductBinding
    private val viewModel: MyProductViewModel by lazy {
        ViewModelProvider(this).get(MyProductViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_my_product, container, false
        )

        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        viewModel.valueId(requireActivity().getSharedPreferences("my_data_pref", Context.MODE_PRIVATE).getInt("id", -1))


        binding.recyclerViewMyProduct.adapter = ProdukAdapter(
            ProdukAdapter.OnClickListener{
                viewModel.displayProductDetails(it)
            }
        )

        viewModel.properties.observe(viewLifecycleOwner, {
            if(null != it){
                binding.kosong.visibility = View.GONE
                binding.kosongText.visibility = View.GONE
            }
        })




        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.option_menu, menu)
    }



}