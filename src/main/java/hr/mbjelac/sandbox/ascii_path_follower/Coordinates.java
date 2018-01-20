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
}
