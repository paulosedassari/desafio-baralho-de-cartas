package br.com.cartas.dto.game;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class PartidaCartasDto implements Serializable {

    @JsonProperty("nome_jogador")
    private String nomeJogador;

    @JsonProperty("mao_que_vai_apostar")
    private int maoDaAposta;


    public PartidaCartasDto(String nomeJogador, int maoDaAposta) {
        this.nomeJogador = nomeJogador;
        this.maoDaAposta = maoDaAposta;
    }
}
