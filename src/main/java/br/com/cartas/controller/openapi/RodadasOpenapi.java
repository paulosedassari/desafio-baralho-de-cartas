package br.com.cartas.controller.openapi;

import br.com.cartas.dto.RodadasComJogadorDto;
import br.com.cartas.dto.error.PadraoErroDto;
import br.com.cartas.dto.game.RetornoDesafioDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Tag(name = "Rodadas do Desafio Cartas")
@ApiResponses(value ={
        @ApiResponse(responseCode = "200", description = "Consulta efetuada com sucesso.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = RodadasComJogadorDto.class))
        }),
        @ApiResponse(responseCode = "404", description = "Recurso não encontrado.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = PadraoErroDto.class))
        }),
        @ApiResponse(responseCode = "500", description = "Erro interno no Servidor.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = PadraoErroDto.class))
        })
})
@RequestMapping("/rodadas")
public interface RodadasOpenapi {

    @Operation(
            operationId = "obterTodosOsRegistrosSemJogador",
            summary = "Consultar todos os registros sem jogador."
    )
    @GetMapping("/sem-jogador")
    ResponseEntity<List<RodadasComJogadorDto>> obterTodosOsRegistrosSemJogador();

    @Operation(
            operationId = "obterTodosOsRegistrosComJogador",
            summary = "Consultar todos os registros com jogador."
    )
    @GetMapping("/com-jogador")
    ResponseEntity<List<RodadasComJogadorDto>> obterTodosOsRegistrosComJogador(@RequestParam(value = "nome", required = false) String nome);
}
