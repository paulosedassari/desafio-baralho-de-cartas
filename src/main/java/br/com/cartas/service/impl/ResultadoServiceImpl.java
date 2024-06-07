package br.com.cartas.service.impl;

import br.com.cartas.dto.game.PartidaCartasDto;
import br.com.cartas.record.ResutaldoComJogador;
import br.com.cartas.record.ResutaldoSemJogador;
import br.com.cartas.service.ResultadoService;
import br.com.cartas.util.Constantes;
import org.springframework.stereotype.Service;

import java.util.Map;

import static java.lang.String.format;

@Service
public class ResultadoServiceImpl implements ResultadoService {

    @Override
    public ResutaldoSemJogador estruturaMensagemDeResultadoDoDesafioSemJogador(Map<String, Integer> maoVencedora, Map<String, Integer> desafioRealizado, String mensagemComOResultado, String maoVencedoraAsString) {
        for (Map.Entry<String, Integer> entry : maoVencedora.entrySet()) {
            mensagemComOResultado = format(Constantes.PONTUACAO_SEM_PARTICIPANTE, desafioRealizado) +
                    format(Constantes.VENCEDOR_DA_PARTIDA_JOGO_SEM_PARTICIPANTE, entry.getKey(), entry.getValue());
            maoVencedoraAsString = entry.getKey();
        }
        ResutaldoSemJogador resultadoSemJogador = new ResutaldoSemJogador(mensagemComOResultado, maoVencedoraAsString);
        return resultadoSemJogador;
    }

    @Override
    public String estruturarMensagemDeResultadoDoDesafioComJogador(PartidaCartasDto partida, Map<String, Integer> todasAsMaos, ResutaldoComJogador resultadoDaAposta) {
        String resultado = format(Constantes.MENSAGEM_RETORNO_CONTENDO_INFORMACOES_SOBRE_RESULTADO, partida.getNomeJogador(), todasAsMaos, partida.getMaoDaAposta(), resultadoDaAposta.resultadoDaApostaDoJogador());

        if (!resultadoDaAposta.resultadoDaApostaDoJogador().contains(Constantes.EMPATE)) {
            resultado += format(Constantes.JOGADOR_VENCEDOR_SEM_EMPATE, resultadoDaAposta.jogadorVencedor(), resultadoDaAposta.qtdPontosJogadorVencedor());
        }

        return resultado;
    }

    @Override
    public ResutaldoComJogador definirResultadoEMensagemComResultadoDaAposta(Map<String, Integer> todasAsMaosParticipantesDoDesafio, PartidaCartasDto partida, String jogadorVencedor, int qtdPontosJogadorVencedor, String resultadoDaApostaDoJogador) {
        for (Map.Entry<String, Integer> entry : todasAsMaosParticipantesDoDesafio.entrySet()) {

            jogadorVencedor = entry.getKey();
            qtdPontosJogadorVencedor = entry.getValue();

            if (todasAsMaosParticipantesDoDesafio.size() > 1) {
                resultadoDaApostaDoJogador = format(Constantes.OCORREU_UM_EMPATE_ENTRE_OS_JOGADORES, todasAsMaosParticipantesDoDesafio);
                break;
            }

            if (jogadorVencedor.contains(String.valueOf(partida.getMaoDaAposta()))) {
                resultadoDaApostaDoJogador = Constantes.PARABENS_ACERTOU_A_MAO_VENCEDORA;
                break;
            }

            resultadoDaApostaDoJogador = Constantes.NAO_ACERTOU_A_MAO_VENCEDORA;
        }
        ResutaldoComJogador resultadoAposta = new ResutaldoComJogador(resultadoDaApostaDoJogador, jogadorVencedor, qtdPontosJogadorVencedor);
        return resultadoAposta;
    }
}
