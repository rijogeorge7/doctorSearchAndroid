package com.mogo.rijogeorge.doctorsearch.widgets

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import android.support.v4.content.res.ResourcesCompat
import android.util.AttributeSet
import android.view.View
import com.mogo.rijogeorge.doctorsearch.R


class DoctorSearchButton : View {

    private val bgPaint = Paint()
    private val textPaint = Paint()
    private var leftIcon : Drawable? = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_stethoscope, null)
    private var buttonText = "Find Doctors"
    private var buttonTextSize = sp2px(20)
    private var maxTextHeight : Float = 0.toFloat()
    private var iconHeight = dp2px(32)
    private var iconWidth = dp2px(22)
    private val padding = dp2px(10)
    var iconX=0
    var iconY=0

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        var bgColor = ContextCompat.getColor(context, R.color.colorPrimaryDark)
        var textColor = ContextCompat.getColor(context, R.color.white)
        if(attrs != null) {
            val a : TypedArray = context.obtainStyledAttributes(attrs, R.styleable.doctorButtonValues, 0, 0)
            if(a.getDrawable(R.styleable.doctorButtonValues_leftDrawable) != null)
                leftIcon = a.getDrawable(R.styleable.doctorButtonValues_leftDrawable)
            if(a.getString(R.styleable.doctorButtonValues_buttonText) != null)
                buttonText = a.getString(R.styleable.doctorButtonValues_buttonText)
            bgColor = a.getColor(R.styleable.doctorButtonValues_bgColor, bgColor)
            textColor = a.getColor(R.styleable.doctorButtonValues_textColor, textColor)
            buttonTextSize = a.getDimensionPixelSize(R.styleable.doctorButtonValues_buttonTextSize, buttonTextSize)
            iconWidth = a.getDimensionPixelSize(R.styleable.doctorButtonValues_iconWidth, iconWidth)
            iconHeight = a.getDimensionPixelSize(R.styleable.doctorButtonValues_iconHeight, iconHeight)
            textColor = a.getColor(R.styleable.doctorButtonValues_buttonTextColor, textColor)
            a.recycle()
        }

        bgPaint.color = bgColor
        bgPaint.isAntiAlias = true
        bgPaint.style = Paint.Style.FILL
        textPaint.color=textColor
        textPaint.isAntiAlias = true
        textPaint.style = Paint.Style.FILL
        textPaint.textSize = buttonTextSize.toFloat()
        textPaint.textAlign
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val fontMetrics = textPaint.getFontMetrics()
        maxTextHeight = -fontMetrics.top+fontMetrics.bottom
        val desiredHeight = maxTextHeight * 2f
        val measureWidth = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec)
        val measuredHeight = resolveSize(desiredHeight.toInt(),heightMeasureSpec)
        setMeasuredDimension(measureWidth, measuredHeight)
    }

    override fun onDraw(canvas: Canvas?) {
        if(canvas != null){
            //draw bg
            canvas.drawRect(0.toFloat(), 0.toFloat(), canvas.width.toFloat(), canvas.height.toFloat(), bgPaint)
            //draw button text
            val textWidth = textPaint.measureText(buttonText)
            val textx = (canvas.width/2) - (textWidth/2)
            canvas.drawText(buttonText, textx, (canvas.height/2) - ((textPaint.descent() + textPaint.ascent()) / 2), textPaint)
            //draw leftIcon
            iconX = (textx-(padding+iconWidth)).toInt()
            iconY = (canvas.height/2)-(iconHeight/2)
            leftIcon?.setBounds(iconX, iconY, iconX+iconWidth, iconY+iconHeight)
            leftIcon?.draw(canvas)
        }

    }

}