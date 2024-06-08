package br.com.cartas.service.impl;

import br.com.cartas.dataprovider.client.CardDeck;
import br.com.cartas.dto.create.CriarBaralhoDto;
import br.com.cartas.exception.CardDeckException;
import br.com.cartas.util.Constantes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertThrows;

class BaralhoServiceImplTest {

    Logger logger;
    CardDeck feign;
    BaralhoServiceImpl service;
    CriarBaralhoDto criarBaralhoDto;

    @BeforeEach
    public void setUp() {
        logger = Mockito.mock(Logger.class);
        feign = Mockito.mock(CardDeck.class);
        service = new BaralhoServiceImpl(feign);
        criarBaralhoDto = new CriarBaralhoDto(1);
    }

    @Test
    public void testCriarBaralhoLogsStartOfDeckCreation() {
        ReflectionTestUtils.setField(service, "logger", logger);

        service.criarBaralho(criarBaralhoDto);

        Mockito.verify(logger).info(String.format(Constantes.INICIANDO_CRIACAO_NOVO_BARALHO, 1));
    }

    @Test
    public void testCriarBaralhoCallsFeignWithCorrectDeckCount() {
        service.criarBaralho(criarBaralhoDto);

        Mockito.verify(feign).criarBaralho(1);
    }

    @Test
    public void testCriarBaralhoHandlesExceptionsAndThrowsCardDeckException() {
        Mockito.when(feign.criarBaralho(Mockito.anyInt())).thenThrow(new RuntimeException("Error"));

        assertThrows(CardDeckException.class, () -> service.criarBaralho(criarBaralhoDto));
    }

    @Test
    public void testRetirarCartasDoBaralhoHandlesExceptionsAndThrowsCardDeckException() {
        Mockito.when(feign.retirarCartasDoBaralho(Mockito.anyString(), Mockito.anyInt())).thenThrow(new RuntimeException("Error"));

        assertThrows(CardDeckException.class, () -> service.retirarCartasDoBaralho("deckId", 1));
    }

}