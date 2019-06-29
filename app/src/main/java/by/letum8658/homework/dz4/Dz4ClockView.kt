package by.letum8658.homework.dz4

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.View
import by.letum8658.homework.R
import java.util.Calendar
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin

class Dz4ClockView : View {

    private val circlePaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val shortLinePaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val longLinePaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val hourPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val minutePaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val secondPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var cx = 0f
    private var cy = 0f
    private var radius = 0f
    private var handLine = 0f
    private var handHourLine = 0f

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(
        context,
        attrs,
        defStyleAttr,
        defStyleRes
    )

    init {
        circlePaint.color = ContextCompat.getColor(context, R.color.white)
        circlePaint.style = Paint.Style.STROKE
        circlePaint.strokeWidth = 5f

        textPaint.color = ContextCompat.getColor(context, R.color.white)
        textPaint.textSize = resources.getDimension(R.dimen.number_text_size)

        shortLinePaint.color = ContextCompat.getColor(context, R.color.white)
        shortLinePaint.strokeWidth = 4f

        longLinePaint.color = ContextCompat.getColor(context, R.color.white)
        longLinePaint.strokeWidth = 6f

        hourPaint.color = ContextCompat.getColor(context, R.color.white)
        hourPaint.strokeWidth = 20f

        minutePaint.color = ContextCompat.getColor(context, R.color.white)
        minutePaint.strokeWidth = 10f

        secondPaint.color = ContextCompat.getColor(context, R.color.colorAccent)
        secondPaint.strokeWidth = 5f
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        cx = width / 2f
        cy = height / 2f
        radius = min(width, height) / 2f - 20f
        handLine = radius * 0.72f
        handHourLine = radius * 0.45f
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas ?: return
        canvas.drawCircle(cx, cy, radius, circlePaint)
        drawNumber(canvas)
        drawLines(canvas)
        drawTime(canvas)
        postInvalidateDelayed(500)
        invalidate()
    }

    private fun drawNumber(canvas: Canvas) {
        canvas.drawText(
            "12",
            cx - textPaint.measureText("12") / 2,
            cy - radius + textPaint.measureText("12") / 2 * 3,
            textPaint
        )
        canvas.drawText(
            "3",
            cx + radius - textPaint.measureText("3") * 3,
            cy + textPaint.measureText("3") / 2,
            textPaint
        )
        canvas.drawText(
            "6",
            cx - textPaint.measureText("6") / 2,
            cy + radius - textPaint.measureText("6") * 2,
            textPaint
        )
        canvas.drawText(
            "9",
            cx - radius + textPaint.measureText("9") * 2,
            cy + textPaint.measureText("9") / 2,
            textPaint
        )
    }

    private fun drawLines(canvas: Canvas) {
        for (i in 0..59) {
            when {
                i % 15 == 0 -> {
                    canvas.drawLine(cx, cy - radius, cx, cy - radius + 25, shortLinePaint)
                    canvas.rotate(6f, cx, cy)
                }
                i % 5 == 0 -> {
                    canvas.drawLine(cx, cy - radius, cx, cy - radius + 75, longLinePaint)
                    canvas.rotate(6f, cx, cy)
                }
                else -> {
                    canvas.drawLine(cx, cy - radius, cx, cy - radius + 25, shortLinePaint)
                    canvas.rotate(6f, cx, cy)
                }
            }
        }
    }

    private fun drawTime(canvas: Canvas) {
        val time = Calendar.getInstance()
        var hour = time.get(Calendar.HOUR_OF_DAY)
        hour = if (hour > 12) hour - 12 else hour
        val hAngle = PI * ((hour + time.get(Calendar.MINUTE) / 60f) * 5f) / 30 - PI / 2
        val minAngle = PI * time.get(Calendar.MINUTE) / 30 - PI / 2
        val secAngle = PI * time.get(Calendar.SECOND) / 30 - PI / 2

        canvas.drawLine(
            cx,
            cy,
            cx + cos(hAngle).toFloat() * handHourLine,
            cy + sin(hAngle).toFloat() * handHourLine,
            hourPaint
        )

        canvas.drawLine(
            cx,
            cy,
            cx + cos(minAngle).toFloat() * handLine,
            cy + sin(minAngle).toFloat() * handLine,
            minutePaint
        )

        canvas.drawLine(
            cx,
            cy,
            cx + cos(secAngle).toFloat() * handLine,
            cy + sin(secAngle).toFloat() * handLine,
            secondPaint
        )
    }
}