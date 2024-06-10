package br.com.cartas.service.impl;

import br.com.cartas.dto.search.CartaBaralhoDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DesafioCartasCalculoServiceImplTest {

    DesafioCartasCalculoServiceImpl service;
    ArrayList<List<CartaBaralhoDto>> hands;

    @BeforeEach
    public void setUp() {
        service = new DesafioCartasCalculoServiceImpl();
        hands = new ArrayList<>();
    }

    @Test
    public void testCalculateScoresForMultipleHandsWithMixedCardValues() {
        List<CartaBaralhoDto> hand1 = new ArrayList<>();
        hand1.add(new CartaBaralhoDto("2", "image1", null, "2", "HEARTS"));
        hand1.add(new CartaBaralhoDto("KING", "image2", null, "KING", "SPADES"));

        List<CartaBaralhoDto> hand2 = new ArrayList<>();
        hand2.add(new CartaBaralhoDto("3", "image3", null, "3", "DIAMONDS"));
        hand2.add(new CartaBaralhoDto("QUEEN", "image4", null, "QUEEN", "CLUBS"));

        hands.add(hand1);
        hands.add(hand2);

        Map<String, Integer> scores = service.calcularPontuacaoDasCartas(hands);

        assertEquals(15, (int) scores.get("Jogador 1"));
        assertEquals(15, (int) scores.get("Jogador 2"));
    }

    @Test
    public void testHandleEmptyHandsWithoutErrors() {
        hands.add(new ArrayList<>());

        Map<String, Integer> scores = service.calcularPontuacaoDasCartas(hands);

        assertEquals(0, (int) scores.get("Jogador 1"));
    }
}