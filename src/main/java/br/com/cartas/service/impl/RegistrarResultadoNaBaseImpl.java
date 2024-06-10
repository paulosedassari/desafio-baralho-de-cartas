package br.com.cartas.service.impl;

import br.com.cartas.exception.CardDeckException;
import br.com.cartas.model.CardDeckComJogadorEntity;
import br.com.cartas.model.CardDeckSemJogadorEntity;
import br.com.cartas.repository.CardDeckComJogadorRepository;
import br.com.cartas.repository.CardDeckSemJogadorRepository;
import br.com.cartas.service.RegistrarResultadoNaBase;
import br.com.cartas.util.Constantes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class RegistrarResultadoNaBaseImpl implements RegistrarResultadoNaBase {

    private Logger logger = LoggerFactory.getLogger(BaralhoServiceImpl.class);

    private final CardDeckSemJogadorRepository cardDeckSemJogadorRepository;
    private final CardDeckComJogadorRepository cardDeckComJogadorRepository;

    public RegistrarResultadoNaBaseImpl(CardDeckSemJogadorRepository cardDeckSemJogadorRepository, CardDeckComJogadorRepository cardDeckComJogadorRepository) {
        this.cardDeckSemJogadorRepository = cardDeckSemJogadorRepository;
        this.cardDeckComJogadorRepository = cardDeckComJogadorRepository;
    }

    @Override
    public void salvarRegistroSemJogador(CardDeckSemJogadorEntity cardDeckSemJogadorEntity) {
        try {
            logger.info(Constantes.SALVANDO_REGISTRO_SEM_JOGADOR_NA_BASE_CARD_DECK);
            cardDeckSemJogadorRepository.save(cardDeckSemJogadorEntity);
        } catch (Exception e) {
            throw new CardDeckException(e.getMessage(), e);
        }
    }

    @Override
    public void salvarRegistroComJogador(CardDeckComJogadorEntity cardDeckComJogadorEntity) {
        try {
            logger.info(Constantes.SALVANDO_REGISTRO_COM_JOGADOR_NA_BASE_CARD_DECK_JOGADOR);
            cardDeckComJogadorRepository.save(cardDeckComJogadorEntity);
        } catch (Exception e) {
            throw new CardDeckException(e.getMessage(), e);
        }
    }
}
