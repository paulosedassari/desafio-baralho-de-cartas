package br.com.cartas.dto;

import br.com.cartas.enums.StatusAposta;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
@JsonPropertyOrder({"id", "nomeJogador", "maoDaAposta", "maoVencedora", "acerto", "dataJogo"})
public class RodadasComJogadorDto extends RodadasDto implements Serializable {

    @JsonProperty("nome_jogador")
    private String nomeJogador;

    @JsonProperty("mao_da_aposta")
    private int maoDaAposta;

    @JsonProperty("acerto")
    private StatusAposta acerto;
}
