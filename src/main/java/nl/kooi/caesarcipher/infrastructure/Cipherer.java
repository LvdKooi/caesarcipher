package nl.kooi.caesarcipher.infrastructure;

import nl.kooi.caesarcipher.core.port.CipheringService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class Cipherer implements CipheringService {

    private static final Map<Integer, String> ALPHABET = getAlphabet(26);

    @Override
    public String cipher(String input, int offset) {
        return Arrays.stream(input.split(""))
                .map(mapToOffsetLetter(getAlphabet(offset)))
                .collect(Collectors.joining());
    }

    private static UnaryOperator<String> mapToOffsetLetter(Map<Integer, String> toAlphabet) {
        return letter ->
                ALPHABET
                        .entrySet()
                        .stream()
                        .filter(entry -> entry.getValue().equals(letter))
                        .map(Map.Entry::getKey)
                        .map(toAlphabet::get)
                        .findAny()
                        .orElse(letter);
    }

    private static Map<Integer, String> getAlphabet(int offset) {
        var alphabetSet = Arrays.stream("abcdefghijklmnopqrstuvwxyz".split("")).toList();

        return IntStream.rangeClosed(1, 26)
                .boxed()
                .collect(Collectors.toMap(Function.identity(), i -> alphabetSet.get(calculateOffset(i, offset) - 1)));
    }

    private static int calculateOffset(int i, int offset) {
        return Optional.of(i + offset)
                .filter(result -> result > 26)
                .map(result -> result - 26)
                .orElse(i + offset);
    }
}
