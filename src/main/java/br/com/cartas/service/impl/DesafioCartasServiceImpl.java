package br.com.cartas.service.impl;

import br.com.cartas.dto.create.CriarBaralhoDto;
import br.com.cartas.dto.create.DadosBaralhoDto;
import br.com.cartas.dto.game.PartidaCartasDto;
import br.com.cartas.dto.game.RetornoDesafioDto;
import br.com.cartas.dto.game.RetornoPartidaCartasDto;
import br.com.cartas.dto.search.CartaBaralhoDto;
import br.com.cartas.dto.search.CartasDoBaralhoDto;
import br.com.cartas.service.*;
import br.com.cartas.util.CommonsUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class DesafioCartasServiceImpl implements DesafioCartasService {

    private final BaralhoService baralhoService;

    private final DesafioCartasCalculoService desafioCartasCalculoService;
    private final FormatarRespotaService formatarRespotaService;

    public DesafioCartasServiceImpl(BaralhoService baralhoService, RegistrarResultadoNaBase registrarResultadoNaBase, DesafioCartasCalculoService desafioCartasCalculoService, FormatarRespotaService formatarRespotaService) {
        this.baralhoService = baralhoService;
        this.desafioCartasCalculoService = desafioCartasCalculoService;
        this.formatarRespotaService = formatarRespotaService;
    }

    @Override
    public RetornoDesafioDto realizarDesafioDasCartas() {
        Map<String, Integer> desafioRealizado = realizarDesafio();
        Map<String, Integer> maoVencedora = CommonsUtil.selecionarMaoComMaiorPontuacao(desafioRealizado);

        String mensagemComOResultado = formatarRespotaService.formatarRespostaSemOVencedorDoDesafioERegistrarJogoNaBase(maoVencedora, desafioRealizado);
        return new RetornoDesafioDto(mensagemComOResultado);
    }

    @Override
    public RetornoPartidaCartasDto realizarDesafioDasCartasComParticipante(PartidaCartasDto partida) {
        Map<String, Integer> desafioRealizado = realizarDesafio();
        return formatarRespotaService.formatarRespostaComOVencedorDoDesafioERegistrarJogoNaBase(desafioRealizado, partida);
    }

    private Map<String, Integer> realizarDesafio() {
        DadosBaralhoDto baralhoCriado = baralhoService.criarBaralho(new CriarBaralhoDto(1));
        CartasDoBaralhoDto cartasRetiradasDoBaralho = baralhoService.retirarCartasDoBaralho(baralhoCriado.getDeckId(), baralhoCriado.getRemaining());

        var cartasEmbaralhadas = embaralharCartasRetiradasDoBaralho(cartasRetiradasDoBaralho);
        var arrayDeCincoSubListComCincoCartasCada = separarAsCartasEmSubListComCincoCartas(cartasEmbaralhadas);

        return desafioCartasCalculoService.calcularPontuacaoDasCartas(arrayDeCincoSubListComCincoCartasCada);
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
