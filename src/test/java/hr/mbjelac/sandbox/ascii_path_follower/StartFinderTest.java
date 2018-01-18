package hr.mbjelac.sandbox.ascii_path_follower;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StartFinderTest {

    private StartFinder finder = new StartFinder();

    @Test
    public void throw_when_null_map() {

        throwsIllegalArgEx(null);
    }

    @Test
    public void throw_when_map_missing_start_character() {

        throwsIllegalArgEx(map(" a b c "));
        throwsIllegalArgEx(
                map(
                        "...",
                        "...",
                        "..."));
    }

    @Test
    public void throw_when_map_has_multiple_starting_characters() {

        throwsIllegalArgEx(map("@@"));
        throwsIllegalArgEx(map("@@@"));
        throwsIllegalArgEx(
                map(
                        "@.",
                        ".@"));
    }

    private void throwsIllegalArgEx(AsciiMap map) {

        Assertions.assertThatThrownBy(() -> finder
                .findStart(map))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void return_coordinates_of_starting_character() {

        assertFoundCoords(
                map(
                        "...",
                        ".@.",
                        "..."),
                xy(1, 1));

        assertFoundCoords(
                map(
                        "...",
                        "...",
                        "..@"),
                xy(2, 2));

        assertFoundCoords(
                map(
                        "..@",
                        "...",
                        "..."),
                xy(2, 0));
    }


    private void assertFoundCoords(AsciiMap map, Coordinates expectedCoordinates) {

        assertThat(finder
                .findStart(map))
                .isEqualTo(expectedCoordinates);

    }

    private AsciiMap map(String... rows) {

        return AsciiMap.fromStrings(rows);
    }

    private Coordinates xy(int x, int y) {

        return Coordinates
                .builder()
                .column(x)
                .row(y)
                .build();
    }
}