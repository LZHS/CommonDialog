package com.lzhs.dialoglibrary.interfaces

import com.lzhs.dialoglibrary.util.BaseDialog

interface DialogLifeCycleListener {

     fun onCreate(dialog: BaseDialog)

     fun onShow(dialog: BaseDialog)

     fun onDismiss(dialog: BaseDialog)
}