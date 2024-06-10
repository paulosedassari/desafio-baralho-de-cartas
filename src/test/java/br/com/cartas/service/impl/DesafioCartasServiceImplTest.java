package br.com.cartas.service.impl;

import br.com.cartas.dto.create.CriarBaralhoDto;
import br.com.cartas.dto.create.DadosBaralhoDto;
import br.com.cartas.dto.search.CartaBaralhoDto;
import br.com.cartas.dto.search.CartasDoBaralhoDto;
import br.com.cartas.dto.search.ImagensCartaDto;
import br.com.cartas.exception.CardDeckException;
import br.com.cartas.service.BaralhoService;
import br.com.cartas.service.DesafioCartasCalculoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class DesafioCartasServiceImplTest {

    BaralhoService baralhoService;
    DesafioCartasCalculoService desafioCartasCalculoService;
    DesafioCartasServiceImpl desafioCartasService;
    DadosBaralhoDto dadosBaralhoDto;
    CartasDoBaralhoDto cartasDoBaralhoDto;

    @BeforeEach
    public void setUp() {
        baralhoService = mock(BaralhoService.class);
        desafioCartasCalculoService = mock(DesafioCartasCalculoService.class);
        desafioCartasService = new DesafioCartasServiceImpl(baralhoService, desafioCartasCalculoService);
        dadosBaralhoDto = new DadosBaralhoDto();
        dadosBaralhoDto.setDeckId("123");
        cartasDoBaralhoDto = new CartasDoBaralhoDto();
        cartasDoBaralhoDto.setCards(new ArrayList<>());
        cartasDoBaralhoDto.getCards().addAll(criarListaDeCartas());
    }

    @Test
    public void testCriarBaralhoCalledWithCorrectParameters() {
        when(baralhoService.criarBaralho(any(CriarBaralhoDto.class))).thenReturn(dadosBaralhoDto);
        when(baralhoService.retirarCartasDoBaralho(anyString(), anyInt())).thenReturn(cartasDoBaralhoDto);

        desafioCartasService.realizarDesafio();

        verify(baralhoService).criarBaralho(argThat(argument -> argument.getDeck_count() == 1));
    }

    @Test
    public void testRealizarDesafioHandleException() {
        when(baralhoService.criarBaralho(any(CriarBaralhoDto.class))).thenThrow(new RuntimeException("Error creating deck"));

        Exception exception = assertThrows(CardDeckException.class, () -> {
            desafioCartasService.realizarDesafio();
        });

        assertEquals("Error creating deck", exception.getMessage());
    }

    private List<CartaBaralhoDto> criarListaDeCartas() {
        return IntStream.range(0, 20)
                .mapToObj(i -> new CartaBaralhoDto("code", "image", new ImagensCartaDto(), "value", "suit"))
                .collect(Collectors.toList());
    }
}