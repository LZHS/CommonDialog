package com.lzhs.dialoglibrary.util

import android.app.Dialog
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import com.lzhs.dialoglibrary.R
import com.lzhs.dialoglibrary.dialogs.BottomMenu
import com.lzhs.dialoglibrary.dialogs.ShareDialog
import com.lzhs.dialoglibrary.dialogs.TipDialog
import com.lzhs.dialoglibrary.interfaces.OnDismissListener
import com.lzhs.dialoglibrary.interfaces.OnShowListener


abstract class BaseDialog {
    companion object {
        lateinit var newContext: AppCompatActivity
        /**
         * 对话框队列
         */
        var dialogList: ArrayList<BaseDialog> = ArrayList()
    }

    var mainHandler = Handler(Looper.getMainLooper())
    lateinit var context: AppCompatActivity
    lateinit var dialog: DialogHelper
    lateinit var baseDialog: BaseDialog
    var layoutId = -1
    var styleId = -1
    var isShow = false
    var isAlreadyShown = false

    var style: DialogSetting.STYLE? = null
    var theme: DialogSetting.THEME? = null
    lateinit var cancelable: BOOLEAN


    lateinit var titleTextInfo: TextInfo
    lateinit var messageTextInfo: TextInfo
    lateinit var buttonTextInfo: TextInfo
    lateinit var buttonPositiveTextInfo: TextInfo
    lateinit var inputInfo: InputInfo
    var backgroundColor = 0
    var customView: View? = null

    var onDismissListener: OnDismissListener? = null
    var dismissEvent: OnDismissListener? = null
    var onShowListener: OnShowListener? = null


    init {
        if (theme == null) theme = DialogSetting.theme
        if (style == null) style = DialogSetting.style
        if (backgroundColor == -1) backgroundColor = DialogSetting.backgroundColor
        if (titleTextInfo == null) titleTextInfo = DialogSetting.titleTextInfo
        if (messageTextInfo == null) messageTextInfo = DialogSetting.contentTextInfo
        if (buttonTextInfo == null) buttonTextInfo = DialogSetting.buttonTextInfo
        if (inputInfo == null) inputInfo = DialogSetting.inputInfo
        if (buttonPositiveTextInfo == null)
            buttonPositiveTextInfo = if (DialogSetting.buttonPositiveTextInfo == null) buttonTextInfo
            else DialogSetting.buttonPositiveTextInfo
    }

    fun build(baseDialog: BaseDialog, layoutId: Int): BaseDialog {
        this.baseDialog = baseDialog
        this.layoutId = layoutId
        return baseDialog
    }

    fun build(baseDialog: BaseDialog): BaseDialog {
        this.baseDialog = baseDialog
        this.layoutId = -1
        return baseDialog
    }

    fun showDialog() {
        showDialog(R.style.BaseDialog)
    }

    fun showDialog(style: Int) {
        DialogSetting.dialogLifeCycleListener?.let { it.onCreate(this) }
        isAlreadyShown = true
        styleId = style
        dismissEvent = object : OnDismissListener {
            override fun onDismiss() {
                isShow = false
                dialogList.remove(baseDialog)
                showNext()
                onDismissListener!!.onDismiss()
                DialogSetting.dialogLifeCycleListener?.let { it.onDismiss(baseDialog) }
            }
        }
        dialogList.add(baseDialog)
        if (baseDialog is TipDialog) showNow() else showNext()


    }

    private fun showNow() {
        isShow = true
        if (context.isDestroyed) {
            if (newContext == null) {
                Log.e(
                    "BaseDialog",
                    "Context错误的指向了一个已被关闭的Activity或者Null，有可能是Activity因横竖屏切换被重启或者您手动执行了unload()方法，请确认其能够正确指向一个正在使用的Activity"
                )
                return
            }
            context = newContext
        }
        val fragmentManager = context.supportFragmentManager
        dialog = DialogHelper.setLayoutId(baseDialog, layoutId)
        if (baseDialog is BottomMenu || baseDialog is ShareDialog) styleId = R.style.BaseDialog
        if (DialogSetting.systemDialogStyle != 0) styleId = DialogSetting.systemDialogStyle
        dialog.setStyle(DialogFragment.STYLE_NORMAL, styleId)
        dialog.show(fragmentManager, "BaseDialog")
        dialog.onShowListener = object : OnShowListener {
            override fun onShow(dialog: Dialog) {
                onShowListener?.onShow(dialog)
                DialogSetting.dialogLifeCycleListener?.onShow(this@BaseDialog)
            }
        }
        if (DialogSetting.systemDialogStyle == 0 &&
            style == DialogSetting.STYLE.STYLE_IOS &&
            baseDialog !is TipDialog &&
            baseDialog !is BottomMenu &&
            baseDialog !is ShareDialog
        ) dialog.animResId = R.style.iOSDialogAnimStyle
        dialog.onDismissListener = dismissEvent
        when (baseDialog) {
            is TipDialog -> {
                if (cancelable == null)
                    cancelable = if (DialogSetting.cancelableTipDialog) BOOLEAN.TRUE else BOOLEAN.FALSE
            }
        }

    }

    private fun showNext() {

    }

    abstract fun bindView(rootView: View)

    abstract fun refreshView()

    abstract fun show()

    fun doDismiss() {
        dialog?.dismiss()
    }

    enum class BOOLEAN {
        NULL, FALSE, TRUE
    }
}