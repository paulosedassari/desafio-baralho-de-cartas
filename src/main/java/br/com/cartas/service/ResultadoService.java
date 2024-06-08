package br.com.cartas.service;

import br.com.cartas.dto.game.PartidaCartasDto;
import br.com.cartas.dto.record.ResutaldoComJogador;
import br.com.cartas.dto.record.ResutaldoSemJogador;

import java.util.Map;

public interface ResultadoService {

    ResutaldoSemJogador estruturaMensagemDeResultadoDoDesafioSemJogador(Map<String, Integer> maoVencedora, Map<String, Integer> desafioRealizado, String mensagemComOResultado, String maoVencedoraAsString);

    String estruturarMensagemDeResultadoDoDesafioComJogador(PartidaCartasDto partida, Map<String, Integer> todasAsMaos, ResutaldoComJogador resultadoDaAposta);

    ResutaldoComJogador definirResultadoEMensagemComResultadoDaAposta(Map<String, Integer> todasAsMaosParticipantesDoDesafio, PartidaCartasDto partida, String jogadorVencedor, int qtdPontosJogadorVencedor, String resultadoDaApostaDoJogador);
}
