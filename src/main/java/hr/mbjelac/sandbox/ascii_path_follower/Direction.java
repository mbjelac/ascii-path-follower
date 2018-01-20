package hr.mbjelac.sandbox.ascii_path_follower;

enum Direction {

    UP('|') {
        @Override
        Coordinates move(Coordinates oldCoordinates) {
            return Coordinates.colRow(oldCoordinates.getColumn(), oldCoordinates.getRow() - 1);
        }
    },
    DOWN('|') {
        @Override
        Coordinates move(Coordinates oldCoordinates) {
            return Coordinates.colRow(oldCoordinates.getColumn(), oldCoordinates.getRow() + 1);
        }
    },
    LEFT('-') {
        @Override
        Coordinates move(Coordinates oldCoordinates) {
            return Coordinates.colRow(oldCoordinates.getColumn() - 1, oldCoordinates.getRow());
        }
    },
    RIGHT('-') {
        @Override
        Coordinates move(Coordinates oldCoordinates) {
            return Coordinates.colRow(oldCoordinates.getColumn() + 1, oldCoordinates.getRow());
        }
    };

    public final char path;

    Direction(char path) {
        this.path = path;
    }

    abstract Coordinates move(Coordinates oldCoordinates);

}
