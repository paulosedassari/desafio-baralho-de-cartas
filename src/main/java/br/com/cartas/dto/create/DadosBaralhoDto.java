package br.com.cartas.dto.create;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class DadosBaralhoDto implements Serializable {

    private Boolean success;

    @JsonProperty("deck_id")
    private String deckId;
    private int remaining;
    private Boolean shuffled;

    public void setDeckId(String deckId) {
        this.deckId = deckId;
    }
}
