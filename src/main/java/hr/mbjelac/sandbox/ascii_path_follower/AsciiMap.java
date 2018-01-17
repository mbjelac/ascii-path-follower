package hr.mbjelac.sandbox.ascii_path_follower;

import lombok.Value;

import java.util.Arrays;

@Value
public class AsciiMap {

    char[][] rows;

    public static AsciiMap fromStrings(String... strings) {

        return new AsciiMap(
                Arrays.stream(strings)
                        .map(String::toCharArray)
                        .toArray(char[][]::new));
    }
}
