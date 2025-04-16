package hr.mbjelac.sandbox.ascii_path_follower;

import lombok.Value;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DirectionFinderTest {

    private final DirectionFinder finder = new DirectionFinder();

    @Test
    public void throw_when_invalid_input() {

        throwsIllegalArgEx(null, null);
        throwsIllegalArgEx(AsciiMap.from(" ", " "), null);
        throwsIllegalArgEx(null, Coordinates.colRow(0, 0));
    }

    @Test
    public void throw_when_no_paths() {

        throwsIllegalArgEx(
                AsciiMap.from(
                        "   ",
                        " x ",
                        "   "),
                Coordinates.colRow(1, 1));
    }

    private void throwsIllegalArgEx(AsciiMap map, Coordinates coords) {

        assertThatThrownBy(() -> finder
                .findDirection(map, coords))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void return_directions_for_simple_cases() {

        assertDirection(
                AsciiMap.from(
                        " | ",
                        " x ",
                        "   "),
                Coordinates.colRow(1, 1))
                .isEqualTo(Direction.UP);
        assertDirection(
                AsciiMap.from(
                        "   ",
                        " x-",
                        "   "),
                Coordinates.colRow(1, 1))
                .isEqualTo(Direction.RIGHT);
        assertDirection(
                AsciiMap.from(
                        "   ",
                        " x ",
                        " | "),
                Coordinates.colRow(1, 1))
                .isEqualTo(Direction.DOWN);
        assertDirection(
                AsciiMap.from(
                        "   ",
                        "-x ",
                        "   "),
                Coordinates.colRow(1, 1))
                .isEqualTo(Direction.LEFT);
    }

    @Test
    public void return_directions_when_starting_on_map_edge() {

        assertDirection(
                AsciiMap.from(
                        "|  ",
                        "x  ",
                        "   "),
                Coordinates.colRow(0, 1)
        )
                .isEqualTo(Direction.UP);
        assertDirection(
                AsciiMap.from(
                        " x-",
                        "   ",
                        "   "),
                Coordinates.colRow(1, 0)
        )
                .isEqualTo(Direction.RIGHT);
        assertDirection(
                AsciiMap.from(
                        "   ",
                        "  x",
                        "  |"),
                Coordinates.colRow(2, 1)
        )
                .isEqualTo(Direction.DOWN);
        assertDirection(
                AsciiMap.from(
                        "   ",
                        "   ",
                        "-x "),
                Coordinates.colRow(1, 2)
        )
                .isEqualTo(Direction.LEFT);
    }



    private DirectionAssert assertDirection(AsciiMap map, Coordinates startingCoordinates) {

        return new DirectionAssert(map, startingCoordinates);
    }

    @Value
    private class DirectionAssert {

        AsciiMap map;
        Coordinates startingCoordinates;

        void isEqualTo(Direction expectedDirection) {

            assertThat(finder
                    .findDirection(map, startingCoordinates))
                    .isEqualTo(expectedDirection);
        }
    }
}