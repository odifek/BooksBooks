package com.sprinthubmobile.example.booksbooks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders


class FirstFragment : Fragment() {

    companion object {
        fun newInstance() = FirstFragment()
    }

    private lateinit var viewModel: BooksViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.first_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val factory = BooksViewModel.Factory(repositoryImplementation)
        viewModel = ViewModelProviders.of(requireActivity(), factory).get(BooksViewModel::class.java)

        viewModel.latestBooks.observe(requireActivity(), Observer { books ->
            // TODO: Update the ui with books information
        })
    }

}
