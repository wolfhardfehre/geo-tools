package nice.fontaine.geotools

import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

class TheCoordinate {

    private var lat:Double = 0.0
    private var lon:Double = 0.0

    @Before fun setup() {
        lat = 52.0
        lon = 13.0
    }

    @Test fun `should contain given coordinates`() {
        val coordinate = Coordinate(lat, lon)

        assertThat(coordinate.lat).isEqualTo(lat)
        assertThat(coordinate.lon).isEqualTo(lon)
    }

    @Test fun `should provide given coordinates as radians`() {
        val coordinate = Coordinate(lat, lon)

        assertThat(coordinate.latRad()).isEqualTo(Math.toRadians(lat))
        assertThat(coordinate.lonRad()).isEqualTo(Math.toRadians(lon))
    }

    @Test fun `should compute distance to other coordinate in meters`() {
        val from = Coordinate(1.0, 0.0)
        val to = Coordinate(2.0, 0.0)

        val actual = from.metersTo(to)

        assertThat(actual).isEqualTo(111195.0802335329)
    }

    @Test fun `should be equal when latitude and longitude are the same`() {
        val from = Coordinate(lat, lon)
        val to = Coordinate(lat, lon)

        assertThat(from == to).isTrue()
    }
}