package br.com.cartas.service.impl;

import br.com.cartas.dto.RodadasComJogadorDto;
import br.com.cartas.enums.StatusAposta;
import br.com.cartas.model.CardDeckComJogadorEntity;
import br.com.cartas.model.CardDeckSemJogadorEntity;
import br.com.cartas.repository.CardDeckComJogadorRepository;
import br.com.cartas.repository.CardDeckSemJogadorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RodadasServiceImplTest {

    CardDeckSemJogadorRepository cardDeckSemJogadorRepository;
    CardDeckComJogadorRepository cardDeckComJogadorRepository;
    ModelMapper modelMapper;

    @BeforeEach
    public void setUp() {
        cardDeckSemJogadorRepository = mock(CardDeckSemJogadorRepository.class);
        cardDeckComJogadorRepository = mock(CardDeckComJogadorRepository.class);
        modelMapper = new ModelMapper();
    }

    @Test
    public void testretrieveAllRecordsWithoutPlayerSuccessfully() {
        List<CardDeckSemJogadorEntity> mockEntities = List.of(
                new CardDeckSemJogadorEntity(1L, "Mao1", LocalDateTime.now()),
                new CardDeckSemJogadorEntity(2L, "Mao2", LocalDateTime.now())
        );

        when(cardDeckSemJogadorRepository.findAll()).thenReturn(mockEntities);
        RodadasServiceImpl rodadasService = new RodadasServiceImpl(cardDeckSemJogadorRepository, cardDeckComJogadorRepository, modelMapper);
        List<RodadasComJogadorDto> result = rodadasService.obterOsRegistrosSemJogador();

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    public void testHandleNullPlayerNameInputGracefully() {
        List<CardDeckComJogadorEntity> mockEntities = List.of(
                new CardDeckComJogadorEntity(1L, "Player1", 1, StatusAposta.ACERTOU, "Mao1", LocalDateTime.now()),
                new CardDeckComJogadorEntity(2L, "Player2", 2, StatusAposta.ERROU, "Mao2", LocalDateTime.now())
        );

        when(cardDeckComJogadorRepository.findAll()).thenReturn(mockEntities);
        RodadasServiceImpl rodadasService = new RodadasServiceImpl(cardDeckSemJogadorRepository, cardDeckComJogadorRepository, modelMapper);
        List<RodadasComJogadorDto> result = rodadasService.obterOsRegistrosComJogador(null);

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    public void testPlayerNameInputGracefully() {
        List<CardDeckComJogadorEntity> mockEntities = List.of(
                new CardDeckComJogadorEntity(1L, "Player1", 1, StatusAposta.ACERTOU, "Mao1", LocalDateTime.now()),
                new CardDeckComJogadorEntity(2L, "Player2", 2, StatusAposta.ERROU, "Mao2", LocalDateTime.now())
        );

        when(cardDeckComJogadorRepository.findAll()).thenReturn(mockEntities);
        RodadasServiceImpl rodadasService = new RodadasServiceImpl(cardDeckSemJogadorRepository, cardDeckComJogadorRepository, modelMapper);
        List<RodadasComJogadorDto> result = rodadasService.obterOsRegistrosComJogador("Paulo Sedassari");

        assertNotNull(result);
        assertEquals(0, result.size());
    }
}