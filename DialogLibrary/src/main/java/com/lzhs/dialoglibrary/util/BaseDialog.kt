package com.lzhs.dialoglibrary.util

import android.os.Handler
import android.os.Looper
import android.util.SparseArray
import android.view.View
import androidx.appcompat.app.AppCompatActivity


class BaseDialog {
    companion object {
        lateinit var newContext: AppCompatActivity
        /**
         * 对话框队列
         */
        var dialogList: SparseArray<BaseDialog> = SparseArray()
    }

    var mainHandler = Handler(Looper.getMainLooper())
    lateinit var context: AppCompatActivity
    lateinit var dialog: DialogHelper
    lateinit var baseDialog: BaseDialog
    var layoutId = -1
    var styleId = -1
    var isShow = false
    var isAlreadyShown = false

    lateinit var style: DialogSetting.STYLE
    lateinit var theme: DialogSetting.THEME
    lateinit var cancelable: BOOLEAN


    lateinit var titleTextInfo: TextInfo
    lateinit var messageTextInfo: TextInfo
    lateinit var buttonTextInfo: TextInfo
    lateinit var buttonPositiveTextInfo: TextInfo
    lateinit var inputInfo: InputInfo
    var backgroundColor = 0
    var customView: View? = null


    init {

    }


    enum class BOOLEAN {
        NULL, FALSE, TRUE
    }
}