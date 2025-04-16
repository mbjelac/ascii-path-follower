package hr.mbjelac.sandbox.ascii_path_follower;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

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

        assertThat(finder.findDirection(
                AsciiMap.from(
                        " | ",
                        " x ",
                        "   "),
                Coordinates.colRow(1, 1)))
                .isEqualTo(Direction.UP);

        assertThat(finder.findDirection(
                AsciiMap.from(
                        "   ",
                        " x-",
                        "   "),
                Coordinates.colRow(1, 1)))
                .isEqualTo(Direction.RIGHT);

        assertThat(finder.findDirection(
                AsciiMap.from(
                        "   ",
                        " x ",
                        " | "),
                Coordinates.colRow(1, 1)))
                .isEqualTo(Direction.DOWN);

        assertThat(finder.findDirection(
                AsciiMap.from(
                        "   ",
                        "-x ",
                        "   "),
                Coordinates.colRow(1, 1)))
                .isEqualTo(Direction.LEFT);
    }

    enum DirectionExample {

        LEFT_EDGE_UP(
                new String[]{
                        "|  ",
                        "x  ",
                        "   "
                },
                Coordinates.colRow(0, 1),
                Direction.UP
        ),

        TOP_EDGE_RIGHT(
                new String[]{
                        " x-",
                        "   ",
                        "   "
                },
                Coordinates.colRow(1, 0),
                Direction.RIGHT
        ),

        RIGHT_EDGE_DOWN(
                new String[]{
                        "   ",
                        "  x",
                        "  |"
                },
                Coordinates.colRow(2, 1),
                Direction.DOWN
        ),

        BOTTOM_EDGE_LEFT(
                new String[]{
                        "   ",
                        "   ",
                        "-x "
                },
                Coordinates.colRow(1, 2),
                Direction.LEFT
        );

        final String[] mapRows;
        final Coordinates startingPoint;
        final Direction expectedDirection;

        DirectionExample(String[] mapRows, Coordinates startingPoint, Direction expectedDirection) {
            this.mapRows = mapRows;
            this.startingPoint = startingPoint;
            this.expectedDirection = expectedDirection;
        }
    }

    @ParameterizedTest
    @EnumSource(DirectionExample.class)
    public void return_directions_when_starting_on_map_edge(DirectionExample example) {
        assertThat(finder.findDirection(
                AsciiMap.from(example.mapRows),
                example.startingPoint
        ))
                .isEqualTo(example.expectedDirection);
    }
}
