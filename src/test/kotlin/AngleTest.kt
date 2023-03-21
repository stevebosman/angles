package uk.co.stevebosman.angles

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import uk.co.stevebosman.angles.Angle.Companion.ONE_TURN_RADIANS
import java.util.stream.Stream
import kotlin.math.PI

internal class AngleTest {

    @Test
    fun from0Degrees() {
        val test = Angle.fromDegrees(0)
        assertEquals(0.0, test.radians)
        assertEquals(0.0, test.degrees)
    }

    @Test
    fun from90Degrees() {
        val test = Angle.fromDegrees(90)
        assertEquals(PI / 2, test.radians)
        assertEquals(90.0, test.degrees)
    }

    @Test
    fun from180Degrees() {
        val test = Angle.fromDegrees(180)
        assertEquals(PI, test.radians)
        assertEquals(180.0, test.degrees)
    }

    @Test
    fun from270Degrees() {
        val test = Angle.fromDegrees(270)
        assertEquals(3 * PI / 2, test.radians)
        assertEquals(270.0, test.degrees)
    }

    @Test
    fun from360Degrees() {
        val test = Angle.fromDegrees(360)
        assertEquals(2 * PI, test.radians)
        assertEquals(360.0, test.degrees)
    }

    @Test
    fun from0Radians() {
        val test = Angle.fromRadians(0.0)
        assertEquals(0.0, test.radians)
        assertEquals(0.0, test.degrees)
    }

    @Test
    fun fromPiOver2Radians() {
        val test = Angle.fromRadians(PI / 2)
        assertEquals(PI / 2, test.radians)
        assertEquals(90.0, test.degrees)
    }

    @Test
    fun fromPiRadians() {
        val test = Angle.fromRadians(PI)
        assertEquals(PI, test.radians)
        assertEquals(180.0, test.degrees)
    }

    @Test
    fun from3PiOver2Radians() {
        val test = Angle.fromRadians(3 * PI / 2)
        assertEquals(3 * PI / 2, test.radians)
        assertEquals(270.0, test.degrees)
    }

    @Test
    fun from2PiRadians() {
        val test = Angle.fromRadians(2 * PI)
        assertEquals(2 * PI, test.radians)
        assertEquals(360.0, test.degrees)
    }

    @Test
    fun unaryMinus() {
        val test = -Angle.fromRadians(PI)
        assertEquals(-PI, test.radians)
        assertEquals(-180.0, test.degrees)
    }

    @Test
    fun plusDegrees() {
        val test = Angle.fromDegrees(180.0) + Angle.fromDegrees(90)
        assertEquals(Angle.Accuracy.DEGREES, test.accuracy)
        assertEquals(PI * 3 / 2, test.radians)
        assertEquals(270.0, test.degrees)
    }

    @Test
    fun plusRadians() {
        val test = Angle.fromRadians(PI) + Angle.fromRadians(PI / 2)
        assertEquals(Angle.Accuracy.RADIANS, test.accuracy)
        assertEquals(PI * 3 / 2, test.radians)
        assertEquals(270.0, test.degrees)
    }

    @Test
    fun plusMixed() {
        val test = Angle.fromRadians(PI) + Angle.fromDegrees(90)
        assertEquals(Angle.Accuracy.MIXED, test.accuracy)
        assertEquals(PI * 3 / 2, test.radians)
        assertEquals(270.0, test.degrees)
    }

    @Test
    fun minusDegrees() {
        val test = Angle.fromDegrees(180) - Angle.fromDegrees(90)
        assertEquals(Angle.Accuracy.DEGREES, test.accuracy)
        assertEquals(PI / 2, test.radians)
        assertEquals(90.0, test.degrees)
    }

    @Test
    fun minusRadians() {
        val test = Angle.fromRadians(PI) - Angle.fromRadians(PI / 2)
        assertEquals(Angle.Accuracy.RADIANS, test.accuracy)
        assertEquals(PI / 2, test.radians)
        assertEquals(90.0, test.degrees)
    }

    @Test
    fun minusMixed() {
        val test = Angle.fromRadians(PI) - Angle.fromDegrees(90)
        assertEquals(Angle.Accuracy.MIXED, test.accuracy)
        assertEquals(PI / 2, test.radians)
        assertEquals(90.0, test.degrees)
    }

    @Test
    fun timesRadians() {
        val test = Angle.fromRadians(PI) * 3
        assertEquals(Angle.Accuracy.RADIANS, test.accuracy)
        assertEquals(PI * 3, test.radians)
        assertEquals(540.0, test.degrees)
    }

    @Test
    fun timesDegrees() {
        val test = Angle.fromDegrees(180) * 3
        assertEquals(Angle.Accuracy.DEGREES, test.accuracy)
        assertEquals(PI * 3, test.radians)
        assertEquals(540.0, test.degrees)
    }

    @Test
    fun timesMixed() {
        val test = (Angle.fromRadians(PI / 2) + Angle.fromDegrees(90)) * 3
        assertEquals(Angle.Accuracy.MIXED, test.accuracy)
        assertEquals(PI * 3, test.radians)
        assertEquals(540.0, test.degrees)
    }

    @Test
    fun divRadians() {
        val test = Angle.fromRadians(2 * PI) / 2
        assertEquals(Angle.Accuracy.RADIANS, test.accuracy)
        assertEquals(PI, test.radians)
        assertEquals(180.0, test.degrees)
    }

    @Test
    fun divDegrees() {
        val test = Angle.fromDegrees(360) / 2
        assertEquals(Angle.Accuracy.DEGREES, test.accuracy)
        assertEquals(PI, test.radians)
        assertEquals(180.0, test.degrees)
    }

    @Test
    fun divMixed() {
        val test = (Angle.fromDegrees(180) + Angle.fromRadians(PI)) / 2
        assertEquals(Angle.Accuracy.MIXED, test.accuracy)
        assertEquals(PI, test.radians)
        assertEquals(180.0, test.degrees)
    }

    @Test
    fun simplifyDegrees() {
        val test = Angle.fromDegrees(540).simplify()
        assertEquals(Angle.Accuracy.DEGREES, test.accuracy)
        assertEquals(PI, test.radians)
        assertEquals(180.0, test.degrees)
    }

    @Test
    fun simplifyRadians() {
        val test = Angle.fromRadians(5 * PI).simplify()
        assertEquals(Angle.Accuracy.RADIANS, test.accuracy)
        assertEquals(PI, test.radians)
        assertEquals(180.0, test.degrees)
    }

    @Test
    fun simplifyMixed() {
        val test = (Angle.fromRadians(3 * PI) + Angle.fromDegrees(360)).simplify()
        assertEquals(Angle.Accuracy.MIXED, test.accuracy)
        assertEquals(PI, test.radians)
        assertEquals(180.0, test.degrees)
    }

    @Test
    fun simplifyDegreesSplit() {
        val test = Angle.fromDegrees(540).simplify(-180, true)
        assertEquals(Angle.Accuracy.DEGREES, test.accuracy)
        assertEquals(-PI, test.radians)
        assertEquals(-180.0, test.degrees)
    }

    @Test
    fun simplifyRadiansSplit() {
        val test = Angle.fromRadians(5 * PI).simplify(-PI, true)
        assertEquals(Angle.Accuracy.RADIANS, test.accuracy)
        assertEquals(-PI, test.radians)
        assertEquals(-180.0, test.degrees)
    }

    @Test
    fun simplifyMixedSplit() {
        val test = (Angle.fromRadians(3 * PI) + Angle.fromDegrees(360)).simplify(-PI, true)
        assertEquals(Angle.Accuracy.MIXED, test.accuracy)
        assertEquals(-PI, test.radians)
        assertEquals(-180.0, test.degrees)
    }

    @Test
    fun simplifyDegreesSplit2() {
        val test = Angle.fromDegrees(540).simplify(-180, false)
        assertEquals(Angle.Accuracy.DEGREES, test.accuracy)
        assertEquals(PI, test.radians)
        assertEquals(180.0, test.degrees)
    }

    @Test
    fun simplifyRadiansSplit2() {
        val test = Angle.fromRadians(5 * PI).simplify(-PI, false)
        assertEquals(Angle.Accuracy.RADIANS, test.accuracy)
        assertEquals(PI, test.radians)
        assertEquals(180.0, test.degrees)
    }

    @Test
    fun simplifyMixedSplit2() {
        val test = (Angle.fromRadians(3 * PI) + Angle.fromDegrees(360)).simplify(-PI, false)
        assertEquals(Angle.Accuracy.MIXED, test.accuracy)
        assertEquals(PI, test.radians)
        assertEquals(180.0, test.degrees)
    }

    @ParameterizedTest(name = "isEquivalentTo returns {0} comparing {1} to {2}")
    @MethodSource("provideValuesForIsEquivalentTo")
    fun isEquivalentTo(expected: Boolean, angle1: Angle, angle2: Angle) {
        assertIsEquivalentTo(expected, angle1, angle2)
    }

    @Test
    fun isEquivalentToPositiveInfinity() {
        val infinityDegrees = Angle.fromDegrees(Double.POSITIVE_INFINITY)
        val infinityRadians = Angle.fromRadians(Double.POSITIVE_INFINITY)
        @Suppress("KotlinConstantConditions")
        assertIsEquivalentTo(Double.POSITIVE_INFINITY == Double.POSITIVE_INFINITY, infinityRadians, infinityDegrees)
    }

    @Test
    fun isEquivalentToNegativeInfinity() {
        val negativeInfinityDegrees = Angle.fromDegrees(Double.NEGATIVE_INFINITY)
        val negativeInfinityRadians = Angle.fromRadians(Double.NEGATIVE_INFINITY)
        @Suppress("KotlinConstantConditions")
        assertIsEquivalentTo(
            Double.NEGATIVE_INFINITY == Double.NEGATIVE_INFINITY,
            negativeInfinityDegrees,
            negativeInfinityRadians
        )
    }

    @Test
    fun isEquivalentToNaN() {
        val nanDegrees = Angle.fromDegrees(Double.NaN)
        val nanRadians = Angle.fromRadians(Double.NaN)
        @Suppress("KotlinConstantConditions", "ConvertNaNEquality")
        assertIsEquivalentTo(Double.NaN == Double.NaN, nanDegrees, nanRadians)
    }

    private fun assertIsEquivalentTo(expected: Boolean, angle1: Angle, angle2: Angle) {
        println("Checking $angle1 isEquivalentTo $angle2")
        if (expected) {
            assertTrue(angle1.isEquivalentTo(angle2, 0.0, 3e-14), "expected $angle1 to be equivalent to $angle2")
            assertTrue(angle1.isEquivalentTo(angle2, 1e-14, 0.0), "expected $angle1 to be equivalent to $angle2")
        } else {
            assertFalse(angle1.isEquivalentTo(angle2, 0.0, 3e-14), "expected $angle1 to not be equivalent to $angle2")
            assertFalse(angle1.isEquivalentTo(angle2, 1e-14, 0.0), "expected $angle1 to not be equivalent to $angle2")
        }
    }


    @Test
    fun isExactlyEquivalentTo() {
        val angle1 = Angle.fromDegrees(0)
        val angle2 = Angle.fromDegrees(360)
        assertTrue(angle1.isEquivalentTo(angle2), "expected $angle1 to be equivalent to $angle2")
        assertTrue(angle2.isEquivalentTo(angle1), "expected $angle2 to be equivalent to $angle1")
    }

    @ParameterizedTest(name = "isCloseTo returns {0} comparing {1} to {2}")
    @MethodSource("provideValuesForIsCloseTo")
    fun isCloseTo(expected: Boolean, angle1: Angle, angle2: Angle) {
        assertIsCloseTo(expected, angle1, angle2)
    }

    @Test
    fun isCloseToPositiveInfinity() {
        val infinityDegrees = Angle.fromDegrees(Double.POSITIVE_INFINITY)
        val infinityRadians = Angle.fromRadians(Double.POSITIVE_INFINITY)
        @Suppress("KotlinConstantConditions")
        assertIsCloseTo(Double.POSITIVE_INFINITY == Double.POSITIVE_INFINITY, infinityRadians, infinityDegrees)
    }

    @Test
    fun isCloseToNegativeInfinity() {
        val negativeInfinityDegrees = Angle.fromDegrees(Double.NEGATIVE_INFINITY)
        val negativeInfinityRadians = Angle.fromRadians(Double.NEGATIVE_INFINITY)
        @Suppress("KotlinConstantConditions")
        assertIsCloseTo(
            Double.NEGATIVE_INFINITY == Double.NEGATIVE_INFINITY,
            negativeInfinityDegrees,
            negativeInfinityRadians
        )
    }

    @Test
    fun isCloseToNaN() {
        val nanDegrees = Angle.fromDegrees(Double.NaN)
        val nanRadians = Angle.fromRadians(Double.NaN)
        @Suppress("KotlinConstantConditions", "ConvertNaNEquality")
        assertIsCloseTo(Double.NaN == Double.NaN, nanDegrees, nanRadians)
    }

    private fun assertIsCloseTo(expected: Boolean, angle1: Angle, angle2: Angle) {
        println("Checking $angle1 isCloseTo $angle2")
        if (expected) {
            assertTrue(angle1.isCloseTo(angle2, 0.0, 3e-14), "expected $angle1 to be close to $angle2")
            assertTrue(angle1.isCloseTo(angle2, 3e-14, 0.0), "expected $angle1 to be close to $angle2")
        } else {
            assertFalse(angle1.isCloseTo(angle2, 0.0, 3e-14), "expected $angle1 to not be close to $angle2")
            assertFalse(angle1.isCloseTo(angle2, 3e-14, 0.0), "expected $angle1 to not be close to $angle2")
        }
    }

    @Test
    fun isExactlyCloseTo() {
        val angle1 = Angle.fromDegrees(0)
        val angle2 = Angle.fromRadians(0)
        assertTrue(angle1.isCloseTo(angle2), "expected $angle1 to be close to $angle2")
        assertTrue(angle2.isCloseTo(angle1), "expected $angle2 to be close to $angle1")
    }

    @Test
    fun isNaN() {
        assertTrue(Angle.fromDegrees(Double.NaN).isNaN())
        assertFalse(Angle.fromDegrees(Double.POSITIVE_INFINITY).isNaN())
        assertFalse(Angle.fromDegrees(Double.NEGATIVE_INFINITY).isNaN())
        assertFalse(Angle.fromDegrees(12.0).isNaN())
    }

    @Test
    fun isFinite() {
        assertFalse(Angle.fromDegrees(Double.NaN).isFinite())
        assertFalse(Angle.fromDegrees(Double.POSITIVE_INFINITY).isFinite())
        assertFalse(Angle.fromDegrees(Double.NEGATIVE_INFINITY).isFinite())
        assertTrue(Angle.fromDegrees(12.0).isFinite())
    }

    @Test
    fun isInfinite() {
        assertFalse(Angle.fromDegrees(Double.NaN).isInfinite())
        assertTrue(Angle.fromDegrees(Double.POSITIVE_INFINITY).isInfinite())
        assertTrue(Angle.fromDegrees(Double.NEGATIVE_INFINITY).isInfinite())
        assertFalse(Angle.fromDegrees(12.0).isInfinite())
    }

    @ParameterizedTest
    @ValueSource(doubles = [10.0, 180.0, 360.0] )
    fun equalityDegreesTest(angle: Double) {
        val testAngle1: Angle = Angle.fromDegrees(angle)
        val testAngle2: Angle = Angle.fromDegrees(angle)
        assertEquals(testAngle1, testAngle2)
        assertEquals(testAngle1.hashCode(), testAngle2.hashCode())
        assertNotSame(testAngle1, testAngle2)
    }

    @ParameterizedTest
    @ValueSource(doubles = [10.0, 180.0, 360.0] )
    fun equalityRadiansTest(angle: Double) {
        val testAngle1: Angle = Angle.fromRadians(angle)
        val testAngle2: Angle = Angle.fromRadians(angle)
        assertEquals(testAngle1, testAngle2)
        assertEquals(testAngle1.hashCode(), testAngle2.hashCode())
        assertNotSame(testAngle1, testAngle2)
    }

    @ParameterizedTest
    @ValueSource(doubles = [10.0, 180.0, 360.0] )
    fun equalityMixedTest(angle: Double) {
        val subtractMe: Angle = Angle.fromDegrees(10.0)
        val testAngle1: Angle = Angle.fromRadians(angle).minus(subtractMe)
        val testAngle2: Angle = Angle.fromRadians(angle).minus(subtractMe)
        assertEquals(testAngle1, testAngle2)
        assertEquals(testAngle1.hashCode(), testAngle2.hashCode())
        assertNotSame(testAngle1, testAngle2)
    }

    companion object {
        @JvmStatic
        fun provideValuesForIsCloseTo(): Stream<Arguments> {
            val nanDegrees = Angle.fromDegrees(Double.NaN)
            val nanRadians = Angle.fromRadians(Double.NaN)
            val infinityDegrees = Angle.fromDegrees(Double.POSITIVE_INFINITY)
            val infinityRadians = Angle.fromRadians(Double.POSITIVE_INFINITY)
            val negativeInfinityDegrees = Angle.fromDegrees(Double.NEGATIVE_INFINITY)
            val negativeInfinityRadians = Angle.fromRadians(Double.NEGATIVE_INFINITY)

            return (1..20).flatMap {
                val degrees1 = Angle.fromDegrees(Angle.ONE_TURN_DEGREES / it.toDouble())
                val radians1 = Angle.fromRadians(ONE_TURN_RADIANS / it.toDouble())
                val degrees2 = Angle.fromDegrees(Angle.ONE_TURN_DEGREES * (it + 1) / it.toDouble())
                val radians2 = Angle.fromRadians(ONE_TURN_RADIANS * (-it + 1) / it.toDouble())
                listOf(
                    Arguments.of(true, radians1, degrees1),
                    Arguments.of(false, radians1, degrees2),
                    Arguments.of(false, radians1, radians2),
                    Arguments.of(true, degrees1, radians1),
                    Arguments.of(false, degrees1, degrees2),
                    Arguments.of(false, degrees1, radians2),
                    Arguments.of(false, degrees1, infinityDegrees),
                    Arguments.of(false, degrees1, infinityRadians),
                    Arguments.of(false, degrees1, negativeInfinityDegrees),
                    Arguments.of(false, degrees1, negativeInfinityRadians),
                    Arguments.of(false, degrees1, nanDegrees),
                    Arguments.of(false, degrees1, nanRadians)
                )
            }.stream()
        }

        @JvmStatic
        fun provideValuesForIsEquivalentTo(): Stream<Arguments> {
            val nanDegrees = Angle.fromDegrees(Double.NaN)
            val nanRadians = Angle.fromRadians(Double.NaN)
            val infinityDegrees = Angle.fromDegrees(Double.POSITIVE_INFINITY)
            val infinityRadians = Angle.fromRadians(Double.POSITIVE_INFINITY)
            val negativeInfinityDegrees = Angle.fromDegrees(Double.NEGATIVE_INFINITY)
            val negativeInfinityRadians = Angle.fromRadians(Double.NEGATIVE_INFINITY)

            return (1..20).flatMap {
                val degrees1 = Angle.fromDegrees(Angle.ONE_TURN_DEGREES / it.toDouble())
                val radians1 = Angle.fromRadians(ONE_TURN_RADIANS / it.toDouble())
                val degrees2 = Angle.fromDegrees(Angle.ONE_TURN_DEGREES * (it + 1) / it.toDouble())
                val radians2 = Angle.fromRadians(ONE_TURN_RADIANS * (-it + 1) / it.toDouble())
                listOf(
                    Arguments.of(true, radians1, degrees1),
                    Arguments.of(true, radians1, degrees2),
                    Arguments.of(true, radians1, radians2),
                    Arguments.of(true, degrees1, radians1),
                    Arguments.of(true, degrees1, degrees2),
                    Arguments.of(true, degrees1, radians2),
                    Arguments.of(false, degrees1, infinityDegrees),
                    Arguments.of(false, degrees1, infinityRadians),
                    Arguments.of(false, degrees1, negativeInfinityDegrees),
                    Arguments.of(false, degrees1, negativeInfinityRadians),
                    Arguments.of(false, degrees1, nanDegrees),
                    Arguments.of(false, degrees1, nanRadians)
                )
            }.stream()
        }
    }
}