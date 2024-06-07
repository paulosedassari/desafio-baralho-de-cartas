package br.com.cartas.dto.game;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
@JsonPropertyOrder({"nome_jogador", "mao_que_vai_apostar", "resultado_da_rodada"})
public class RetornoPartidaCartasDto extends PartidaCartasDto implements Serializable {

    @JsonProperty("resultado_da_rodada")
    private String resultado;

    public RetornoPartidaCartasDto(String nomeJogador, int maoDaAposta, String resultado) {
        super(nomeJogador, maoDaAposta);
        this.resultado = resultado;
    }
}
