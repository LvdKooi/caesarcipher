package nl.kooi.caesarcipher.core.port;

public interface CipheringService {

    String cipher(String input, int offset);

    String decipher(String input, int offset);
}
