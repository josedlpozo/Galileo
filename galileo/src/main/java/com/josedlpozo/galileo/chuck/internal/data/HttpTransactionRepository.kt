package com.josedlpozo.galileo.chuck.internal.data

import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject

internal object HttpTransactionRepository {

    val data: Subject<List<HttpTransaction>> = PublishSubject.create()

    private val list : MutableList<HttpTransaction> = mutableListOf()

    fun add(transaction: HttpTransaction) {
        list.add(transaction)
        data.onNext(list)
    }

    fun all(): List<HttpTransaction> = list

    fun find(id: Long) : HttpTransaction? = list.find { it.id == id }
}