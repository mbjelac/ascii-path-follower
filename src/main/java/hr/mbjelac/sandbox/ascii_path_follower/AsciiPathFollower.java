package hr.mbjelac.sandbox.ascii_path_follower;

import lombok.RequiredArgsConstructor;
import lombok.val;

@RequiredArgsConstructor
public class AsciiPathFollower {

    private final StartFinder startFinder;
    private final DirectionFinder directionFinder;
    private final Follower follower;
    private final LetterExtractor letterExtractor;

    public FollowedPath follow(AsciiMap map) {

        val startingCoordinates = startFinder.findStart(map);

        val startingDirection = directionFinder.findDirection(map, startingCoordinates);

        val initialState = State.builder()
                .location(startingCoordinates)
                .direction(startingDirection)
                .build();

        val pathCharacters = follower.follow(initialState, map);

        val letters = letterExtractor.extract(pathCharacters);

        return new FollowedPath(pathCharacters, letters);
    }
}
