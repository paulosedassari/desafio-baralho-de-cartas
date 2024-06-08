package br.com.cartas.service;

import br.com.cartas.dto.RodadasComJogadorDto;

import java.util.List;

public interface RodadasService {

    List<RodadasComJogadorDto> obterOsRegistrosSemJogador();

    List<RodadasComJogadorDto> obterOsRegistrosComJogador(String nome);
}
