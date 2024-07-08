package nl.kooi.caesarcipher.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import nl.kooi.caesarcipher.api.dto.CipherRequestDTO;
import nl.kooi.caesarcipher.api.dto.CipheringResultDTO;
import nl.kooi.caesarcipher.core.model.CipheringResult;
import nl.kooi.caesarcipher.core.usecase.CipherUseCase;
import nl.kooi.caesarcipher.core.usecase.command.CipherCommand;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CipherController.class)
class CipherControllerTest {

    @MockBean
    private CipherUseCase useCase;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Captor
    private ArgumentCaptor<CipherCommand> commandCaptor;

    @Test
    void shouldPassRequestUseCaseAndReturnResult_cipher() {
        when(useCase.cipher(any(CipherCommand.class)))
                .thenReturn(new CipheringResult("translation", "input", 1));

        var cipherRequest = new CipherRequestDTO("hi", 1);
        var result = getResult(cipherRequest, "cipher", status().isOk(), CipheringResultDTO.class);

        verify(useCase, times(1)).cipher(commandCaptor.capture());

        assertCommandAndResponse(result);
    }

    @Test
    void shouldPassRequestUseCaseAndReturnResult_deipher() {
        when(useCase.decipher(any(CipherCommand.class)))
                .thenReturn(new CipheringResult("translation", "input", 1));

        var cipherRequest = new CipherRequestDTO("hi", 1);
        var result = getResult(cipherRequest, "decipher", status().isOk(), CipheringResultDTO.class);

        verify(useCase, times(1)).decipher(commandCaptor.capture());

        assertCommandAndResponse(result);
    }

    @SneakyThrows
    private <T> T getResult(CipherRequestDTO cipherRequest,
                            String urlAction,
                            ResultMatcher resultMatcher,
                            Class<T> clazz) {

        var jsonResponse = mockMvc.perform(
                        post("/cipher-service/".concat(urlAction))
                                .content(objectMapper.writeValueAsString(cipherRequest))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(resultMatcher)
                .andReturn()
                .getResponse()
                .getContentAsString();

        return objectMapper.readValue(jsonResponse, clazz);
    }

    private void assertCommandAndResponse(CipheringResultDTO result) {
        assertThat(commandCaptor.getValue())
                .isNotNull()
                .extracting(
                        CipherCommand::offset,
                        CipherCommand::text
                )
                .containsExactly(
                        1,
                        "hi"
                );

        AssertionsForClassTypes.assertThat(result)
                .isNotNull()
                .extracting(CipheringResultDTO::result)
                .matches("translation"::equals);
    }

    @Test
    void shouldPassRequestUseCaseAndReturnResult_decipher() {
    }

}