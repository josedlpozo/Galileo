package com.josedlpozo.galileo.remoteconfig.view


import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.FrameLayout
import com.josedlpozo.galileo.R
import com.josedlpozo.galileo.remoteconfig.RemoteConfigKey
import com.josedlpozo.galileo.remoteconfig.RemoteConfigKeyString

internal class StringPrefEditor @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, key: RemoteConfigKey, listener: (RemoteConfigKey) -> Unit = {}) : FrameLayout(context, attrs, defStyleAttr) {

    private val valueView: EditText

    var value: String
        get() = valueView.text.toString()
        set(value) = valueView.setText(value)

    init {
        LayoutInflater.from(context).inflate(R.layout.item_editor_string, this, true)
        valueView = findViewById(R.id.pref_value)
        valueView.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                listener(RemoteConfigKeyString(key.key, charSequence.toString()))
            }

            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}

            override fun afterTextChanged(editable: Editable) {}
        })
    }
}