package nice.fontaine

import kotlin.math.asin
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

const val EARTH_RADIUS = 6_371_008.8

/**
 * Computes distance between two Coordinates in meters.
 *
 * @param   from Coordinate
 * @param   to Coordinate
 * @return  distance in meters
 */
fun meters(from: Coordinate, to: Coordinate): Double = EARTH_RADIUS * angle(from, to)

/**
 * Computes angle between two Coordinates in radians.
 *
 * @param   from Coordinate
 * @param   to Coordinate
 * @return  angle in radians
 */
fun angle(from: Coordinate, to: Coordinate): Double = 2 * asin(sqrt(distance(from, to)))

/**
 * Computes haversine distance between two Coordinates.
 *
 * @param   from Coordinate
 * @param   to Coordinate
 * @return  haversine distance
 */
fun distance(from: Coordinate, to: Coordinate): Double = hav(to.latRad() - from.latRad()) +
            cos(from.latRad()) * cos(to.latRad()) * hav(to.lonRad() - from.lonRad())

/**
 * Computes the haversine of a given value.
 *
 * @param   x Double
 * @return  haversine of x
 */
fun hav(x: Double): Double {
    val half = sin(x * 0.5)
    return half * half
}
