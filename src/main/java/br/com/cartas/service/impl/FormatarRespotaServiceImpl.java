package br.com.cartas.service.impl;

import br.com.cartas.dto.game.PartidaCartasDto;
import br.com.cartas.dto.game.RetornoPartidaCartasDto;
import br.com.cartas.enums.StatusAposta;
import br.com.cartas.model.CardDeckComJogadorEntity;
import br.com.cartas.model.CardDeckSemJogadorEntity;
import br.com.cartas.dto.record.ResutaldoComJogador;
import br.com.cartas.dto.record.ResutaldoSemJogador;
import br.com.cartas.service.FormatarRespotaService;
import br.com.cartas.service.RegistrarResultadoNaBase;
import br.com.cartas.service.ResultadoService;
import br.com.cartas.util.CommonsUtil;
import br.com.cartas.util.Constantes;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class FormatarRespotaServiceImpl implements FormatarRespotaService {

    private final RegistrarResultadoNaBase registrarResultadoNaBase;
    private final ResultadoService resultadoService;

    public FormatarRespotaServiceImpl(RegistrarResultadoNaBase registrarResultadoNaBase, ResultadoService resultadoService) {
        this.registrarResultadoNaBase = registrarResultadoNaBase;
        this.resultadoService = resultadoService;
    }

    @Override
    public String formatarRespostaSemOVencedorDoDesafioERegistrarJogoNaBase(Map<String, Integer> maoVencedora, Map<String, Integer> desafioRealizado) {
        String mensagemComOResultado = "";
        String maoVencedoraAsString = "";

        ResutaldoSemJogador resultadoSemJogador = resultadoService.estruturaMensagemDeResultadoDoDesafioSemJogador(maoVencedora, desafioRealizado, mensagemComOResultado, maoVencedoraAsString);

        registrarResultadoNaBase.salvarRegistroSemJogador(new CardDeckSemJogadorEntity(null, resultadoSemJogador.maoVencedoraAsString(), LocalDateTime.now()));
        return resultadoSemJogador.mensagemComOResultado();
    }

    @Override
    public RetornoPartidaCartasDto formatarRespostaComOVencedorDoDesafioERegistrarJogoNaBase(Map<String, Integer> todasAsMaosParticipantesDoDesafio, PartidaCartasDto partida) {
        String resultadoDaApostaDoJogador = "";
        String jogadorVencedor = "";
        int qtdPontosJogadorVencedor = 0;

        Map<String, Integer> todasAsMaos = todasAsMaosParticipantesDoDesafio;
        Map<String, Integer> maoVencedora = CommonsUtil.selecionarMaoComMaiorPontuacao(todasAsMaosParticipantesDoDesafio);

        ResutaldoComJogador resultadoAposta = resultadoService.definirResultadoEMensagemComResultadoDaAposta(maoVencedora, partida, jogadorVencedor, qtdPontosJogadorVencedor, resultadoDaApostaDoJogador);

        String resultado = resultadoService.estruturarMensagemDeResultadoDoDesafioComJogador(partida, todasAsMaos, resultadoAposta);
        registrarResultadoDoJogoComJogadorNaBase(partida, resultado, resultadoAposta.jogadorVencedor());

        return new RetornoPartidaCartasDto(partida.getNomeJogador(), partida.getMaoDaAposta(), resultado);
    }

    private void registrarResultadoDoJogoComJogadorNaBase(PartidaCartasDto partida, String resultado, String jogadorVencedor) {
        if (resultado.contains(Constantes.PARABENS_ACERTOU_A_MAO_VENCEDORA)) {
            registrarResultadoNaBase.salvarRegistroComJogador(new CardDeckComJogadorEntity(null, partida.getNomeJogador(), partida.getMaoDaAposta(), StatusAposta.ACERTOU, jogadorVencedor, LocalDateTime.now()));
        } else {
            registrarResultadoNaBase.salvarRegistroComJogador(new CardDeckComJogadorEntity(null, partida.getNomeJogador(), partida.getMaoDaAposta(), StatusAposta.ERROU, jogadorVencedor, LocalDateTime.now()));
        }
    }
}
