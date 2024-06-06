package br.com.cartas.dto.search;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Getter
@ToString
public class CartasDoBaralhoDto implements Serializable {

    private Boolean sucesso;

    @JsonProperty("deck_id")
    private String deckId;

    private List<CartaBaralhoDto> cards;
    private int remaining;
}
