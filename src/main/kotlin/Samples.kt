package uk.co.stevebosman.angles

import kotlin.math.PI

internal fun simplifyExample1() {
    // Simplifies to 180°
    Angle.fromDegrees(540).simplify()
}

internal fun simplifyExample2() {
    // Simplifies to 180°
    Angle.fromDegrees(540).simplify(-180, false)
}

internal fun simplifyExample3() {
    // Simplifies to -π radians
    Angle.fromRadians(3*PI).simplify(-PI, true)
}