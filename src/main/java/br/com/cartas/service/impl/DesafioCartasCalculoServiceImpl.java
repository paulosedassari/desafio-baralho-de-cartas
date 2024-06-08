package br.com.cartas.service.impl;

import br.com.cartas.dto.search.CartaBaralhoDto;
import br.com.cartas.service.DesafioCartasCalculoService;
import br.com.cartas.util.CommonsUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DesafioCartasCalculoServiceImpl implements DesafioCartasCalculoService {

    @Override
    public Map<String, Integer> calcularPontuacaoDasCartas(ArrayList<List<CartaBaralhoDto>> hands) {
        Map<String, Integer> scores = new LinkedHashMap<>();

        for (int i = 0; i < hands.size(); i++) {
            int sum = retornarValoresDasCartasComoInteiro(hands.get(i)).stream().mapToInt(Integer::intValue).sum();
            scores.put("Jogador " + (i + 1), sum);
        }

        return scores;
    }

    private List<Integer> retornarValoresDasCartasComoInteiro(List<CartaBaralhoDto> cards) {
        return cards.stream()
                .map(card -> {
                    String value = card.getValue();
                    if (CommonsUtil.valoresCartasEspeciais().containsKey(value)) {
                        return CommonsUtil.valoresCartasEspeciais().get(value);
                    } else {
                        return Integer.parseInt(value);
                    }
                })
                .collect(Collectors.toList());
    }

}
