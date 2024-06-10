package br.com.cartas.service.impl;

import br.com.cartas.dto.create.CriarBaralhoDto;
import br.com.cartas.dto.create.DadosBaralhoDto;
import br.com.cartas.dto.search.CartaBaralhoDto;
import br.com.cartas.dto.search.CartasDoBaralhoDto;
import br.com.cartas.exception.CardDeckException;
import br.com.cartas.service.BaralhoService;
import br.com.cartas.service.DesafioCartasCalculoService;
import br.com.cartas.service.DesafioCartasService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class DesafioCartasServiceImpl implements DesafioCartasService {

    private final BaralhoService baralhoService;

    private final DesafioCartasCalculoService desafioCartasCalculoService;

    public DesafioCartasServiceImpl(BaralhoService baralhoService, DesafioCartasCalculoService desafioCartasCalculoService) {
        this.baralhoService = baralhoService;
        this.desafioCartasCalculoService = desafioCartasCalculoService;
    }

    @Override
    public Map<String, Integer> realizarDesafio() {
        try {
            DadosBaralhoDto baralhoCriado = baralhoService.criarBaralho(new CriarBaralhoDto(1));
            CartasDoBaralhoDto cartasRetiradasDoBaralho = baralhoService.retirarCartasDoBaralho(baralhoCriado.getDeckId(), 20);

            var cartasEmbaralhadas = embaralharCartasRetiradasDoBaralho(cartasRetiradasDoBaralho);
            var arrayDeCincoSubListComCincoCartasCada = separarAsCartasEmSubListComCincoCartas(cartasEmbaralhadas);

            return desafioCartasCalculoService.calcularPontuacaoDasCartas(arrayDeCincoSubListComCincoCartasCada);
        } catch (Exception e) {
            throw new CardDeckException(e.getMessage(), e);
        }
    }

    private static ArrayList<CartaBaralhoDto> embaralharCartasRetiradasDoBaralho(CartasDoBaralhoDto cartasRetiradasDoBaralho) {
        var cartas = new ArrayList<>(cartasRetiradasDoBaralho.getCards());
        Collections.shuffle(cartas);
        return cartas;
    }

    private static ArrayList<List<CartaBaralhoDto>> separarAsCartasEmSubListComCincoCartas(ArrayList<CartaBaralhoDto> cartas) {
        var hands = new ArrayList<List<CartaBaralhoDto>>();
        for (int i = 0; i < 4; i++) {
            hands.add(cartas.subList(i * 5, (i + 1) * 5));
        }
        return hands;
    }
}
