package hr.mbjelac.sandbox.ascii_path_follower;


import java.util.Arrays;

class DirectionFinder {

    public Direction findDirection(AsciiMap map, Coordinates startingCoordinates) {

        if (map == null || startingCoordinates == null) {
            throw new IllegalArgumentException(
                    "Map and coordinates must not be null! " +
                            "Map:" + map + ", " +
                            "Start: " + startingCoordinates);
        }

        return Arrays
                .stream(Direction.values())
                .filter(direction -> {
                    Coordinates nextCoords = direction.move(startingCoordinates);
                    return nextCoords.isWithinBounds(map) && 
                           map.get(nextCoords) == direction.path;
                })
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Failed to find starting path! " +
                                        "Map: " + map + ", " +
                                        "Start: " + startingCoordinates));
    }

}
