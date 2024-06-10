package br.com.cartas.service;

import br.com.cartas.dto.game.PartidaCartasDto;
import br.com.cartas.dto.game.RetornoDesafioDto;
import br.com.cartas.dto.game.RetornoPartidaCartasDto;

import java.util.Map;

public interface FormatarRespotaService {

    RetornoDesafioDto formatarRespostaSemOVencedorDoDesafio(Map<String, Integer> maoVencedora, Map<String, Integer> desafioRealizado);

    RetornoPartidaCartasDto formatarRespostaComOVencedorDoDesafio(Map<String, Integer> todasAsMaosParticipantesDoDesafio, PartidaCartasDto partida);
}
