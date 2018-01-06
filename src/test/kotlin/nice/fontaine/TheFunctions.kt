package nice.fontaine

import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

class TheFunctions {

    private lateinit var from: Coordinate
    private lateinit var to: Coordinate

    @Before fun setup() {
        from = Coordinate(1.0, 0.0)
        to = Coordinate(2.0, 0.0)
    }

    @Test fun `should compute distance in meters between two coordinates`() {
        val actual = meters(from, to)

        assertThat(actual).isEqualTo(111195.0802335329)
    }
}