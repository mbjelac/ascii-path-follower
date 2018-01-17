package hr.mbjelac.sandbox.ascii_path_follower.acceptance;

import hr.mbjelac.sandbox.ascii_path_follower.AsciiMap;
import hr.mbjelac.sandbox.ascii_path_follower.AsciiPathFollower;
import hr.mbjelac.sandbox.ascii_path_follower.FollowedPath;

import static org.assertj.core.api.Assertions.assertThat;

abstract class AcceptanceTest {

    private final AsciiPathFollower follower = new AsciiPathFollower();

    private AsciiMap map;
    private FollowedPath path;

    protected final void givenMap(String... asciiMapRows) {

        map = new AsciiMap(asciiMapRows);
    }

    protected final void whenFollowingPath() {

        path = follower.follow(map);
    }

    protected final void thenPathIs(String expectedPath) {

        assertThat(path.getCharacters()).isEqualTo(expectedPath);
    }

    protected final void andLettersAre(String expectedLetters) {

        assertThat(path.getLetters()).isEqualTo(expectedLetters);
    }

}
