package com.lzhs.dialoglibrary.util

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.lzhs.dialoglibrary.interfaces.OnDismissListener
import com.lzhs.dialoglibrary.interfaces.OnShowListener

object DialogHelper : DialogFragment() {
    var onDismissListener: OnDismissListener? = null
    var onShowListener: OnShowListener? = null
    var isRestartDialog = false
    var materialDialog: AlertDialog? = null
    var layoutId = -1
    var rootView: View? = null
    lateinit var parentId: String
    var styleId = -1
    var animResId = -1

    lateinit var baseDialog: BaseDialog


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    fun setLayoutId(baseDialog: BaseDialog, layoutId: Int): DialogHelper {
        this.baseDialog = baseDialog
        this.layoutId = layoutId
        this.parentId = baseDialog.toString()
        return this
    }

    override fun setStyle(style: Int, theme: Int) {
        styleId = theme
        super.setStyle(style, theme)
    }

}