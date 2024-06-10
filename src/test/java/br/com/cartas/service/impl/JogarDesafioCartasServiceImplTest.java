package br.com.cartas.service.impl;

import br.com.cartas.dto.game.PartidaCartasDto;
import br.com.cartas.dto.game.RetornoDesafioDto;
import br.com.cartas.dto.game.RetornoPartidaCartasDto;
import br.com.cartas.service.DesafioCartasService;
import br.com.cartas.service.FormatarRespotaService;
import br.com.cartas.service.RegistrarResultadoNaBase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class JogarDesafioCartasServiceImplTest {

    DesafioCartasService desafioCartasService;
    FormatarRespotaService formatarRespotaService;
    RegistrarResultadoNaBase registrarResultadoNaBase;

    @BeforeEach
    public void setUp() {
        desafioCartasService = mock(DesafioCartasService.class);
        formatarRespotaService = mock(FormatarRespotaService.class);
        registrarResultadoNaBase = mock(RegistrarResultadoNaBase.class);
    }

    @Test
    public void testJogarSemParticipanteReturnsCorrectRetornoDesafioDto() {
        Map<String, Integer> desafioRealizado = Map.of("Player1", 10, "Player2", 20);
        Map<String, Integer> maoVencedora = Map.of("Player2", 20);
        RetornoDesafioDto retornoDesafioDto = new RetornoDesafioDto("Player2", "Player2 venceu com 20 pontos!");

        when(desafioCartasService.realizarDesafio()).thenReturn(desafioRealizado);
        when(formatarRespotaService.formatarRespostaSemOVencedorDoDesafio(maoVencedora, desafioRealizado)).thenReturn(retornoDesafioDto);

        JogarDesafioCartasServiceImpl service = new JogarDesafioCartasServiceImpl(formatarRespotaService, desafioCartasService, registrarResultadoNaBase);
        RetornoDesafioDto result = service.jogarSemParticipante();

        assertEquals(retornoDesafioDto, result);
    }

    @Test
    public void testJogarComParticipanteReturnsCorrectRetornoPartidaCartasDto() {
        PartidaCartasDto partida = new PartidaCartasDto("Player1", 1, "Player2");
        Map<String, Integer> desafioRealizado = Map.of("Player1", 10, "Player2", 20);
        RetornoPartidaCartasDto retornoPartidaCartasDto = new RetornoPartidaCartasDto("Player1", 1, "Player2", "Player2 venceu com 20 pontos!");

        when(desafioCartasService.realizarDesafio()).thenReturn(desafioRealizado);
        when(formatarRespotaService.formatarRespostaComOVencedorDoDesafio(desafioRealizado, partida)).thenReturn(retornoPartidaCartasDto);

        JogarDesafioCartasServiceImpl service = new JogarDesafioCartasServiceImpl(formatarRespotaService, desafioCartasService, registrarResultadoNaBase);
        RetornoPartidaCartasDto result = service.jogarComParticipante(partida);

        assertEquals(retornoPartidaCartasDto, result);
    }

    @Test
    public void testjogarComParticipanteHandlesEmptyDesafioRealizadoMap() {
        PartidaCartasDto partida = new PartidaCartasDto("Player1", 1, "Player2");
        Map<String, Integer> desafioRealizado = Collections.emptyMap();
        RetornoPartidaCartasDto retornoPartidaCartasDto = new RetornoPartidaCartasDto("Player1", 1, "empate", "Ocorreu um empate entre os jogadores!");

        when(desafioCartasService.realizarDesafio()).thenReturn(desafioRealizado);
        when(formatarRespotaService.formatarRespostaComOVencedorDoDesafio(desafioRealizado, partida)).thenReturn(retornoPartidaCartasDto);

        JogarDesafioCartasServiceImpl service = new JogarDesafioCartasServiceImpl(formatarRespotaService, desafioCartasService, registrarResultadoNaBase);
        RetornoPartidaCartasDto result = service.jogarComParticipante(partida);

        assertEquals(retornoPartidaCartasDto, result);
    }

    @Test
    public void testRegistrarResultadoDoJogoComJogadorNaBaseHandlesNullPartida() {
        JogarDesafioCartasServiceImpl service = new JogarDesafioCartasServiceImpl(formatarRespotaService, desafioCartasService, registrarResultadoNaBase);

        assertThrows(NullPointerException.class, () -> {
            service.jogarComParticipante(null);
        });
    }
}