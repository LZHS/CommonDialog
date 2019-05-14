package com.lzhs.dialoglibrary.util

import com.lzhs.dialoglibrary.interfaces.DialogLifeCycleListener

class DialogSetting {
    companion object {
        /**
         * 是否开启模糊效果
         */
        var isUseBlur = false

        /**
         * 全局主题风格
         */
        var style: STYLE = STYLE.STYLE_MATERIAL

        /**
         * 全局对话框明暗风格
         */
        var theme: THEME = THEME.LIGHT

        /**
         * 全局提示框明暗风格
         */
        var tipTheme: THEME = THEME.DARK

        /**
         * 全局标题文字样式
         */
        lateinit var titleTextInfo: TextInfo

        /**
         * 全局正文文字样式
         */
        lateinit var contentTextInfo: TextInfo

        /**
         * 全局默认按钮文字样式
         */
        lateinit var buttonTextInfo: TextInfo

        /**
         * 全局焦点按钮文字样式
         */
        lateinit var buttonPositiveTextInfo: TextInfo

        /**
         * 全局输入框文本样式
         */
        lateinit var inputInfo:InputInfo

        /**
         * 全局对话框背景颜色，值0时不生效
         */
        var backgroundColor=0

        /**
         * 全局对话框默认是否可以点击外围遮罩区域或返回键关闭，此开关不影响提示框（TipDialog）以及等待框（TipDialog）
         */
        var cancelable=false

        /**
         * 全局提示框及等待框（WaitDialog、TipDialog）默认是否可以关闭
         */
        var cancelableTipDialog=false

        /**
         * 模糊透明度(0~255)
         */
        var blurAlpha=210

        /**
         * 允许自定义系统对话框style，注意设置此功能会导致原对话框风格和动画失效
         */
        var systemDialogStyle = 0

        /**
         * 全局Dialog生命周期监听器
         */
        var dialogLifeCycleListener:DialogLifeCycleListener? = null
    }


    enum class STYLE {
        STYLE_MATERIAL, STYLE_KONGZUE, STYLE_IOS
    }

    enum class THEME {
        /**
         * 高亮
         */
        LIGHT,
        /**
         * 暗色
         */
        DARK
    }

}