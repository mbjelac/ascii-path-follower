package hr.mbjelac.sandbox.ascii_path_follower;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
class Coordinates {

    int row, column;

    public static Coordinates colRow(int column, int row) {
        return new Coordinates(row, column);
    }

    public boolean isWithinBounds(AsciiMap map) {
        return row >= 0 && 
               row < map.getCells().length &&
               column >= 0 && 
               column < map.getCells()[0].length;
    }
}
