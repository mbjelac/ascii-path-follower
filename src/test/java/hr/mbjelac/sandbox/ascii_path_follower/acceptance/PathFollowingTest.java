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

    @Test
    public void map2() {

        givenMap(
                "@",
                "| C----+",
                "A |    |",
                "+---B--+",
                "  |      x",
                "  |      |",
                "  +---D--+"
        );

        whenFollowingPath();

        thenPathIs("@|A+---B--+|+----C|-||+---D--+|x");
        andLettersAre("ABCD");
    }

    @Test
    public void map3() {

        givenMap(
                "  @---+",
                "      B",
                "K-----|--A",
                "|     |  |",
                "|  +--E  |",
                "|  |     |",
                "+--E--Ex C",
                "   |     |",
                "   +--F--+"
        );

        whenFollowingPath();

        thenPathIs("@---+B||E--+|E|+--F--+|C|||A--|-----K|||+--E--Ex");
        andLettersAre("BEEFCAKE");
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
