package br.com.cartas.dto.search;

import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@ToString
public class ImagensCartaDto implements Serializable {

    private String svg;
    private String png;
}
