package hr.mbjelac.sandbox.ascii_path_follower.acceptance;

import org.junit.Test;

public class PathFollowingTest {

    @Test
    public void map1() {

        givenMap(
                "@---A---+",
                "        |",
                "x-B-+   C",
                "    |   |",
                "    +---+"
        );

        whenFollowingPath();

        thenPathIs("@---A---+|C|+---+|+-B-x");
        andLettersAre("ACB");
    }

    private void givenMap(String... asciiMapRows) {

    }

    private void whenFollowingPath() {

    }

    private void thenPathIs(String expectedPath) {

    }

    private void andLettersAre(String expectedLetters) {

    }
}
