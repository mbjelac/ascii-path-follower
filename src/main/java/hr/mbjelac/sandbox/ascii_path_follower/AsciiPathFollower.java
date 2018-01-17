package hr.mbjelac.sandbox.ascii_path_follower;

import lombok.RequiredArgsConstructor;
import lombok.val;

@RequiredArgsConstructor
public class AsciiPathFollower {

    private final Follower follower;
    private final LetterExtractor letterExtractor;

    public FollowedPath follow(AsciiMap map) {

        val pathCharacters = follower.follow(map);

        val letters = letterExtractor.extract(pathCharacters);

        return new FollowedPath(pathCharacters, letters);
    }
}
