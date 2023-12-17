package com.koreyhinton.goretro

import android.content.Context
import android.util.AttributeSet
import android.view.View

class GameView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        setBackgroundColor(0x00FF00)
        super.onLayout(changed, left, top, right, bottom)
    }
}