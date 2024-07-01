package nl.kooi.caesarcipher.infrastructure;

import nl.kooi.caesarcipher.core.port.CipheringService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringJUnitConfig(Cipherer.class)
class CiphererTest {

    @Autowired
    private CipheringService cipheringService;

    @Test
    void testCipher_offset_1() {

        var result = cipheringService.cipher("hallo", 1);

        assertThat(result)
                .isNotNull()
                .isEqualTo("ibmmp");
    }

    @Test
    void testCipher_offset_25() {

        var result = cipheringService.cipher("hallo", 25);

        assertThat(result)
                .isNotNull()
                .isEqualTo("gzkkn");
    }

    @Test
    void testCipher_offset_26_should_return_same_word() {

        var result = cipheringService.cipher("hallo", 26);

        assertThat(result)
                .isNotNull()
                .isEqualTo("hallo");
    }


}