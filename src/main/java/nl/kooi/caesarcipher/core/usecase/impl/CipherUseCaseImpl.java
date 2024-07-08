package nl.kooi.caesarcipher.core.usecase.impl;

import lombok.RequiredArgsConstructor;
import nl.kooi.caesarcipher.core.model.CipheringResult;
import nl.kooi.caesarcipher.core.port.CipheringService;
import nl.kooi.caesarcipher.core.usecase.CipherUseCase;
import nl.kooi.caesarcipher.core.usecase.command.CipherCommand;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CipherUseCaseImpl implements CipherUseCase {

    private final CipheringService cipheringService;

    @Override
    public CipheringResult cipher(CipherCommand command) {
        var result = cipheringService.cipher(command.text(), command.offset());

        return new CipheringResult(result, command.text(), command.offset());
    }

    @Override
    public CipheringResult decipher(CipherCommand command) {
        var result = cipheringService.decipher(command.text(), command.offset());

        return new CipheringResult(result, command.text(), command.offset());
    }
}
