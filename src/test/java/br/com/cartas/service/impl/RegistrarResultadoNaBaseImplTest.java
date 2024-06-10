package br.com.cartas.service.impl;

import br.com.cartas.enums.StatusAposta;
import br.com.cartas.exception.CardDeckException;
import br.com.cartas.model.CardDeckComJogadorEntity;
import br.com.cartas.model.CardDeckSemJogadorEntity;
import br.com.cartas.repository.CardDeckComJogadorRepository;
import br.com.cartas.repository.CardDeckSemJogadorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class RegistrarResultadoNaBaseImplTest {

    CardDeckSemJogadorRepository mockRepository;
    CardDeckComJogadorRepository mockComJogadorRepository;
    RegistrarResultadoNaBaseImpl service;

    @BeforeEach
    public void setUp() {
        mockRepository = mock(CardDeckSemJogadorRepository.class);
        mockComJogadorRepository = mock(CardDeckComJogadorRepository.class);
        service = new RegistrarResultadoNaBaseImpl(mockRepository, mockComJogadorRepository);
    }

    @Test
    public void testSaveCardDeckSemJogadorSuccess() {
        CardDeckSemJogadorEntity entity = new CardDeckSemJogadorEntity(1L, "RoyalFlush", LocalDateTime.now());
        service.salvarRegistroSemJogador(entity);

        verify(mockRepository, times(1)).save(entity);
    }

    @Test
    public void testSaveCardDeckComJogadorSuccess() {
        CardDeckComJogadorEntity entity = new CardDeckComJogadorEntity(1L, "John Doe", 5, StatusAposta.ACERTOU, "RoyalFlush", LocalDateTime.now());
        service.salvarRegistroComJogador(entity);

        verify(mockComJogadorRepository, times(1)).save(entity);
    }

    @Test
    public void testThrowsExceptionOnRepositoryErrorSemJogador() {
        CardDeckSemJogadorEntity entity = new CardDeckSemJogadorEntity(1L, "RoyalFlush", LocalDateTime.now());
        Mockito.doThrow(new RuntimeException("Error")).when(mockRepository).save(entity);

        assertThrows(CardDeckException.class, () -> service.salvarRegistroSemJogador(entity));
    }

    @Test
    public void testThrowsExceptionOnRepositoryErrorComJogador() {
        CardDeckComJogadorEntity entity = new CardDeckComJogadorEntity(1L, "RoyalFlush", 1, StatusAposta.ACERTOU, "Jogador 1", LocalDateTime.now());
        Mockito.doThrow(new RuntimeException("Error")).when(mockComJogadorRepository).save(entity);

        assertThrows(CardDeckException.class, () -> service.salvarRegistroComJogador(entity));
    }
}