package br.com.cartas.controller;

import br.com.cartas.dto.RodadasComJogadorDto;
import br.com.cartas.dto.RodadasDto;
import br.com.cartas.service.RodadasService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rodadas")
public class RodadasController {

    private final RodadasService rodadasService;

    public RodadasController(RodadasService rodadasService) {
        this.rodadasService = rodadasService;
    }

    @GetMapping("/sem-jogador")
    public ResponseEntity<List<RodadasComJogadorDto>> obterTodosOsRegistrosSemJogador() {
        List<RodadasComJogadorDto> rodadasDtos = rodadasService.obterOsRegistrosSemJogador();
        return ResponseEntity.status(HttpStatus.OK).body(rodadasDtos);
    }

    @GetMapping("/com-jogador")
    public ResponseEntity<List<RodadasComJogadorDto>> obterTodosOsRegistrosComJogador(@RequestParam(value = "nome", required = false) String nome) {
        return ResponseEntity.status(HttpStatus.OK).body(rodadasService.obterOsRegistrosComJogador(nome));
    }
}
