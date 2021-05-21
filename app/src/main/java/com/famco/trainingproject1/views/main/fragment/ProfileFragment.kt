package com.famco.trainingproject1.views.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.famco.trainingproject1.databinding.FragmentProfileBinding
import com.famco.trainingproject1.viewModel.ProfileViewModel
import com.famco.trainingproject1.views.base.BaseFragment

class ProfileFragment : BaseFragment() {

    private lateinit var profileViewModel: ProfileViewModel
    private var _binding: FragmentProfileBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        profileViewModel =
            ViewModelProvider(this).get(ProfileViewModel::class.java)

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val logoutBtn = binding.logoutBtn
        logoutBtn.setOnClickListener {
            context?.let { it1 -> showAlertDialog(it1,"user logged out") }
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}