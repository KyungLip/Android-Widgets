package com.didi.nav.driving.sdk.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Path
import android.graphics.RectF
import android.graphics.Region
import android.util.AttributeSet
import android.widget.LinearLayout
import com.didi.sdk.map.web.utils.SelfDisplayUtils

/**
 * @author kyliupeng
 */
class ClipCornersLinearLayout : LinearLayout {
    /**
     * 圆角半径像素值
     */
    private var round = 24f

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        round = SelfDisplayUtils.dip2px(context, 8f).toFloat()
    }

    fun setRound(round: Float) {
        this.round = round
    }

    override fun dispatchDraw(canvas: Canvas) {
        if (round > 0) {
            val path = Path()
            val rectF = RectF(paddingLeft.toFloat(), paddingTop.toFloat(), (width - paddingRight).toFloat(), (height - paddingBottom).toFloat())
            path.addRoundRect(rectF, round, round, Path.Direction.CW)
            // 先对canvas进行裁剪
            canvas.clipPath(path, Region.Op.INTERSECT)
        }
        super.dispatchDraw(canvas)
    }
}