package nl.kooi.caesarcipher.infrastructure;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringJUnitConfig(Cipherer.class)
class CiphererTest {

    @Autowired
    private Cipherer cipherer;

    @ParameterizedTest
    @ValueSource(strings = {"hallo", "HALLO", "HaLlo"})
    void testCipher_offset_1(String text) {
        var result = cipherer.cipher(text, 1);

        assertThat(result)
                .isNotNull()
                .isEqualTo("ibmmp");
    }

    @ParameterizedTest
    @ValueSource(strings = {"ibmmp", "IBMMP", "IbMmP"})
    void testDecipher_offset_1(String text) {
        var result = cipherer.decipher(text, 1);

        assertThat(result)
                .isNotNull()
                .isEqualTo("hallo");
    }

    @Test
    void testCipher_offset_25() {
        var result = cipherer.cipher("hallo", 25);

        assertThat(result)
                .isNotNull()
                .isEqualTo("gzkkn");
    }

    @Test
    void testDecipher_offset_25() {
        var result = cipherer.decipher("gzkkn", 25);

        assertThat(result)
                .isNotNull()
                .isEqualTo("hallo");
    }

    @Test
    void testCipher_offset_26_should_return_same_word() {
        var result = cipherer.cipher("hallo", 26);

        assertThat(result)
                .isNotNull()
                .isEqualTo("hallo");
    }

    @Test
    void testDecipher_offset_26_should_return_same_word() {
        var result = cipherer.decipher("hallo", 26);

        assertThat(result)
                .isNotNull()
                .isEqualTo("hallo");
    }
}