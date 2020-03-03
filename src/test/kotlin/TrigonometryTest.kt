package uk.co.stevebosman.angles
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.tan
import kotlin.math.acos as kacos
import kotlin.math.asin as kasin
import kotlin.math.atan as katan

internal class TrigonometryTest {
    @Test
    fun cos() {
        for(d in -720..720) {
            assertEquals(cos(degreesToRadians(d)), cos(Angle.fromDegrees(d)))
        }
    }

    @Test
    fun sin() {
        for(d in -720..720) {
            assertEquals(sin(degreesToRadians(d)), sin(Angle.fromDegrees(d)))
        }
    }

    @Test
    fun tan() {
        for(d in -720..720) {
            assertEquals(tan(degreesToRadians(d)), tan(Angle.fromDegrees(d)))
        }
    }

    @Test
    fun sec() {
        for(d in -720..720) {
            assertEquals(1/cos(degreesToRadians(d)), sec(Angle.fromDegrees(d)))
        }
    }

    @Test
    fun csc() {
        for(d in -720..720) {
            assertEquals(1/sin(degreesToRadians(d)), csc(Angle.fromDegrees(d)))
        }
    }

    @Test
    fun cot() {
        for(d in -720..720) {
            assertEquals(1/tan(degreesToRadians(d)), cot(Angle.fromDegrees(d)))
        }
    }

    @Test
    fun acos() {
        for(d in -720..720) {
            val n = d/720.0
            assertEquals(kacos(n), acos(n).radians)
        }
    }

    @Test
    fun asin() {
        for(d in -720..720) {
            val n = d/720.0
            assertEquals(kasin(n), asin(n).radians)
        }
    }

    @Test
    fun atan() {
        for(n in -720..720) {
            assertEquals(katan(n.toDouble()), atan(n.toDouble()).radians)
        }
    }
}