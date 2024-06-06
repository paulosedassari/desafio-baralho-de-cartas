package br.com.cartas.dto.game;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class RetornoDesafioDto implements Serializable {

    private String resultado;
}
