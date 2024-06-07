package br.com.cartas.service.impl;

import br.com.cartas.dataprovider.client.CardDeck;
import br.com.cartas.dto.create.CriarBaralhoDto;
import br.com.cartas.dto.create.DadosBaralhoDto;
import br.com.cartas.dto.search.CartasDoBaralhoDto;
import br.com.cartas.exception.CardDeckException;
import br.com.cartas.service.BaralhoCartasService;
import br.com.cartas.util.Constantes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
public class BaralhoCartasServiceImpl implements BaralhoCartasService {

    private Logger logger = LoggerFactory.getLogger(BaralhoCartasServiceImpl.class);

    private final CardDeck feign;

    public BaralhoCartasServiceImpl(CardDeck feign) {
        this.feign = feign;
    }

    @Override
    public DadosBaralhoDto criarBaralho(CriarBaralhoDto dados) {
        try {
            logger.info(format(Constantes.INICIANDO_CRIACAO_NOVO_BARALHO, dados.getDeck_count()));
            return feign.criarBaralho(1);
        } catch (Exception e) {
            throw new CardDeckException(e.getMessage(), e);
        }
    }

    @Override
    public CartasDoBaralhoDto retirarCartasDoBaralho(String deckId, int count) {
        try {
            logger.info(format(Constantes.RETIRANDO_CARTAS_DO_BARALHO, count, deckId));
            return feign.retirarCartasDoBaralho(deckId, count);
        } catch (Exception e) {
            throw new CardDeckException(e.getMessage(), e);
        }
    }
}
