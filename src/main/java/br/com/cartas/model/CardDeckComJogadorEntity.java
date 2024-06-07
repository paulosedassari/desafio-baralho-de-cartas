package br.com.cartas.model;

import br.com.cartas.enums.StatusAposta;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "card_deck_jogador")
public class CardDeckComJogadorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 50)
    @Column(name = "NOME_JOGADOR")
    private String nomeJogador;

    @Column(name = "MAO_DA_APOSTA")
    private int maoDaAposta;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "ACERTO")
    private StatusAposta acerto;

    @NotBlank
    @Size(max = 10)
    @Column(name = "MAO_VENCEDORA")
    private String maoVencedora;

    @NotNull
    @Column(name = "DATA_DO_JOGO")
    private LocalDateTime dataDoJogo;
}
