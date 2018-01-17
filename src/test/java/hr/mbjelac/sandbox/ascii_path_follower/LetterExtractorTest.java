package hr.mbjelac.sandbox.ascii_path_follower;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class LetterExtractorTest {

    private final LetterExtractor extractor = new LetterExtractor();

    @Test
    public void return_empty_string_on_null_or_empty() {

        assertThat(extractor.extract(null)).isEmpty();
        assertThat(extractor.extract("")).isEmpty();
    }

    @Test
    public void extract_only_uppercase_letters() {

        assertThat(extractor
                .extract("   .--a.e| |1-| d2XX zy3 :  4e; L!5 @ 6#%7a--x"))
                .isEqualTo("XXL");
    }
}