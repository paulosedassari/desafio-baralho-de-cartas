package br.com.cartas.dto;

import br.com.cartas.enums.StatusAposta;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class RodadasDto implements Serializable {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("mao_vencedora")
    private String maoVencedora;

    @JsonProperty("data_jogo")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataJogo;
}
