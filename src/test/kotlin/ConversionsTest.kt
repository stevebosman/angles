package uk.co.stevebosman.angles

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import kotlin.math.PI

class ConversionsTest {
    @Test
    fun radiansToDegrees() {
        Assertions.assertEquals(180.0, radiansToDegrees(PI))
        Assertions.assertEquals(0.0, radiansToDegrees(0))
        Assertions.assertEquals(-180.0, radiansToDegrees(-PI))
        Assertions.assertEquals(540.0, radiansToDegrees(3 * PI))
    }

    @Test
    fun degreesToRadians() {
        Assertions.assertEquals(PI, degreesToRadians(180))
        Assertions.assertEquals(0.0, degreesToRadians(0))
        Assertions.assertEquals(-PI, degreesToRadians(-180))
        Assertions.assertEquals(3 * PI, degreesToRadians(540))
    }
}