package nl.kooi.caesarcipher.core.usecase.command;

import nl.kooi.caesarcipher.core.exception.CipheringException;

public record CipherCommand(String text, int offset) {

    public CipherCommand {

        if (text == null || text.isEmpty()) {
            throw new CipheringException("The text shouldn't be null or empty.");
        }

        if (offset < 1 || offset > 26) {
            throw new CipheringException("The offset should be between 1 and 26.");
        }


    }
}
