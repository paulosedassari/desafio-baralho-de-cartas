package br.com.cartas.dto.create;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@ToString
public class CriarBaralhoDto implements Serializable {

    private int deck_count;
}
