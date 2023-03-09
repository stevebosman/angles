package uk.co.stevebosman.angles

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import kotlin.math.*
import kotlin.math.acos as kacos
import kotlin.math.asin as kasin
import kotlin.math.atan as katan

internal class TrigonometryTest {
    @Test
    fun cos() {
        for (d in -720..720) {
            assertEquals(cos(degreesToRadians(d)), cos(Angle.fromDegrees(d)))
        }
    }

    @Test
    fun sin() {
        for (d in -720..720) {
            assertEquals(sin(degreesToRadians(d)), sin(Angle.fromDegrees(d)))
        }
    }

    @Test
    fun tan() {
        for (d in -720..720) {
            assertEquals(tan(degreesToRadians(d)), tan(Angle.fromDegrees(d)))
        }
    }

    @Test
    fun sec() {
        for (d in -720..720) {
            assertEquals(1 / cos(degreesToRadians(d)), sec(Angle.fromDegrees(d)))
        }
    }

    @Test
    fun csc() {
        for (d in -720..720) {
            assertEquals(1 / sin(degreesToRadians(d)), csc(Angle.fromDegrees(d)))
        }
    }

    @Test
    fun cot() {
        for (d in -720..720) {
            assertEquals(1 / tan(degreesToRadians(d)), cot(Angle.fromDegrees(d)))
        }
    }

    @Test
    fun acos() {
        for (d in -720..720) {
            val n = d / 720.0
            assertEquals(kacos(n), acos(n).radians)
        }
    }

    @Test
    fun asin() {
        for (d in -720..720) {
            val n = d / 720.0
            assertEquals(kasin(n), asin(n).radians)
        }
    }

    @Test
    fun atan() {
        for (n in -720..720) {
            assertEquals(katan(n.toDouble()), atan(n.toDouble()).radians)
        }
    }

    @Test
    fun asec() {
        assertEquals(0.0, asec(1.0).radians)
        assertEquals(PI, asec(-1.0).radians)
    }

    @ParameterizedTest
    @ValueSource(doubles = [0.99999, 0.0, -0.99999])
    fun asecIllegalArgumentException(d: Double) {
        assertThrows(IllegalArgumentException::class.java) { asec(d) }
    }

    @Test
    fun acsc() {
        assertEquals(PI / 2, acsc(1.0).radians)
        assertEquals(-PI / 2, acsc(-1.0).radians)
    }

    @Test
    fun acot() {
        // test values taken from http://www.maths.surrey.ac.uk/hosted-sites/R.Knott/Fibonacci/simpleTrig.html
        assertEquals(90.0, acot(0).degrees, 2e-14)
        assertEquals(82.5, acot((sqrt(2.0) - 1) * (sqrt(3.0) - sqrt(2.0))).degrees, 2e-14)
        assertEquals(75.0, acot(2 - sqrt(3.0)).degrees, 2e-14)
        assertEquals(72.0, acot(sqrt(1 - (2 / sqrt(5.0)))).degrees, 2e-14)
        assertEquals(67.5, acot(sqrt(2.0) - 1).degrees, 2e-14)
        assertEquals(60.0, acot(1 / sqrt(3.0)).degrees, 2e-14)
    }
}