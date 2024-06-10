package br.com.cartas.controller;

import br.com.cartas.dto.InformacoesDesafioDto;
import br.com.cartas.dto.game.PartidaCartasDto;
import br.com.cartas.dto.game.RetornoDesafioDto;
import br.com.cartas.dto.game.RetornoPartidaCartasDto;
import br.com.cartas.service.InformacoesService;
import br.com.cartas.service.JogarDesafioCartasService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cartas/desafio")
public class DesafioCartasController {

    private final JogarDesafioCartasService jogarDesafioCartasService;
    private final InformacoesService informacoesService;

    public DesafioCartasController(JogarDesafioCartasService jogarDesafioCartasService, InformacoesService informacoesService) {
        this.jogarDesafioCartasService = jogarDesafioCartasService;
        this.informacoesService = informacoesService;
    }

    @GetMapping()
    public ResponseEntity<RetornoDesafioDto> jogarDesafioDasCartas() {
        RetornoDesafioDto resultadoMaoVencedora = jogarDesafioCartasService.jogarSemParticipante();
        return ResponseEntity.status(HttpStatus.OK).body(resultadoMaoVencedora);
    }

    @GetMapping("/informacoes")
    public ResponseEntity<InformacoesDesafioDto> retornarInformacoesSobreODesafio() {
        return ResponseEntity.status(HttpStatus.OK).body(informacoesService.estruturarInformacoesSobreODesafio());
    }

    @PostMapping("/partida")
    public ResponseEntity<RetornoPartidaCartasDto> jogarJuntoDesafioDasCartas(@RequestBody PartidaCartasDto partida) {
        RetornoPartidaCartasDto resultadoMaoVencedora = jogarDesafioCartasService.jogarComParticipante(partida);
        return ResponseEntity.status(HttpStatus.OK).body(resultadoMaoVencedora);
    }
}
