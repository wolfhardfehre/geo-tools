package nice.fontaine.geotools

import kotlin.math.asin
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

const val EARTH_RADIUS = 6_371_008.8

/**
 * Computes havDistance between two Coordinates in meters using haversine.
 *
 * @param   from Coordinate
 * @param   to Coordinate
 * @return  havDistance in meters
 */
fun meters(from: Coordinate, to: Coordinate) = EARTH_RADIUS * angle(from, to)

/**
 * Computes angle between two Coordinates in radians.
 *
 * @param   from Coordinate
 * @param   to Coordinate
 * @return  angle in radians
 */
fun angle(from: Coordinate, to: Coordinate) = 2 * asin(sqrt(havDistance(from, to)))

/**
 * Computes haversine havDistance between two Coordinates.
 *
 * @param   from Coordinate
 * @param   to Coordinate
 * @return  haversine havDistance
 */
fun havDistance(from: Coordinate, to: Coordinate) = hav(to.latRad() - from.latRad()) +
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

/**
 * Computes havDistance between coordinates using Pythagoras’ theorem on an equi­rectangular projection.
 * Should be used if performance is an issue and accuracy less important, for small distances.
 *
 * @param   from Coordinate
 * @param   to Coordinate
 * @return  havDistance in meters
 */
fun metersApprox(from: Coordinate, to: Coordinate): Double {
    val x = (to.lonRad() - from.lonRad()) * cos((from.latRad() + to.latRad()) / 2)
    val y = to.latRad() - from.latRad()
    return EARTH_RADIUS * pythagoras(x, y)
}

/**
 * Computes havDistance between x and y value in cartesian coordinate system (Pythagoras´ Theorem).
 *
 * @param   x Double
 * @param   y Double
 * @return  havDistance between coordinates
 */
fun pythagoras(x: Double, y: Double) = sqrt(x * x + y * y)