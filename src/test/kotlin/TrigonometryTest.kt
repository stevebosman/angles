package uk.co.stevebosman.angles

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import java.util.stream.Stream
import kotlin.math.*
import kotlin.math.acos as kacos
import kotlin.math.asin as kasin
import kotlin.math.atan as katan

internal class TrigonometryTest {
    @ParameterizedTest(name = "cos({0})==Angle.cos({0})")
    @MethodSource("testValues")
    fun cosTest(d: Double) {
        assertEquals(cos(degreesToRadians(d)), cos(Angle.fromDegrees(d)))
    }

    @ParameterizedTest(name = "sin({0})==Angle.sin({0})")
    @MethodSource("testValues")
    fun sinTest(d: Double) {
        assertEquals(sin(degreesToRadians(d)), sin(Angle.fromDegrees(d)))
    }

    @ParameterizedTest(name = "tan({0})==Angle.tan({0})")
    @MethodSource("testValues")
    fun tanTest(d: Double) {
        assertEquals(tan(degreesToRadians(d)), tan(Angle.fromDegrees(d)))
    }

    @ParameterizedTest(name = "1/cos({0})==Angle.sec({0})")
    @MethodSource("testValues")
    fun secTest(d: Double) {
        assertEquals(1 / cos(degreesToRadians(d)), sec(Angle.fromDegrees(d)))
    }

    @ParameterizedTest(name = "1/sin({0})==Angle.csc({0})")
    @MethodSource("testValues")
    fun cscTest(d: Double) {
        assertEquals(1 / sin(degreesToRadians(d)), csc(Angle.fromDegrees(d)))
    }

    @ParameterizedTest(name = "1/tan({0})==Angle.cot({0})")
    @MethodSource("testValues")
    fun cotTest(d: Double) {
        assertEquals(1 / tan(degreesToRadians(d)), cot(Angle.fromDegrees(d)))
    }

    @ParameterizedTest(name = "kacos({0}/720)==Angle.acos({0}/720)")
    @MethodSource("testValues")
    fun acosTest(d: Double) {
        val n = d / 720.0
        assertEquals(kacos(n), acos(n).radians)
    }

    @ParameterizedTest(name = "Angle.acos({0}) throws Exception")
    @ValueSource(doubles = [1.01, -1.01])
    fun acosFailureTest(d: Double) {
        assertThrows<IllegalArgumentException> { acos(d) }
    }

    @ParameterizedTest(name = "kasin({0}/720)==Angle.asin({0}/720).radians")
    @MethodSource("testValues")
    fun asinTest(d: Double) {
        val n = d / 720.0
        assertEquals(kasin(n), asin(n).radians)
    }

    @ParameterizedTest(name = "Angle.asin({0}) throws Exception")
    @ValueSource(doubles = [1.01, -1.01])
    fun asinFailureTest(d: Double) {
        assertThrows<IllegalArgumentException> { asin(d) }
    }

    @ParameterizedTest(name = "katan({0})==Angle.atan({0}).radians")
    @MethodSource("testValues")
    fun atanTest(n: Double) {
        assertEquals(katan(n), atan(n).radians)
    }

    @Test
    fun asecHigh() {
        assertEquals(0.0, asec(1.0).radians)
    }

    @Test
    fun asecLow() {
        assertEquals(PI, asec(-1.0).radians)
    }

    @ParameterizedTest(name = "Angle.asec({0}) throws Exception")
    @ValueSource(doubles = [0.9999999, -0.9999999])
    fun asecFailureTest(d: Double) {
        assertThrows<IllegalArgumentException> { asec(d) }
    }

    @Test
    fun acscHigh() {
        assertEquals(PI / 2, acsc(1.0).radians)
    }

    @Test
    fun acscLow() {
        assertEquals(-PI / 2, acsc(-1.0).radians)
    }

    @ParameterizedTest(name = "Angle.acsc({0}) throws Exception")
    @ValueSource(doubles = [0.9999999, -0.9999999])
    fun acscFailureTest(d: Double) {
        assertThrows<IllegalArgumentException> { acsc(d) }
    }

    @ParameterizedTest(name = "Angle.acot({1}) is approximately {0}, delta = {2}")
    @MethodSource("acotTestValues")
    fun acot(expected: Double, testValue: Double, delta: Double) {
        assertEquals(expected, acot(testValue).degrees, delta)
    }

    companion object {
        @JvmStatic
        fun testValues(): Stream<Arguments> {
            return (-720..720).map { Arguments.of(it.toDouble()) }.stream()
        }

        @JvmStatic
        fun acotTestValues(): Stream<Arguments> {
            // test values taken from http://www.maths.surrey.ac.uk/hosted-sites/R.Knott/Fibonacci/simpleTrig.html
            return listOf<Arguments>(
                Arguments.of(90.0, 0, 2e-14),
                Arguments.of(82.5, (sqrt(2.0) - 1) * (sqrt(3.0) - sqrt(2.0)), 2e-14),
                Arguments.of(75.0, 2 - sqrt(3.0), 2e-14),
                Arguments.of(72.0, sqrt(1 - (2 / sqrt(5.0))), 2e-14),
                Arguments.of(67.5, sqrt(2.0) - 1, 2e-14),
                Arguments.of(60.0, 1 / sqrt(3.0), 2e-14)
            ).stream()
        }
    }
}