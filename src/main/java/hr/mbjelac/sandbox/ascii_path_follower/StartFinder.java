package hr.mbjelac.sandbox.ascii_path_follower;

import lombok.Value;
import lombok.val;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;

class StartFinder {

    private static final char STARTING_CHARACTER = '@';

    State findStart(AsciiMap map) {

        if (map == null) {
            throw new IllegalArgumentException("Map is null!");
        }

        if (countStartingCharacters(map) != 1) {
            throw new IllegalArgumentException(
                    "Map should contain exactly 1 starting character: " +
                            Arrays.deepToString(map.getRows()));
        }

        return Optional
                .ofNullable(
                        findInMap(
                                map,
                                c -> c == STARTING_CHARACTER))
                .map(coordinates -> State
                        .builder()
                        .x(coordinates.getColumn())
                        .y(coordinates.getRow())
                        .build())
                .orElseThrow(IllegalStateException::new);
    }

    private int countStartingCharacters(AsciiMap map) {

        return Arrays
                .stream(map.getRows())
                .mapToInt(row -> String
                        .valueOf(row)
                        .replaceAll("[^" + STARTING_CHARACTER + "]", "")
                        .length())
                .sum();
    }

    @Value
    private class Coordinates {
        int row, column;
    }

    private Coordinates findInMap(
            AsciiMap map,
            Predicate<Character> matcher) {

        for (int rowIndex = 0; rowIndex < map.getRows().length; rowIndex++) {

            val row = map.getRows()[rowIndex];

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
