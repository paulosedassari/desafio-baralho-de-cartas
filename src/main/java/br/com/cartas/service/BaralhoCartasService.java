package br.com.cartas.service;

import br.com.cartas.dto.create.CriarBaralhoDto;
import br.com.cartas.dto.create.DadosBaralhoDto;
import br.com.cartas.dto.search.CartasDoBaralhoDto;

public interface BaralhoCartasService {

    DadosBaralhoDto criarBaralho(CriarBaralhoDto dados);

    CartasDoBaralhoDto retirarCartasDoBaralho(String deckId, int count);
}
