package nl.kooi.caesarcipher.core.usecase.command;

import nl.kooi.caesarcipher.core.exception.CipheringException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CipherCommandTest {

    @Test
    void creatingACipherCommandContainingATextAndAValidOffset() {
        assertDoesNotThrow(() -> new CipherCommand("text", 1));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void nullOrEmptyTextShouldThrowACipheringException(String text) {
        assertThat(
                assertThrows(CipheringException.class, () -> new CipherCommand(text, 1)).getMessage()
        ).isEqualTo("The text shouldn't be null or empty.");
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 27, 28})
    void anOffsetNotBetweenOneAndTwentySixShouldThrowACipheringException(int offSet) {
        assertThat(
                assertThrows(CipheringException.class, () -> new CipherCommand("text", offSet)).getMessage()
        ).isEqualTo("The offset should be between 1 and 26.");
    }
}