package nl.kooi.caesarcipher.core.usecase;

import nl.kooi.caesarcipher.core.model.CipheringResult;
import nl.kooi.caesarcipher.core.usecase.command.CipherCommand;

public interface CipherUseCase {

    CipheringResult cipher(CipherCommand command);

    CipheringResult decipher(CipherCommand command);
}
