package br.com.cartas.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class InformacoesDesafioDto implements Serializable {

    private String bemVindo;
    private String infosSobreApi;
    private String opcaoSemJogador;
    private String opcaoComJogador;
    private String consultaAosRegistros;
}
