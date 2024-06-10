package br.com.cartas.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty("bem_vindo")
    private String bemVindo;

    @JsonProperty("infos_sobre_api")
    private String infosSobreApi;

    @JsonProperty("opca_sem_jogador")
    private String opcaoSemJogador;

    @JsonProperty("opcao_com_jogador")
    private String opcaoComJogador;

    @JsonProperty("consulta_aos_registros")
    private String consultaAosRegistros;
}
