package hr.mbjelac.sandbox.ascii_path_follower;

import java.util.Optional;

class LetterExtractor {

    String extract(String characters) {

        return Optional
                .ofNullable(characters)
                .map(cs -> cs.replaceAll("[^A-Z]", ""))
                .orElse("");
    }
}
