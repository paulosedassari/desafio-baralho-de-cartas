package br.com.cartas.service.impl;

import br.com.cartas.dto.game.PartidaCartasDto;
import br.com.cartas.dto.game.RetornoDesafioDto;
import br.com.cartas.dto.game.RetornoPartidaCartasDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FormatarRespotaServiceImplTest {

    FormatarRespotaServiceImpl service;

    @BeforeEach
    public void setUp() {
        service = new FormatarRespotaServiceImpl();
    }

    @Test
    public void testFormatarRespostaSemOVencedorDoDesafioValidInputs() {
        Map<String, Integer> maoVencedora = Map.of("Player1", 10);
        Map<String, Integer> desafioRealizado = Map.of("Player1", 10, "Player2", 5);

        RetornoDesafioDto result = service.formatarRespostaSemOVencedorDoDesafio(maoVencedora, desafioRealizado);

        assertEquals("Player1", result.getMaoVencedora());
    }

    @Test
    public void testFormatarRespostaComOVencedorDoDesafioValidInputs() {
        Map<String, Integer> todasAsMaosParticipantesDoDesafio = Map.of("Player1", 10, "Player2", 5);
        PartidaCartasDto partida = new PartidaCartasDto("Player1", 10, "Player1");

        RetornoPartidaCartasDto result = service.formatarRespostaComOVencedorDoDesafio(todasAsMaosParticipantesDoDesafio, partida);

        assertEquals("Player1", result.getMaoVencedora());
    }

    @Test
    public void testFormatarRespostaSemOVencedorDoDesafioEmptyMaps() {
        Map<String, Integer> maoVencedora = Map.of();
        Map<String, Integer> desafioRealizado = Map.of();

        RetornoDesafioDto result = service.formatarRespostaSemOVencedorDoDesafio(maoVencedora, desafioRealizado);

        assertEquals("", result.getMaoVencedora());
        assertTrue(result.getResultado().isEmpty());
    }

}