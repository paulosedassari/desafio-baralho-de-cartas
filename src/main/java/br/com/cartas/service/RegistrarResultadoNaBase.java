package br.com.cartas.service;

import br.com.cartas.model.CardDeckComJogadorEntity;
import br.com.cartas.model.CardDeckSemJogadorEntity;

public interface RegistrarResultadoNaBase {

    void salvarRegistroSemJogador(CardDeckSemJogadorEntity cardDeckSemJogadorEntity);
    void salvarRegistroComJogador(CardDeckComJogadorEntity cardDeckComJogadorEntity);
}
