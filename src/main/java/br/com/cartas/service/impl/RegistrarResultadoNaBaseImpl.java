package br.com.cartas.service.impl;

import br.com.cartas.exception.CardDeckException;
import br.com.cartas.model.CardDeckComJogadorEntity;
import br.com.cartas.model.CardDeckSemJogadorEntity;
import br.com.cartas.repository.CardDeckComJogadorRepository;
import br.com.cartas.repository.CardDeckRepository;
import br.com.cartas.service.RegistrarResultadoNaBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class RegistrarResultadoNaBaseImpl implements RegistrarResultadoNaBase {

    private Logger logger = LoggerFactory.getLogger(BaralhoServiceImpl.class);

    private final CardDeckRepository cardDeckRepository;
    private final CardDeckComJogadorRepository cardDeckComJogadorRepository;

    public RegistrarResultadoNaBaseImpl(CardDeckRepository cardDeckRepository, CardDeckComJogadorRepository cardDeckComJogadorRepository) {
        this.cardDeckRepository = cardDeckRepository;
        this.cardDeckComJogadorRepository = cardDeckComJogadorRepository;
    }

    @Override
    public void salvarRegistroSemJogador(CardDeckSemJogadorEntity cardDeckSemJogadorEntity) {
        try {
            logger.info("Salvando registro do desafio sem jogador na base CARD_DECK.");
            cardDeckRepository.save(cardDeckSemJogadorEntity);
        } catch (Exception e) {
            throw new CardDeckException(e.getMessage(), e);
        }
    }

    @Override
    public void salvarRegistroComJogador(CardDeckComJogadorEntity cardDeckComJogadorEntity) {
        try {
            logger.info("Salvando registro do desafio com jogador na base CARD_DECK_JOGADOR.");
            cardDeckComJogadorRepository.save(cardDeckComJogadorEntity);
        } catch (Exception e) {
            throw new CardDeckException(e.getMessage(), e);
        }
    }
}
