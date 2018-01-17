package hr.mbjelac.sandbox.ascii_path_follower;


import lombok.val;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

class StartFinder {

    private static final char STARTING_CHARACTER = '@';

    State findStart(AsciiMap map) {

        if (map == null) {
            throw new IllegalArgumentException("map is null!");
        }

        final AtomicReference<State> state = new AtomicReference<>(null);

        crawlOverMap(
                map,
                (c, rowIndex, columnIndex) -> {

                    if (c == STARTING_CHARACTER) {

                        if (state.get() != null) {
                            throw new IllegalArgumentException(
                                    "Map has multiple starting characters: " +
                                            mapToString(map));
                        }

                        state.set(State
                                .builder()
                                .x(columnIndex)
                                .y(rowIndex)
                                .build());
                    }

                });

        if (state.get() == null) {
            throw new IllegalArgumentException(
                    "Map missing starting character: " + mapToString(map));
        }

        return state.get();
    }

    private interface MapListener {

        void onElement(char element, int rowIndex, int columnIndex);
    }

    private void crawlOverMap(AsciiMap map, MapListener listener) {

        for (int rowIndex = 0; rowIndex < map.getRows().length; rowIndex++) {

            val row = map.getRows()[rowIndex];

            for (int columnIndex = 0; columnIndex < row.length; columnIndex++) {

                val c = row[columnIndex];

                listener.onElement(c, rowIndex, columnIndex);
            }
        }
    }

    private String mapToString(AsciiMap map) {

        return Arrays.deepToString(map.getRows());
    }

}
