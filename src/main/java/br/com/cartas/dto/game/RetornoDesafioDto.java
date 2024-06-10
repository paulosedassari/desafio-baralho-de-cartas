package br.com.cartas.dto.game;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@ToString
@JsonPropertyOrder({"jogador_vencedor", "resultado"})
public class RetornoDesafioDto implements Serializable {

    @JsonProperty("jogador_vencedor")
    private String maoVencedora;
    private String resultado;
}
