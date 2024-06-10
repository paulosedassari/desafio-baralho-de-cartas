package br.com.cartas.dto.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class ErroDto implements Serializable {

    private int codigo;
    private String mensagem;
    private String detalhes;
}
