package nice.fontaine.geotools

data class Coordinate(val lat: Double, val lon: Double) {

    init { validate() }

    fun latRad(): Double = Math.toRadians(lat)

    fun lonRad(): Double = Math.toRadians(lon)

    fun metersTo(other: Coordinate): Double = meters(this, other)

    private fun validate() {
        if (lat <= -90.0) throw IllegalArgumentException("Latitude is invalid!")
        if (lat >= 90.0) throw IllegalArgumentException("Latitude is invalid!")
        if (lon <= -180.0) throw IllegalArgumentException("Longitude is invalid!")
        if (lon >= 180.0) throw IllegalArgumentException("Longitude is invalid!")
    }
}