package hr.mbjelac.sandbox.ascii_path_follower;


import lombok.val;

import java.util.Arrays;

class StartFinder {

    private static final char STARTING_CHARACTER = '@';

    State findStart(AsciiMap map) {

        if (map == null) {
            throw new IllegalArgumentException("map is null!");
        }

        State state = null;

        for (int rowIndex = 0; rowIndex < map.getRows().length; rowIndex++) {

            val row = map.getRows()[rowIndex];

            for (int columnIndex = 0; columnIndex < row.length; columnIndex++) {

                val c = row[columnIndex];

                if (c == STARTING_CHARACTER) {

                    if (state != null) {
                        throw new IllegalArgumentException(
                                "Map has multiple starting characters: " +
                                        mapToString(map));
                    }

                    state = State
                            .builder()
                            .x(columnIndex)
                            .y(rowIndex)
                            .build();
                }
            }
        }

        if (state == null) {
            throw new IllegalArgumentException(
                    "Map missing starting character: " + mapToString(map));
        }

        return state;
    }

    private String mapToString(AsciiMap map) {

        return Arrays.deepToString(map.getRows());
    }

}
