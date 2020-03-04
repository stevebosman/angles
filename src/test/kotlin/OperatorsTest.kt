package uk.co.stevebosman.angles

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import kotlin.math.PI

class OperatorsTest {
    @Test
    fun timesRadians() {
        val test = 3 * Angle.fromRadians(PI)
        Assertions.assertEquals(Angle.Accuracy.RADIANS, test.accuracy)
        Assertions.assertEquals(PI * 3, test.radians)
        Assertions.assertEquals(540.0, test.degrees)
    }

    @Test
    fun timesDegrees() {
        val test = 3 * Angle.fromDegrees(180)
        Assertions.assertEquals(Angle.Accuracy.DEGREES, test.accuracy)
        Assertions.assertEquals(PI * 3, test.radians)
        Assertions.assertEquals(540.0, test.degrees)
    }

}