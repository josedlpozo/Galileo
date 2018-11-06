package com.josedlpozo.galileo.chuck.internal.ui

import android.content.Context
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.josedlpozo.galileo.R
import com.josedlpozo.galileo.chuck.internal.data.HttpTransactionRepository
import com.josedlpozo.galileo.chuck.internal.support.FormatUtils
import com.josedlpozo.galileo.items.GalileoItem

internal class TransactionListView(context: Context) : RecyclerView(context), GalileoItem {

    private val adapter: TransactionAdapter by lazy {
        TransactionAdapter {
            TransactionActivity.start(context, it.id)
        }
    }

    init {
        setAdapter(adapter)
        layoutManager = LinearLayoutManager(context)
        addItemDecoration(DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL))

        val items = HttpTransactionRepository.all()
        adapter.refresh(items)
        if (items.isNotEmpty()) scrollToPosition(items.size - 1)
    }

    override val view: View = this

    override val name: String = "Chuck"

    override val icon: Int = R.drawable.ic_http_request

    override fun snapshot(): String {
        val transactions = HttpTransactionRepository.all()
        return transactions.joinToString("\n\n\n\n", transform = {
            "===================================================\n" +
                    "===================================================\n\n" +
                    FormatUtils.getShareText(it) + "\n\n" +
                    "===================================================\n" +
                    "===================================================\n"
        }, postfix = "\n\n")
    }

}