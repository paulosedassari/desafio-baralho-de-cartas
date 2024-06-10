package br.com.cartas.controller;

import br.com.cartas.dto.game.PartidaCartasDto;
import br.com.cartas.dto.game.RetornoDesafioDto;
import br.com.cartas.dto.game.RetornoPartidaCartasDto;
import br.com.cartas.service.InformacoesService;
import br.com.cartas.service.JogarDesafioCartasService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class DesafioCartasControllerTest {

    JogarDesafioCartasService jogarDesafioCartasService;
    InformacoesService informacoesService;
    DesafioCartasController controller;

    @BeforeEach
    public void setUp() {
        jogarDesafioCartasService = mock(JogarDesafioCartasService.class);
        informacoesService = mock(InformacoesService.class);
        controller = new DesafioCartasController(jogarDesafioCartasService, informacoesService);
    }

    @Test
    public void testGetCartasDesafioReturns200_OKWithValidRetornoDesafioDto() {
        RetornoDesafioDto retornoDesafioDto = new RetornoDesafioDto("Jogador 1", "ACERTOU");
        when(jogarDesafioCartasService.jogarSemParticipante()).thenReturn(retornoDesafioDto);

        ResponseEntity<RetornoDesafioDto> response = controller.jogarDesafioDasCartas();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(retornoDesafioDto, response.getBody());
    }

    @Test
    public void testCorrectlyCallsRealizarDesafioDasCartasComParticipante() {
        PartidaCartasDto partida = new PartidaCartasDto("Paulo", 1, "Jogador 1");
        RetornoPartidaCartasDto retornoPartidaCartasDto = new RetornoPartidaCartasDto("Jogador 1", 1, "Jogador 1","ACERTOU");
        when(jogarDesafioCartasService.jogarComParticipante(partida)).thenReturn(retornoPartidaCartasDto);

        controller.jogarJuntoDesafioDasCartas(partida);

        verify(jogarDesafioCartasService, times(1)).jogarComParticipante(partida);
    }
}