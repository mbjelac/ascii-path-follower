package hr.mbjelac.sandbox.ascii_path_follower;


import lombok.val;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

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

        return crawlOverMap(
                map,
                (c, rowIndex, columnIndex) -> {

                    if (c == STARTING_CHARACTER) {

                        return State
                                .builder()
                                .x(columnIndex)
                                .y(rowIndex)
                                .build();
                    }

                    return null;
                });
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

    private interface MapListener<T> {

        T onElement(char element, int rowIndex, int columnIndex);

    }

    private <T> T crawlOverMap(AsciiMap map, MapListener<T> listener) {

        for (int rowIndex = 0; rowIndex < map.getRows().length; rowIndex++) {

            val row = map.getRows()[rowIndex];

            for (int columnIndex = 0; columnIndex < row.length; columnIndex++) {

                val c = row[columnIndex];

                T t = listener.onElement(c, rowIndex, columnIndex);

                if (t != null) {
                    return t;
                }
            }
        }

        throw new IllegalStateException();
    }
}
