package br.com.cartas.controller;

import br.com.cartas.controller.openapi.RodadasOpenapi;
import br.com.cartas.dto.RodadasComJogadorDto;
import br.com.cartas.service.RodadasService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RodadasController implements RodadasOpenapi {

    private final RodadasService rodadasService;

    public RodadasController(RodadasService rodadasService) {
        this.rodadasService = rodadasService;
    }

    @Override
    public ResponseEntity<List<RodadasComJogadorDto>> obterTodosOsRegistrosSemJogador() {
        List<RodadasComJogadorDto> rodadasDtos = rodadasService.obterOsRegistrosSemJogador();
        return ResponseEntity.status(HttpStatus.OK).body(rodadasDtos);
    }

    @Override
    public ResponseEntity<List<RodadasComJogadorDto>> obterTodosOsRegistrosComJogador(@RequestParam(value = "nome", required = false) String nome) {
        return ResponseEntity.status(HttpStatus.OK).body(rodadasService.obterOsRegistrosComJogador(nome));
    }
}
