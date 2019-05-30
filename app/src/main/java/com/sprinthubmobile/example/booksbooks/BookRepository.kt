package com.sprinthubmobile.example.booksbooks

import io.reactivex.Observable

interface BookRepository {

    fun latestBooks(): Observable<List<Book>>

}
