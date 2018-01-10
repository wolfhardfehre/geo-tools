package nice.fontaine.geotools

import kotlin.math.*

const val EARTH_RADIUS = 6_371_008.8

/**
 * Computes distance in meters between two Coordinates using haversine formula.
 *
 * @param   from Coordinate
 * @param   to Coordinate
 * @return  havDistance in meters
 *
 * @example
 *     val from = Coordinate(52.205, 0.119)
 *     val to = Coordinate(48.857, 2.351)
 *     val d = meters(from, to)              // ~ 404.3 km
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
 * Computes haversine distance between two Coordinates.
 *
 * @param   from Coordinate
 * @param   to Coordinate
 * @return  haversine distance
 */
private fun havDistance(from: Coordinate, to: Coordinate):Double {
    val fromLat = from.lat.rad()
    val toLat = to.lat.rad()
    val delta = deltaLon(from.lon, to.lon)
    return hav(delta(fromLat, toLat)) + cos(fromLat) * cos(toLat) * hav(delta)
}

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
 * Computes distance approximation between coordinates using Pythagoras’ theorem on an equi­rectangular projection.
 * Should be used if performance is an issue and accuracy less important, for small distances.
 *
 * @param   from Coordinate
 * @param   to Coordinate
 * @return  havDistance in meters
 */
fun approx(from: Coordinate, to: Coordinate): Double {
    val fromLat = from.lat.rad()
    val toLat = to.lat.rad()
    val delta = deltaLon(from.lon, to.lon)
    val x = delta * cos((fromLat + toLat) / 2)
    val y = delta(fromLat, toLat)
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

/**
 * Returns the (initial) bearing from origin to destination coordinate.
 * Initial bearing (also: forward azimuth) which if followed in a straight line
 * along a great-circle arc (also: orthodrome) will take you from the start point to the end point.
 *
 * @param   from Coordinate
 * @param   to Coordinate
 * @return  Initial bearing in degrees from north.
 *
 * @example
 *     val from = Coordinate(52.205, 0.119)
 *     val to = Coordinate(48.857, 2.351)
 *     val b = bearing(from, to)            // ~ 156.2°
 */
fun bearing(from: Coordinate, to: Coordinate): Double {
    val fromLat = from.lat.rad()
    val toLat = to.lat.rad()
    val delta = deltaLon(from.lon, to.lon)
    val y = sin(delta) * cos(toLat)
    val x = cos(fromLat) * sin(toLat) - sin(fromLat) * cos(toLat) * cos(delta)
    return (atan2(y, x).deg() + 360) % 360
}

/**
 * Returns final bearing arriving at destination from origin. The final bearing
 * will differ from the initial bearing by varying degrees according to distance and latitude.
 *
 * @param   from Coordinate
 * @param   to Coordinate
 * @return Final bearing in degrees from north.
 *
 * @example
 *     val from = Coordinate(52.205, 0.119)
 *     val to = Coordinate(48.857, 2.351)
 *     val b = finalBearing(from, to)       // ~ 157.9°
 */
fun finalBearing(from: Coordinate, to: Coordinate): Double {
    return (bearing(to, from) + 180) % 360
}

/**
 * Half-way point along a great circle path between the two points.
 *
 * @param   from Coordinate
 * @param   to Coordinate
 * @return  Coordinate of midpoint
 *
 * @example
 *     val from = Coordinate(52.205, 0.119)
 *     val to = Coordinate(48.857, 2.351)
 *     val mid = midpoint(from, to)        // ~ 50.5363°N 1.2746°E
 */
fun midpoint(from: Coordinate, to: Coordinate): Coordinate {
    val fromLat = from.lat.rad()
    val toLat = to.lat.rad()
    val delta = deltaLon(from.lon, to.lon)
    val cosToLat = cos(toLat)
    val by = cosToLat * sin(delta)
    val y = sin(fromLat) + sin(toLat)
    val cosFromLatBx = cos(fromLat) + cosToLat * cos(delta)
    val lat = atan2(y, pythagoras(cosFromLatBx, by)).deg()
    val lon = (from.lon.rad() + atan2(by, cosFromLatBx)).deg()
    return Coordinate(lat, (lon + 540) % 360 - 180)
}

/**
 * Converts degrees to radians.
 *
 * @return radians
 */
fun Double.rad() = this / 180.0 * PI

/**
 * Converts radians to degrees.
 *
 * @return degrees
 */
fun Double.deg() = this * 180.0 / PI

private fun delta(from: Double, to: Double) = to - from

private fun deltaLon(from: Double, to: Double) = (to - from).rad()


