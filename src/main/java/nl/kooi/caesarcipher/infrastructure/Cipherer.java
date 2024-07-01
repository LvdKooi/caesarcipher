package nl.kooi.caesarcipher.infrastructure;

import nl.kooi.caesarcipher.core.port.CipheringService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Map;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class Cipherer implements CipheringService {


    @Override
    public String cipher(String input, int offset) {
        var alphabet = getAlphabet(26);
        var cipheredAlphabet = getAlphabet(offset);

        return Arrays.stream(input.split(""))
                .map(letter -> alphabet.entrySet().stream().filter(entry -> entry.getValue().equals(letter)).map(Map.Entry::getKey).map(index -> cipheredAlphabet.get(calculateIndex(index, offset))).peek(System.out::println).findAny().orElse(letter))
                .collect(Collectors.joining());
    }

    private static Map<Integer, String> getAlphabet(int offset) {
        var alphabetSet = Arrays.stream("abcdefghijklmnopqrstuvwxyz".split("")).toList();

        return IntStream.rangeClosed(1, 26)
                .boxed()
                .map(i -> i - 1)
                .map(calculateOffset(offset))
                .collect(Collectors.toMap(i -> i + 1, alphabetSet::get));
    }

    private static UnaryOperator<Integer> calculateOffset(int offset) {
        return i -> {
            var result = i + offset;
            if (result > 25) {
                return result - 26;
            }
            return result;
        };
    }


    private static int calculateIndex(int i, int offset) {
        var result = i + offset;
        if (result > 26) {
            return result - 26;
        }
        return result;
    }
}
