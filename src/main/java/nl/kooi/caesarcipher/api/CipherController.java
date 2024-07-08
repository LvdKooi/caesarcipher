package nl.kooi.caesarcipher.api;

import lombok.RequiredArgsConstructor;
import nl.kooi.caesarcipher.api.dto.CipherRequestDTO;
import nl.kooi.caesarcipher.api.dto.CipheringResultDTO;
import nl.kooi.caesarcipher.core.usecase.CipherUseCase;
import nl.kooi.caesarcipher.core.usecase.command.CipherCommand;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cipher-service")
public class CipherController {

    private final CipherUseCase useCase;

    @PostMapping("/cipher")
    public CipheringResultDTO cipher(@RequestBody CipherRequestDTO request) {
        var result = useCase.cipher(new CipherCommand(request.text(), request.offset()));

        return new CipheringResultDTO(result.translation());
    }

    @PostMapping("/decipher")
    public CipheringResultDTO decipher(@RequestBody CipherRequestDTO request) {
        var result = useCase.decipher(new CipherCommand(request.text(), request.offset()));

        return new CipheringResultDTO(result.translation());
    }
}
