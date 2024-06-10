package br.com.cartas.controller.openapi;

import br.com.cartas.dto.InformacoesDesafioDto;
import br.com.cartas.dto.game.PartidaCartasDto;
import br.com.cartas.dto.game.RetornoDesafioDto;
import br.com.cartas.dto.game.RetornoPartidaCartasDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Desafio Cartas")
@ApiResponses(value ={
        @ApiResponse(responseCode = "200", description = "Desafio realizado com sucesso.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = RetornoDesafioDto.class))
        }),
        @ApiResponse(responseCode = "404", description = "Recurso não encontrado.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = RetornoDesafioDto.class))
        }),
        @ApiResponse(responseCode = "500", description = "Erro interno no Servidor.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = RetornoDesafioDto.class))
        })
})
public interface DesafioCartasOpenapi {

    @Operation(
            operationId = "jogarDesafioDasCartas",
            summary = "Jogar Desafio das Cartas sem Jogador."
    )
    @GetMapping()
    ResponseEntity<RetornoDesafioDto> jogarDesafioDasCartas();

    @Operation(
            operationId = "retornarInformacoesSobreODesafio",
            summary = "Retorna informações sobre a API."
    )
    @GetMapping("/informacoes")
    ResponseEntity<InformacoesDesafioDto> retornarInformacoesSobreODesafio();

    @Operation(
            operationId = "jogarJuntoDesafioDasCartas",
            summary = "Jogar Desafio das Cartas com Jogador."
    )
    @PostMapping("/partida")
    ResponseEntity<RetornoPartidaCartasDto> jogarJuntoDesafioDasCartas(@RequestBody PartidaCartasDto partida);
}
