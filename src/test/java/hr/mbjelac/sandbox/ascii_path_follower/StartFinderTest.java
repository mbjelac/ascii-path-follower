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
    public void return_correct_state_when_starting_character_found() {

        assertState(
                map(
                        "...",
                        ".@.",
                        "..."),
                state(1, 1));

        assertState(
                map(
                        "...",
                        "...",
                        "..@"),
                state(2, 2));

        assertState(
                map(
                        "..@",
                        "...",
                        "..."),
                state(2, 0));
    }


    private void assertState(AsciiMap map, State expectedState) {

        assertThat(finder
                .findStart(map))
                .isEqualTo(expectedState);

    }

    private AsciiMap map(String... rows) {

        return AsciiMap.fromStrings(rows);
    }

    private State state(int x, int y) {

        return State
                .builder()
                .x(x)
                .y(y)
                .build();
    }
}