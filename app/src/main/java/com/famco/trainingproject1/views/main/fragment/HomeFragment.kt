package com.famco.trainingproject1.views.main.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.famco.trainingproject1.adapters.BookListAdapter
import com.famco.trainingproject1.adapters.OnRowClickAdapter
import com.famco.trainingproject1.databinding.FragmentHomeBinding
import com.famco.trainingproject1.model.Book
import com.famco.trainingproject1.viewModel.HomeViewModel

private const val TAG = "HomeFragment"

class HomeFragment : Fragment(), OnRowClickAdapter {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val addBookFab = binding.addBook
        addBookFab.setOnClickListener {

        }

        val recyclerview: RecyclerView = binding.postRecyclerView
        recyclerview.layoutManager = LinearLayoutManager(context)
        val adapter = context?.let { BookListAdapter(it, this) }
        recyclerview.adapter = adapter

        homeViewModel.bookList.observe(viewLifecycleOwner, Observer {
            adapter?.updateList(it)

        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onEditItemClick(book: Book) {
        Log.e(TAG, "onItemClick: " + book.id)
        val args = Bundle()
        args.putInt("id", +book.id)
        val editBookDialogFragment: DialogFragment = EditBookDialogFragment()
        editBookDialogFragment.arguments = args
        this.fragmentManager?.let { it1 -> editBookDialogFragment.show(it1, "TAG") }
    }

    override fun onDeleteItemClick(id: Int) {
        homeViewModel.hitDeletePost(id)
    }
}