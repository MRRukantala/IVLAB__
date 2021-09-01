package com.project.ivlab.profil

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.project.ivlab.MainActivity
import com.project.ivlab.R
import com.project.ivlab.course.CourseAdapter
import com.project.ivlab.databinding.FragmentProfilBinding
import com.project.ivlab.login.Data
import com.project.ivlab.profil.myCourse.MyCourseViewModel
import com.project.ivlab.splashscreen.SplashScreenFragmentDirections


class ProfilFragment : Fragment() {

    lateinit var binding: FragmentProfilBinding
    private val viewModel: MyCourseViewModel by lazy {
        ViewModelProvider(this).get(MyCourseViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_profil, container, false
        )


        binding.profileCardViewProduct.setOnClickListener {
            findNavController().navigate(ProfilFragmentDirections.actionProfilFragmentToMyProductFragment())
        }

        binding.profileCardViewSharing.setOnClickListener {
            findNavController().navigate(ProfilFragmentDirections.actionProfilFragmentToMySharingFragment())
        }

        binding.profileCardViewLaunching.setOnClickListener {
            findNavController().navigate(ProfilFragmentDirections.actionProfilFragmentToMyLaunchingFragment())
        }

        binding.lifecycleOwner = this
        binding.myCourseViewModel = viewModel
        viewModel.valueId(getData().id)

        binding.recyclerView.adapter =
            CourseAdapter(CourseAdapter.OnClickListener {
                viewModel.displayMyCourseDetails(it)
            })

        binding.textView.text = getData().nama_bumdes
        binding.textView2.text = getData().desa
        binding.textView13.text = getData().nama_bumdes

        viewModel.navigatedToSelectedProperty.observe(
            viewLifecycleOwner, {
                if (null != it) {
                    this.findNavController().navigate(
                        ProfilFragmentDirections.actionProfilFragmentToDetailMyCourse(it)
                    )
                    viewModel.displayMyCourseDetailsCompelete()
                }
            }
        )

        viewModel.property.observe(viewLifecycleOwner, {
            if (null != it) {
                binding.kosong.visibility = View.GONE
                binding.kosongText.visibility = View.GONE
            }
        })


        // Inflate the layout for this fragment
        return binding.root
    }

    private fun keluar() {
        requireActivity().getSharedPreferences("my_data_pref", Context.MODE_PRIVATE).edit()
            .putInt("id", -1)
            .apply()
        SplashScreenFragmentDirections.actionSplashScreenFragmentToLoginFragment()
        toMainActivity()
        requireActivity().finish()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as AppCompatActivity).supportActionBar?.show()

        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.option_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.app_bar_keluar -> {
                keluar()
                return true
            }
            R.id.app_bar_edit_profile -> {

            }
        }
        return true
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

    fun toMainActivity() {
        val intent = Intent(requireContext(), MainActivity::class.java)
        startActivity(intent)
    }
}