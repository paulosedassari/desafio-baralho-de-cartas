package br.com.cartas.service.impl;


import br.com.cartas.dto.create.CriarBaralhoDto;
import br.com.cartas.dto.create.DadosBaralhoDto;
import br.com.cartas.dto.game.PartidaCartasDto;
import br.com.cartas.dto.game.RetornoDesafioDto;
import br.com.cartas.dto.game.RetornoPartidaCartasDto;
import br.com.cartas.dto.search.CartaBaralhoDto;
import br.com.cartas.dto.search.CartasDoBaralhoDto;
import br.com.cartas.service.BaralhoCartasService;
import br.com.cartas.service.DesafioCartasService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
public class DesafioCartasServiceImpl implements DesafioCartasService {

    private final BaralhoCartasService baralhoCartasService;

    public DesafioCartasServiceImpl(BaralhoCartasService baralhoCartasService) {
        this.baralhoCartasService = baralhoCartasService;
    }

    private static final Map<String, Integer> VALORES_CARTAS_ESPECIAIS = Map.of(
            "ACE", 1,
            "KING", 13,
            "QUEEN", 12,
            "JACK", 11
    );

    @Override
    public RetornoDesafioDto realizarDesafioDasCartas() {
        String mensagemComOResultado = "";
        Map<String, Integer> desafioRealizado = realizarDesafio();

        selecionarMaoVencedora(desafioRealizado);

        for (Map.Entry<String, Integer> entry : desafioRealizado.entrySet()) {
            mensagemComOResultado = (format("O vencedor da partida foi o %s com a pontuação de %d pontos.", entry.getKey(), entry.getValue()));
        }

        return new RetornoDesafioDto(mensagemComOResultado);
    }

    @Override
    public RetornoPartidaCartasDto realizarDesafioDasCartasComParticipante(PartidaCartasDto partida) {
        Map<String, Integer> desafioRealizado = realizarDesafio();
        return formatarRespostaComOVencedorDoDesafio(desafioRealizado, partida);
    }

    private Map<String, Integer> realizarDesafio() {
        DadosBaralhoDto baralhoCriado = baralhoCartasService.criarBaralho(new CriarBaralhoDto(1));
        CartasDoBaralhoDto cartasRetiradasDoBaralho = baralhoCartasService.retirarCartasDoBaralho(baralhoCriado.getDeckId(), baralhoCriado.getRemaining());

        var cartasEmbaralhadas = embaralharCartasRetiradasDoBaralho(cartasRetiradasDoBaralho);
        var arrayDeCincoSubListComCincoCartasCada = separarAsCartasEmSubListComCincoCartas(cartasEmbaralhadas);

        return calcularPontuacaoDasCartas(arrayDeCincoSubListComCincoCartasCada);
    }

    private RetornoPartidaCartasDto formatarRespostaComOVencedorDoDesafio(Map<String, Integer> todasAsMaosParticipantesDoDesafio, PartidaCartasDto partida) {
        String resultadoDaApostaDoJogador = "";
        String jogadorVencedor = "";
        int qtdPontosJogadorVencedor = 0;

        Map<String, Integer> todasAsMaos = todasAsMaosParticipantesDoDesafio;

        selecionarMaoVencedora(todasAsMaosParticipantesDoDesafio);

        for (Map.Entry<String, Integer> entry : todasAsMaosParticipantesDoDesafio.entrySet()) {
            jogadorVencedor = entry.getKey();
            qtdPontosJogadorVencedor = entry.getValue();
            if (jogadorVencedor.contains(String.valueOf(partida.getMaoDaAposta()))) {
                resultadoDaApostaDoJogador = "Parabéeens!!! Você acertou a mão vencedora :).";
            } else {
                resultadoDaApostaDoJogador = "Que pena! Você não acertou a mão vencedora :(.";
            }
        }

        String resultado = format("Olá %s!" +
                        " Como vai? Cheguei com o resultado!!!" +
                        " Segue a pontuação completa: %s." +
                        " Você apostou na mão %d como vencedora, certo?" +
                        " %s" +
                        " O %s venceu com %d pontos!",
                partida.getNomeJogador(), todasAsMaos, partida.getMaoDaAposta(), resultadoDaApostaDoJogador, jogadorVencedor, qtdPontosJogadorVencedor);

        return new RetornoPartidaCartasDto(partida.getNomeJogador(), partida.getMaoDaAposta(), resultado);
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

    private Map<String, Integer> calcularPontuacaoDasCartas(List<List<CartaBaralhoDto>> hands) {
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
                    if (VALORES_CARTAS_ESPECIAIS.containsKey(value)) {
                        return VALORES_CARTAS_ESPECIAIS.get(value);
                    } else {
                        return Integer.parseInt(value);
                    }
                })
                .collect(Collectors.toList());
    }

    private static void selecionarMaoVencedora(Map<String, Integer> todasAsMaosParticipantesDoDesafio) {
        int maxScore = Collections.max(todasAsMaosParticipantesDoDesafio.values());
        todasAsMaosParticipantesDoDesafio.entrySet().stream()
                .filter(entry -> entry.getValue() == maxScore)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
