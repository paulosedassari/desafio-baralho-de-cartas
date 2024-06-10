package br.com.cartas.dto.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class PadraoErroDto implements Serializable {

    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    private ErroDto erro;
}
