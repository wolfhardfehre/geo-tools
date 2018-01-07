package nice.fontaine.geotools

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.Before
import org.junit.Test
import org.junit.internal.matchers.ThrowableMessageMatcher.hasMessage



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

    @Test fun `should throw exception when latitude is lower -90 degrees`() {
        assertThatThrownBy { Coordinate(-90.1, 0.0) }
                .hasMessage("Latitude is invalid!")
    }

    @Test fun `should throw exception when latitude is higher 90 degrees`() {
        assertThatThrownBy { Coordinate(90.1, 0.0) }
                .hasMessage("Latitude is invalid!")
    }

    @Test fun `should throw exception when longitude is lower -180 degrees`() {
        assertThatThrownBy { Coordinate(0.0, -180.1) }
                .hasMessage("Longitude is invalid!")
    }

    @Test fun `should throw exception when longitude is higher 180 degrees`() {
        assertThatThrownBy { Coordinate(0.0, 180.1) }
                .hasMessage("Longitude is invalid!")
    }
}