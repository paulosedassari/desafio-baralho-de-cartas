package br.com.cartas.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CommonsUtil {

    public static final Map<String, Integer> valoresCartasEspeciais() {
        return Map.of(
                "ACE", 1,
                "KING", 13,
                "QUEEN", 12,
                "JACK", 11
        );
    }

    public static Map<String, Integer> selecionarMaoComMaiorPontuacao(Map<String, Integer> todasAsMaosParticipantesDoDesafio) {
        int maxScore = Collections.max(todasAsMaosParticipantesDoDesafio.values());

        return todasAsMaosParticipantesDoDesafio.entrySet().stream()
                .filter(entry -> entry.getValue() == maxScore)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static List<Integer> opcoesValidasParaAsMaosDasApostas() {
        return List.of(1, 2, 3, 4);
    }
}
