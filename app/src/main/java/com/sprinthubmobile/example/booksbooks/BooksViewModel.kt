package com.sprinthubmobile.example.booksbooks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.reactivex.disposables.CompositeDisposable

/**
 * A viewmodel can be shared easily between fragments by assigning an activity scope when initializing the viewmodel in fragment
 * All concerned fragments should observe latestBooks. Since the viewmodel has one instance, it is initialized once, therefore, all
 * fragments receive the same result whenever they attach to the activity. No multiple calls to repository is made.
 * You can implement your repository any how you want it. In this example, [BookRepository] has a method latestBooks which returns an [Observable]
 */
class BooksViewModel(private val repository: BookRepository) : ViewModel() {

    private val disposables = CompositeDisposable()

    private val _latestBooks = MutableLiveData<List<Book>>()


    val latestBooks: LiveData<List<Book>>
        get() = _latestBooks

    init {
        // Start loading books
        loadBooks()
    }

    private fun loadBooks() {
        repository.latestBooks()
            .subscribe({ books ->
                _latestBooks.postValue(books)
            }, { error -> TODO("Handle error") })
            .let { disposables.add(it) }
    }

    class Factory(private val repository: BookRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return BooksViewModel(repository) as T
        }

    }
}
