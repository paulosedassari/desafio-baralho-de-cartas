package br.com.cartas.service;

import br.com.cartas.dto.game.PartidaCartasDto;
import br.com.cartas.dto.game.RetornoDesafioDto;
import br.com.cartas.dto.game.RetornoPartidaCartasDto;

public interface JogarDesafioCartasService {

    RetornoDesafioDto jogarSemParticipante();

    RetornoPartidaCartasDto jogarComParticipante(PartidaCartasDto partida);
}
