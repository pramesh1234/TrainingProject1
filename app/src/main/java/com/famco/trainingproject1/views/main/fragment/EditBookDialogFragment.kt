package com.famco.trainingproject1.views.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.famco.trainingproject1.databinding.FragmentEditBookDialogBinding
import com.famco.trainingproject1.model.Book
import com.famco.trainingproject1.viewModel.EditBookDialogViewModel

class EditBookDialogFragment : DialogFragment() {

    lateinit var editBookViewModel: EditBookDialogViewModel
    private var _binding: FragmentEditBookDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditBookDialogBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val titleEt = binding.title
        val description = binding.description
        val updateBtn = binding.submitBtn
        val id = arguments?.getInt("id")

        editBookViewModel = ViewModelProvider(this).get(EditBookDialogViewModel::class.java)
        if (id != null) {
            editBookViewModel.hitGetPostApi(id)
        }
        editBookViewModel.bookData.observe(viewLifecycleOwner, Observer {

            titleEt.setText(it.title)
            description.setText(it.body)
        })
        updateBtn.setOnClickListener {
            val book = Book()

            if (id != null) {
                book.userId = 1
                book.id = id
                book.title = titleEt.text.toString()
                book.body = description.text.toString()
                editBookViewModel.hitUpdatePostApi(id, book)
            }
        }
        editBookViewModel.isUpdateSuccess.observe(viewLifecycleOwner, Observer {
            if (it) {
                Toast.makeText(context, "successfully updated", Toast.LENGTH_SHORT).show()
                dialog?.dismiss()
            }
        })
        return root

    }

    override fun onStart() {
        super.onStart()
        dialog?.window
            ?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

    }

}