package br.com.cartas.service.impl;

import br.com.cartas.dto.InformacoesDesafioDto;
import br.com.cartas.service.InformacoesService;
import org.springframework.stereotype.Service;

@Service
public class InformacoesServiceImpl implements InformacoesService {

    @Override
    public InformacoesDesafioDto estruturarInformacoesSobreODesafio() {
        return new InformacoesDesafioDto(
                "Bem-Vindo ao Desafio do Baralho de Cartas!",
                "Nosso serviço possui duas opções de jogo, explicadas abaixo.",
                "1- Você solicita uma rodada, mas não participa.",
                "2- Você solicita uma rodada e participa informando seu nome e a mão que vai apostar, sendo as opções de mãos de 1 a 4.",
                "Você também pode consultar os registros dos jogos chamando os endpoints /rodadas/sem-jogador e /rodadas/com-jogador."
        );
    }
}
