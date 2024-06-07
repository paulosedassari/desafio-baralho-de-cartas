package br.com.cartas.service;

import br.com.cartas.dto.game.PartidaCartasDto;
import br.com.cartas.dto.game.RetornoPartidaCartasDto;

import java.util.Map;

public interface FormatarRespotaService {

    String formatarRespostaSemOVencedorDoDesafioERegistrarJogoNaBase(Map<String, Integer> maoVencedora, Map<String, Integer> desafioRealizado);

    RetornoPartidaCartasDto formatarRespostaComOVencedorDoDesafioERegistrarJogoNaBase(Map<String, Integer> todasAsMaosParticipantesDoDesafio, PartidaCartasDto partida);
}
