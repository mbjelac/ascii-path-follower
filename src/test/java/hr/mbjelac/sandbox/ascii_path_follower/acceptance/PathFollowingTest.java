package hr.mbjelac.sandbox.ascii_path_follower.acceptance;

import org.junit.Test;

public class PathFollowingTest {

    @Test
    public void map1() {

        givenMap("" +
                "@---A---+\n" +
                "        |\n" +
                "x-B-+   C\n" +
                "    |   |\n" +
                "    +---+");

        whenFollowingPath();

        thenPathIs("@---A---+|C|+---+|+-B-x");
        andLettersAre("ACB");
    }

    private void givenMap(String asciiMap) {

    }

    private void whenFollowingPath() {

    }

    private void thenPathIs(String expectedPath) {

    }

    private void andLettersAre(String expectedLetters) {

    }
}
