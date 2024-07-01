package nl.kooi.caesarcipher.infrastructure;

import nl.kooi.caesarcipher.core.port.DecipheringService;
import org.springframework.stereotype.Service;

@Service
public class Decipherer implements DecipheringService {
    @Override
    public String decipher(String input, int offset) {
        return "";
    }
}
