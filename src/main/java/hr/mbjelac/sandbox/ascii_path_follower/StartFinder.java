package hr.mbjelac.sandbox.ascii_path_follower;

import lombok.val;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;

class StartFinder {

    private static final char STARTING_CHARACTER = '@';

    Coordinates findStart(AsciiMap map) {

        if (map == null) {
            throw new IllegalArgumentException("Map is null!");
        }

        if (countStartingCharacters(map) != 1) {
            throw new IllegalArgumentException(
                    "Map should contain exactly 1 starting character: " +
                            Arrays.deepToString(map.getCells()));
        }

        return Optional
                .ofNullable(
                        findInMap(
                                map,
                                c -> c == STARTING_CHARACTER))
                .orElseThrow(IllegalStateException::new);
    }

    private int countStartingCharacters(AsciiMap map) {

        return Arrays
                .stream(map.getCells())
                .mapToInt(row -> String
                        .valueOf(row)
                        .replaceAll("[^" + STARTING_CHARACTER + "]", "")
                        .length())
                .sum();
    }

    private Coordinates findInMap(
            AsciiMap map,
            Predicate<Character> matcher) {

        for (int rowIndex = 0; rowIndex < map.getCells().length; rowIndex++) {

            val row = map.getCells()[rowIndex];

            for (int columnIndex = 0; columnIndex < row.length; columnIndex++) {

                val c = row[columnIndex];

                if (matcher.test(c)) {
                    return new Coordinates(rowIndex, columnIndex);
                }
            }
        }

        return null;
    }
}
