package hr.mbjelac.sandbox.ascii_path_follower;

public class AsciiPathFollowerFactory {

    public static AsciiPathFollower create() {

        return new AsciiPathFollower(
                new StartFinder(),
                new DirectionFinder(),
                new Follower(),
                new LetterExtractor());
    }
}
