import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import uk.co.stevebosman.angles.Angle
import uk.co.stevebosman.angles.simplifyExample1
import uk.co.stevebosman.angles.simplifyExample2
import uk.co.stevebosman.angles.simplifyExample3

class SamplesKtTest {

    @Test
    fun testSimplifyExample1() {
        assertEquals(Angle.fromDegrees(180.0), simplifyExample1())
    }

    @Test
    fun testSimplifyExample2() {
        assertEquals(Angle.fromDegrees(90.0), simplifyExample2())
    }

    @Test
    fun testSimplifyExample3() {
        assertEquals(Angle.fromRadians(-Math.PI), simplifyExample3())
    }
}