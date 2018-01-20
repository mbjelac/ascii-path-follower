package hr.mbjelac.sandbox.ascii_path_follower;

import lombok.Value;

import java.util.Arrays;

@Value
public class AsciiMap {

    char[][] cells;

    public static AsciiMap from(String... strings) {

        return new AsciiMap(
                Arrays.stream(strings)
                        .map(String::toCharArray)
                        .toArray(char[][]::new));
    }

    public char get(Coordinates coordinates) {

        return cells[coordinates.getRow()][coordinates.getColumn()];
    }
}
