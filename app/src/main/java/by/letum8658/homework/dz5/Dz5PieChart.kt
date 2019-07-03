package by.letum8658.homework.dz5

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.View
import by.letum8658.homework.R
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin
import kotlin.random.Random

class Dz5PieChart : View {
    private val arcPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val ovalRectF = RectF()
    private val linePaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val pointPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var cx = 0f
    private var cy = 0f
    private var radius = 0f
    private val random = Random
    private var startAngle = 0f
    private var sweepAngle = 0f
    private var sumNumbers = 0f
    private var lineLenght = 0f
    private var pointRadius = 0f

    var listNumbers: List<Int> = mutableListOf()
        set(value) {
            field = value
            invalidate()
        }

    constructor(contex: Context?) : super(contex)
    constructor(contex: Context?, attrs: AttributeSet?) : super(contex, attrs)
    constructor(contex: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(contex, attrs, defStyleAttr)
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(contex: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(
        contex,
        attrs,
        defStyleAttr,
        defStyleRes
    )

    init {
        arcPaint.style = Paint.Style.FILL

        linePaint.color = ContextCompat.getColor(context, R.color.gray)

        pointPaint.color = ContextCompat.getColor(context, R.color.gray)

        textPaint.color = ContextCompat.getColor(context, R.color.black)
        textPaint.textSize = resources.getDimension(R.dimen.text_size_hw5)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        cx = width / 2f
        cy = height / 2f
        radius = min(width, height) / 3.5f

        ovalRectF.left = cx - radius
        ovalRectF.right = cx + radius
        ovalRectF.top = cy - radius
        ovalRectF.bottom = cy + radius

        lineLenght = radius / 5f
        pointRadius = radius / 40f

        linePaint.strokeWidth = radius / 80f
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas ?: return
        drawPieChart(canvas)
        drawLines(canvas)
        drawText(canvas)
    }

    private fun drawPieChart(canvas: Canvas) {
        sumNumbers = listNumbers.sum().toFloat()
        for (i in listNumbers) {
            arcPaint.setARGB(255, random.nextInt(256), random.nextInt(256), random.nextInt(256))
            sweepAngle = 360f * i.toFloat() / sumNumbers
            canvas.drawArc(ovalRectF, startAngle, sweepAngle, true, arcPaint)
            startAngle += sweepAngle
        }
    }

    private fun drawLines(canvas: Canvas) {
        startAngle = 90f
        sweepAngle = 0f
        for (i in listNumbers) {
            canvas.save()
            canvas.rotate(startAngle, cx, cy)
            sweepAngle = 360f * i.toFloat() / sumNumbers
            canvas.rotate(sweepAngle / 2f, cx, cy)
            canvas.drawLine(cx, cy - radius, cx, cy - radius - lineLenght, linePaint)
            canvas.drawCircle(cx, cy - radius - lineLenght, pointRadius, pointPaint)
            startAngle += sweepAngle
            canvas.restore()
        }
    }

    private fun drawText(canvas: Canvas) {
        startAngle = 0f
        sweepAngle = 0f
        var angle: Float
        for (i in listNumbers) {
            sweepAngle = 360f * i.toFloat() / sumNumbers
            angle = (startAngle + sweepAngle / 2f) * PI.toFloat() / 180f
            if (((90f + startAngle + sweepAngle / 2f) % 360) in 0f..180f) {
                canvas.drawText(
                    i.toString(),
                    cx + cos(angle) * (radius + lineLenght) + lineLenght / 2f,
                    cy + sin(angle) * (radius + lineLenght) + textPaint.textSize / 2.5f,
                    textPaint
                )
            } else {
                canvas.drawText(
                    i.toString(),
                    cx + cos(angle) * (radius + lineLenght) - lineLenght / 2f - textPaint.measureText(i.toString()),
                    cy + sin(angle) * (radius + lineLenght) + textPaint.textSize / 2.5f,
                    textPaint
                )
            }
            startAngle += sweepAngle
        }
    }
}