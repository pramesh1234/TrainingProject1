package com.famco.trainingproject1.views.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.famco.trainingproject1.databinding.FragmentAddBookBinding
import com.famco.trainingproject1.viewModel.AddBookViewModel
import com.famco.trainingproject1.views.base.BaseFragment

class AddBookFragment : BaseFragment() {

    private lateinit var addBookViewModel: AddBookViewModel
    private var _binding: FragmentAddBookBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        addBookViewModel =
            ViewModelProvider(this).get(AddBookViewModel::class.java)

        _binding = FragmentAddBookBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val titleEt = binding.title
        val description = binding.description
        val submitBtn = binding.submitBtn
        submitBtn.setOnClickListener {
            addBookViewModel.hitAddPostApi(titleEt.text.toString(), description.text.toString())
        }
        addBookViewModel.showSuccessToast.observe(viewLifecycleOwner, Observer {
            if (it) {
                context?.let { it1 -> showToast(it1, "Post added successfully") }
                titleEt.setText("")
                description.setText("")
            }
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}