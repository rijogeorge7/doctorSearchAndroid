package com.mogo.rijogeorge.rijosseekbar

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.support.v4.content.ContextCompat
import android.view.MotionEvent

class RijosSeekBar : View {

    private val progressPaint = Paint()
    private val remainingPaint = Paint()
    private val thumbPaint = Paint()
    private val progressTextPaint = Paint()
    private val padding = dp2px(2)
    private var progress=50
    private var progressText = "50"
    private var progressAppendText=""
    private var progressTextWithAppend =""
    private var min =0
    private var max = 100
    private var totalValue = 100
    private var progressPercent = 50f
    private var trackHeight = dp2px(2)
    private var thumbSize = dp2px(5).toFloat()
    private var progressTextSize = sp2px(12)
    private var mLeft = 0
    private var mRight = 0
    private var mTrackLength = 0f
    private var thumbCenterX = 0f

    //to retrive seek bar progress value
    var seekProgress : String = progress.toString()
        get() = this.progress.toString()

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        var progressColor = ContextCompat.getColor(context, R.color.progress_color)
        var remainingColor = ContextCompat.getColor(context, R.color.black)
        var progressTextColor = ContextCompat.getColor(context, R.color.progress_color)
        var thumbColor = ContextCompat.getColor(context, R.color.progress_color)

        if(attrs != null) {

            val a : TypedArray = context.obtainStyledAttributes(attrs, R.styleable.SeekValues, 0, 0)
            progressColor = a.getColor(R.styleable.SeekValues_progressColor, progressColor)
            remainingColor = a.getColor(R.styleable.SeekValues_remainingColor, remainingColor)
            progressTextColor = a.getColor(R.styleable.SeekValues_progressTextColor, progressTextColor)
            thumbColor = a.getColor(R.styleable.SeekValues_thumbColor, thumbColor)
            trackHeight = a.getDimensionPixelSize(R.styleable.SeekValues_trackHeight, trackHeight)
            thumbSize = a.getDimensionPixelSize(R.styleable.SeekValues_thumbSize, thumbSize.toInt()).toFloat()
            if(a.getString(R.styleable.SeekValues_progressTextAppend) != null) {
                progressAppendText = a.getString(R.styleable.SeekValues_progressTextAppend)
            }
            progressTextSize = a.getDimensionPixelSize(R.styleable.SeekValues_progressTextSize, progressTextSize)
            max = a.getInt(R.styleable.SeekValues_max, max)
            min = a.getInt(R.styleable.SeekValues_min, min)
            totalValue = max-min
            progress = a.getInt(R.styleable.SeekValues_progress, progress)
            progressText = progress.toString()
            progressPercent = progress.toFloat() / totalValue
            progressTextWithAppend = progressText+" "+progressAppendText
            a.recycle()

        }

        progressPaint.color=progressColor
        progressPaint.isAntiAlias=true
        progressPaint.style = Paint.Style.STROKE
        progressPaint.strokeWidth=trackHeight.toFloat()
        remainingPaint.color=remainingColor
        remainingPaint.isAntiAlias=true
        remainingPaint.style = Paint.Style.STROKE
        remainingPaint.strokeWidth = trackHeight.toFloat()
        thumbPaint.color=thumbColor
        thumbPaint.isAntiAlias=true
        thumbPaint.style = Paint.Style.FILL
        progressTextPaint.color = progressTextColor
        progressTextPaint.isAntiAlias=true
        progressTextPaint.style = Paint.Style.FILL
        progressTextPaint.textSize= progressTextSize.toFloat()

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        mLeft = 0
        mRight = measuredWidth
        mTrackLength = (mRight - mLeft).toFloat()
        thumbCenterX = mTrackLength*progressPercent
        val fontMetrics = progressTextPaint.getFontMetrics()
        val maxTextHeight = -fontMetrics.top+fontMetrics.bottom
        val desiredHeight = (maxTextHeight * 2f) + (thumbSize * 2f) + padding + padding
        val measureWidth = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec)
        val measuredHeight = resolveSize(desiredHeight.toInt(),heightMeasureSpec)
        setMeasuredDimension(measureWidth, measuredHeight)
    }

    override fun onDraw(canvas: Canvas?) {

        if(canvas != null) {
            //draw progress line
            canvas.drawLine(0.toFloat(), (canvas.height-thumbSize)-padding, thumbCenterX,(canvas.height-thumbSize)-padding, progressPaint)
            //draw remaining line
            canvas.drawLine(thumbCenterX, (canvas.height-thumbSize)-padding, mTrackLength,(canvas.height-thumbSize)-padding, remainingPaint)
            //draw thumb
            canvas.drawCircle(thumbCenterX, (canvas.height-thumbSize)-padding,thumbSize, thumbPaint)
            //draw progress text
            val textWidth = progressTextPaint.measureText(progressTextWithAppend)
            val textx = (thumbCenterX)-(textWidth/2)
            canvas.drawText(progressTextWithAppend, textx, canvas.height-thumbSize-thumbSize-padding, progressTextPaint)
        }

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {

        this.getParent().requestDisallowInterceptTouchEvent(true);
        when (event?.getAction()) {
            MotionEvent.ACTION_DOWN -> {updateToch(event)}
            MotionEvent.ACTION_MOVE -> {updateToch(event)}
            MotionEvent.ACTION_UP -> this.getParent().requestDisallowInterceptTouchEvent(false);
            MotionEvent.ACTION_CANCEL -> this.getParent().requestDisallowInterceptTouchEvent(false);
        }
        return true
    }

    private fun updateToch(event:  MotionEvent) {
        progressPercent = event.x / mTrackLength
        thumbCenterX = mTrackLength*progressPercent
        progress = (totalValue * progressPercent).toInt()
        progressText = progress.toString()+" mile"
        invalidate()
    }

}