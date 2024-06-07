package br.com.cartas.service.impl;


import br.com.cartas.dto.create.CriarBaralhoDto;
import br.com.cartas.dto.create.DadosBaralhoDto;
import br.com.cartas.dto.game.PartidaCartasDto;
import br.com.cartas.dto.game.RetornoDesafioDto;
import br.com.cartas.dto.game.RetornoPartidaCartasDto;
import br.com.cartas.dto.search.CartaBaralhoDto;
import br.com.cartas.dto.search.CartasDoBaralhoDto;
import br.com.cartas.enums.StatusAposta;
import br.com.cartas.model.CardDeckComJogadorEntity;
import br.com.cartas.model.CardDeckSemJogadorEntity;
import br.com.cartas.service.BaralhoCartasService;
import br.com.cartas.service.DesafioCartasService;
import br.com.cartas.service.RegistrarResultadoNaBase;
import br.com.cartas.util.Constantes;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
public class DesafioCartasServiceImpl implements DesafioCartasService {

    private final BaralhoCartasService baralhoCartasService;
    private final RegistrarResultadoNaBase registrarResultadoNaBase;

    public DesafioCartasServiceImpl(BaralhoCartasService baralhoCartasService, RegistrarResultadoNaBase registrarResultadoNaBase) {
        this.baralhoCartasService = baralhoCartasService;
        this.registrarResultadoNaBase = registrarResultadoNaBase;
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

        Map<String, Integer> maoVencedora = selecionarMaoVencedora(desafioRealizado);
        String maoVencedoraAsString = "";

        for (Map.Entry<String, Integer> entry : maoVencedora.entrySet()) {
            mensagemComOResultado = format(Constantes.PONTUACAO_SEM_PARTICIPANTE, desafioRealizado) +
                    format(Constantes.VENCEDOR_DA_PARTIDA_JOGO_SEM_PARTICIPANTE, entry.getKey(), entry.getValue());
            maoVencedoraAsString = entry.getKey();
        }

        registrarResultadoNaBase.salvarRegistroSemJogador(new CardDeckSemJogadorEntity(null, maoVencedoraAsString, LocalDateTime.now()));
        return new RetornoDesafioDto(mensagemComOResultado);
    }

    @Override
    public RetornoPartidaCartasDto realizarDesafioDasCartasComParticipante(PartidaCartasDto partida) {
        Map<String, Integer> desafioRealizado = realizarDesafio();
        return formatarRespostaComOVencedorDoDesafioERegistrarJogoNaBase(desafioRealizado, partida);
    }

    private Map<String, Integer> realizarDesafio() {
        DadosBaralhoDto baralhoCriado = baralhoCartasService.criarBaralho(new CriarBaralhoDto(1));
        CartasDoBaralhoDto cartasRetiradasDoBaralho = baralhoCartasService.retirarCartasDoBaralho(baralhoCriado.getDeckId(), baralhoCriado.getRemaining());

        var cartasEmbaralhadas = embaralharCartasRetiradasDoBaralho(cartasRetiradasDoBaralho);
        var arrayDeCincoSubListComCincoCartasCada = separarAsCartasEmSubListComCincoCartas(cartasEmbaralhadas);

        return calcularPontuacaoDasCartas(arrayDeCincoSubListComCincoCartasCada);
    }

    private RetornoPartidaCartasDto formatarRespostaComOVencedorDoDesafioERegistrarJogoNaBase(Map<String, Integer> todasAsMaosParticipantesDoDesafio, PartidaCartasDto partida) {

        String resultadoDaApostaDoJogador = "";
        String jogadorVencedor = "";
        int qtdPontosJogadorVencedor = 0;

        Map<String, Integer> todasAsMaos = todasAsMaosParticipantesDoDesafio;
        Map<String, Integer> maoVencedora = selecionarMaoVencedora(todasAsMaosParticipantesDoDesafio);

        DefinirResultadoDaAposta resultadoAposta = definirResultadoEMensagemComResultadoDaAposta(maoVencedora, partida, jogadorVencedor, qtdPontosJogadorVencedor, resultadoDaApostaDoJogador);

        String resultado = estruturarMensagemDeResultadoDoDesafio(partida, todasAsMaos, resultadoAposta.resultadoDaApostaDoJogador(), resultadoAposta.jogadorVencedor(), resultadoAposta.qtdPontosJogadorVencedor());
        registrarResultadoDoJogoNaBase(partida, resultado, resultadoAposta.jogadorVencedor());

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

    private static Map<String, Integer> selecionarMaoVencedora(Map<String, Integer> todasAsMaosParticipantesDoDesafio) {
        int maxScore = Collections.max(todasAsMaosParticipantesDoDesafio.values());

        return todasAsMaosParticipantesDoDesafio.entrySet().stream()
                .filter(entry -> entry.getValue() == maxScore)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private static String estruturarMensagemDeResultadoDoDesafio(PartidaCartasDto partida, Map<String, Integer> todasAsMaos, String resultadoDaApostaDoJogador, String jogadorVencedor, int qtdPontosJogadorVencedor) {
        String resultado = format("Olá %s!" +
                        " Como vai? Cheguei com o resultado!!!" +
                        " Segue a pontuação completa: %s." +
                        " Você apostou na mão %d como vencedora, certo?" +
                        " %s", partida.getNomeJogador(), todasAsMaos, partida.getMaoDaAposta(), resultadoDaApostaDoJogador);
        if (!resultadoDaApostaDoJogador.contains("empate")) resultado += format(" O %s venceu com %d pontos!", jogadorVencedor, qtdPontosJogadorVencedor);
        return resultado;
    }

    private void registrarResultadoDoJogoNaBase(PartidaCartasDto partida, String resultado, String jogadorVencedor) {
        if (resultado.contains(Constantes.PARABENS_ACERTOU_A_MAO_VENCEDORA)) {
            registrarResultadoNaBase.salvarRegistroComJogador(new CardDeckComJogadorEntity(null, partida.getNomeJogador(), partida.getMaoDaAposta(), StatusAposta.ACERTOU, jogadorVencedor, LocalDateTime.now()));
        } else {
            registrarResultadoNaBase.salvarRegistroComJogador(new CardDeckComJogadorEntity(null, partida.getNomeJogador(), partida.getMaoDaAposta(), StatusAposta.ERROU, jogadorVencedor, LocalDateTime.now()));
        }
    }

    private static DefinirResultadoDaAposta definirResultadoEMensagemComResultadoDaAposta(Map<String, Integer> todasAsMaosParticipantesDoDesafio, PartidaCartasDto partida, String jogadorVencedor, int qtdPontosJogadorVencedor, String resultadoDaApostaDoJogador) {

        for (Map.Entry<String, Integer> entry : todasAsMaosParticipantesDoDesafio.entrySet()) {

            jogadorVencedor = entry.getKey();
            qtdPontosJogadorVencedor = entry.getValue();

            if (todasAsMaosParticipantesDoDesafio.size() > 1) {
                resultadoDaApostaDoJogador = format("No entanto, nessa rodada ocorreu um empate entre os jogadores %s!", todasAsMaosParticipantesDoDesafio);
                break;
            }

            if (jogadorVencedor.contains(String.valueOf(partida.getMaoDaAposta()))) {
                resultadoDaApostaDoJogador = Constantes.PARABENS_ACERTOU_A_MAO_VENCEDORA;
                break;
            }

            resultadoDaApostaDoJogador = Constantes.NAO_ACERTOU_A_MAO_VENCEDORA;
        }
        DefinirResultadoDaAposta resultadoAposta = new DefinirResultadoDaAposta(resultadoDaApostaDoJogador, jogadorVencedor, qtdPontosJogadorVencedor);
        return resultadoAposta;
    }

    private record DefinirResultadoDaAposta(String resultadoDaApostaDoJogador, String jogadorVencedor,
                                            int qtdPontosJogadorVencedor) {
    }
}
