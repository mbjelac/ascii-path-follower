package hr.mbjelac.sandbox.ascii_path_follower;

import lombok.Value;
import org.junit.Test;

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

        findFor(
                AsciiMap.from(
                        " | ",
                        " x ",
                        "   "),
                Coordinates.colRow(1, 1))
                .direction(Direction.UP);
        findFor(
                AsciiMap.from(
                        "   ",
                        " x-",
                        "   "),
                Coordinates.colRow(1, 1))
                .direction(Direction.RIGHT);
        findFor(
                AsciiMap.from(
                        "   ",
                        " x ",
                        " | "),
                Coordinates.colRow(1, 1))
                .direction(Direction.DOWN);
        findFor(
                AsciiMap.from(
                        "   ",
                        "-x ",
                        "   "),
                Coordinates.colRow(1, 1))
                .direction(Direction.LEFT);
    }

    @Test
    public void return_directions_when_starting_on_map_edge() {

        findFor(
                AsciiMap.from(
                        "|  ",
                        "x  ",
                        "   "),
                Coordinates.colRow(0, 1))
                .direction(Direction.UP);
        findFor(
                AsciiMap.from(
                        " x-",
                        "   ",
                        "   "),
                Coordinates.colRow(1, 0))
                .direction(Direction.RIGHT);
        findFor(
                AsciiMap.from(
                        "   ",
                        "  x",
                        "  |"),
                Coordinates.colRow(2, 1))
                .direction(Direction.DOWN);
        findFor(
                AsciiMap.from(
                        "   ",
                        "   ",
                        "-x "),
                Coordinates.colRow(1, 2))
                .direction(Direction.LEFT);
    }



    private DirectionAssert findFor(AsciiMap map, Coordinates startingCoordinates) {

        return new DirectionAssert(map, startingCoordinates);
    }

    @Value
    private class DirectionAssert {

        AsciiMap map;
        Coordinates startingCoordinates;

        void direction(Direction expectedDirection) {

            assertThat(finder
                    .findDirection(map, startingCoordinates))
                    .isEqualTo(expectedDirection);
        }
    }
}