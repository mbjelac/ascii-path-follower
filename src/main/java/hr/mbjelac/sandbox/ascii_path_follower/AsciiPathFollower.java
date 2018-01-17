package hr.mbjelac.sandbox.ascii_path_follower;

import lombok.RequiredArgsConstructor;
import lombok.val;

@RequiredArgsConstructor
public class AsciiPathFollower {

    private final StartFinder startFinder;
    private final Follower follower;
    private final LetterExtractor letterExtractor;

    public FollowedPath follow(AsciiMap map) {

        val startingState = startFinder.findStart(map);

        val pathCharacters = follower.follow(startingState, map);

        val letters = letterExtractor.extract(pathCharacters);

        return new FollowedPath(pathCharacters, letters);
    }
}
