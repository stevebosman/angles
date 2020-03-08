package uk.co.stevebosman.angles

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import uk.co.stevebosman.angles.Angle.Companion.ONE_TURN_RADIANS
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

    @Test
    fun isEquivalentTo() {
        val nanDegrees = Angle.fromDegrees(Double.NaN)
        val nanRadians = Angle.fromRadians(Double.NaN)
        val infinityDegrees = Angle.fromDegrees(Double.POSITIVE_INFINITY)
        val infinityRadians = Angle.fromRadians(Double.POSITIVE_INFINITY)
        val negativeInfinityDegrees = Angle.fromDegrees(Double.NEGATIVE_INFINITY)
        val negativeInfinityRadians = Angle.fromRadians(Double.NEGATIVE_INFINITY)
        for (i in 1..20) {
            val degrees1 = Angle.fromDegrees(Angle.ONE_TURN_DEGREES / i.toDouble())
            val radians1 = Angle.fromRadians(ONE_TURN_RADIANS / i.toDouble())
            val degrees2 = Angle.fromDegrees(Angle.ONE_TURN_DEGREES * (i+1)  / i.toDouble())
            val radians2 = Angle.fromRadians (ONE_TURN_RADIANS * (-i+1) / i.toDouble())
            assertIsEquivalentTo(true, radians1, degrees1)
            assertIsEquivalentTo(true, radians1, degrees2)
            assertIsEquivalentTo(true, radians1, radians2)
            assertIsEquivalentTo(true, degrees1, radians1)
            assertIsEquivalentTo(true, degrees1, degrees2)
            assertIsEquivalentTo(true, degrees1, radians2)
            assertIsEquivalentTo(false, degrees1, infinityDegrees)
            assertIsEquivalentTo(false, degrees1, infinityRadians)
            assertIsEquivalentTo(false, degrees1, negativeInfinityDegrees)
            assertIsEquivalentTo(false, degrees1, negativeInfinityRadians)
            assertIsEquivalentTo(false, degrees1, nanDegrees)
            assertIsEquivalentTo(false, degrees1, nanRadians)
        }
        assertIsEquivalentTo(Double.POSITIVE_INFINITY == Double.POSITIVE_INFINITY, infinityRadians, infinityDegrees)
        assertIsEquivalentTo(Double.NEGATIVE_INFINITY == Double.NEGATIVE_INFINITY, negativeInfinityDegrees, negativeInfinityRadians)
        assertIsEquivalentTo(Double.NaN == Double.NaN, nanDegrees, nanRadians)
    }

    private fun assertIsEquivalentTo(expected:Boolean, angle1: Angle, angle2: Angle) {
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
    fun isCloseTo() {
        val nanDegrees = Angle.fromDegrees(Double.NaN)
        val nanRadians = Angle.fromRadians(Double.NaN)
        val infinityDegrees = Angle.fromDegrees(Double.POSITIVE_INFINITY)
        val infinityRadians = Angle.fromRadians(Double.POSITIVE_INFINITY)
        val negativeInfinityDegrees = Angle.fromDegrees(Double.NEGATIVE_INFINITY)
        val negativeInfinityRadians = Angle.fromRadians(Double.NEGATIVE_INFINITY)
        for (i in 1..20) {
            val degrees1 = Angle.fromDegrees(Angle.ONE_TURN_DEGREES / i.toDouble())
            val radians1 = Angle.fromRadians(ONE_TURN_RADIANS / i.toDouble())
            val degrees2 = Angle.fromDegrees(Angle.ONE_TURN_DEGREES * (i+1)  / i.toDouble())
            val radians2 = Angle.fromRadians (ONE_TURN_RADIANS * (-i+1) / i.toDouble())
            assertIsCloseTo(true, radians1, degrees1)
            assertIsCloseTo(false, radians1, degrees2)
            assertIsCloseTo(false, radians1, radians2)
            assertIsCloseTo(true, degrees1, radians1)
            assertIsCloseTo(false, degrees1, degrees2)
            assertIsCloseTo(false, degrees1, radians2)
            assertIsCloseTo(false, degrees1, infinityDegrees)
            assertIsCloseTo(false, degrees1, infinityRadians)
            assertIsCloseTo(false, degrees1, negativeInfinityDegrees)
            assertIsCloseTo(false, degrees1, negativeInfinityRadians)
            assertIsCloseTo(false, degrees1, nanDegrees)
            assertIsCloseTo(false, degrees1, nanRadians)
        }
        assertIsCloseTo(Double.POSITIVE_INFINITY == Double.POSITIVE_INFINITY, infinityRadians, infinityDegrees)
        assertIsCloseTo(Double.NEGATIVE_INFINITY == Double.NEGATIVE_INFINITY, negativeInfinityDegrees, negativeInfinityRadians)
        assertIsCloseTo(Double.NaN == Double.NaN, nanDegrees, nanRadians)
    }

    private fun assertIsCloseTo(expected:Boolean, angle1: Angle, angle2: Angle) {
        println("Checking $angle1 isCloseTo $angle2")
        if (expected) {
            assertTrue(angle1.isCloseTo(angle2, 0.0, 3e-14), "expected $angle1 to be equivalent to $angle2")
            assertTrue(angle1.isCloseTo(angle2, 3e-14, 0.0), "expected $angle1 to be equivalent to $angle2")
        } else {
            assertFalse(angle1.isCloseTo(angle2, 0.0, 3e-14), "expected $angle1 to not be equivalent to $angle2")
            assertFalse(angle1.isCloseTo(angle2, 3e-14, 0.0), "expected $angle1 to not be equivalent to $angle2")
        }
    }
}