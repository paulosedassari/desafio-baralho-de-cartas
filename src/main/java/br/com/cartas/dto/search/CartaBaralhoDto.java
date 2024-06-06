package br.com.cartas.dto.search;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class CartaBaralhoDto implements Serializable {

    private String code;
    private String image;
    private ImagensCartaDto images;
    private String value;
    private String suit;
}
