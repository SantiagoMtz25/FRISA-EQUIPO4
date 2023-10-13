package com.example.loginpagetest

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.view.View
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RotatingCircleLoadingView(context: Context) : View(context) {
    private val paint: Paint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.STROKE
        strokeCap = Paint.Cap.ROUND
        color = 0xff009688.toInt()
    }
    private val oval = RectF()
    private var startAngle = 0f
    private val sweepAngle = 120f
    private val rotationSpeed = 2f

    private var animationJob: Job? = null

    fun startAnimation(scope: CoroutineScope) {
        animationJob = scope.launch {
            while (true) {
                startAngle += rotationSpeed
                if (startAngle > 360) {
                    startAngle -= 360f
                }
                postInvalidateOnAnimation()
                delay(16)
            }
        }
    }

    fun stopAnimation() {
        animationJob?.cancel()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val strokeWidth = (width.coerceAtMost(height) / 10).toFloat()
        paint.strokeWidth = strokeWidth
        oval.set(strokeWidth, strokeWidth, width - strokeWidth, height - strokeWidth)
        canvas.drawArc(oval, startAngle, sweepAngle, false, paint)
    }
}
