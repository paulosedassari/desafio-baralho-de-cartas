package br.com.cartas.controller;

import br.com.cartas.dto.game.PartidaCartasDto;
import br.com.cartas.dto.game.RetornoDesafioDto;
import br.com.cartas.dto.game.RetornoPartidaCartasDto;
import br.com.cartas.service.DesafioCartasService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class DesafioCartasControllerTest {

    DesafioCartasService desafioCartasService;
    DesafioCartasController controller;

    @BeforeEach
    public void setUp() {
        desafioCartasService = mock(DesafioCartasService.class);
        controller = new DesafioCartasController(desafioCartasService);
    }

    @Test
    public void testGetCartasDesafioReturns200_OKWithValidRetornoDesafioDto() {
        RetornoDesafioDto retornoDesafioDto = new RetornoDesafioDto("ACERTOU");
        when(desafioCartasService.realizarDesafioDasCartas()).thenReturn(retornoDesafioDto);

        ResponseEntity<RetornoDesafioDto> response = controller.jogarDesafioDasCartas();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(retornoDesafioDto, response.getBody());
    }

    @Test
    public void testCorrectlyCallsRealizarDesafioDasCartasComParticipante() {
        PartidaCartasDto partida = new PartidaCartasDto("Jogador 1", 1);
        RetornoPartidaCartasDto retornoPartidaCartasDto = new RetornoPartidaCartasDto("Jogador 1", 1, "ACERTOU");
        when(desafioCartasService.realizarDesafioDasCartasComParticipante(partida)).thenReturn(retornoPartidaCartasDto);

        controller.jogarJuntoDesafioDasCartas(partida);

        verify(desafioCartasService, times(1)).realizarDesafioDasCartasComParticipante(partida);
    }
}