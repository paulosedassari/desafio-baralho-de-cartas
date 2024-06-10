package br.com.cartas.service.impl;

import br.com.cartas.dto.game.PartidaCartasDto;
import br.com.cartas.dto.game.RetornoDesafioDto;
import br.com.cartas.dto.game.RetornoPartidaCartasDto;
import br.com.cartas.enums.StatusAposta;
import br.com.cartas.exception.DadosInputIncorretoException;
import br.com.cartas.model.CardDeckComJogadorEntity;
import br.com.cartas.model.CardDeckSemJogadorEntity;
import br.com.cartas.service.DesafioCartasService;
import br.com.cartas.service.FormatarRespotaService;
import br.com.cartas.service.JogarDesafioCartasService;
import br.com.cartas.service.RegistrarResultadoNaBase;
import br.com.cartas.util.CommonsUtil;
import br.com.cartas.util.Constantes;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class JogarDesafioCartasServiceImpl implements JogarDesafioCartasService {

    private final FormatarRespotaService formatarRespotaService;
    private final DesafioCartasService desafioCartasService;
    private final RegistrarResultadoNaBase registrarResultadoNaBase;

    public JogarDesafioCartasServiceImpl(FormatarRespotaService formatarRespotaService, DesafioCartasService desafioCartasService, RegistrarResultadoNaBase registrarResultadoNaBase) {
        this.formatarRespotaService = formatarRespotaService;
        this.desafioCartasService = desafioCartasService;
        this.registrarResultadoNaBase = registrarResultadoNaBase;
    }

    @Override
    public RetornoDesafioDto jogarSemParticipante() {
        Map<String, Integer> desafioRealizado = desafioCartasService.realizarDesafio();
        Map<String, Integer> maoVencedora = CommonsUtil.selecionarMaoComMaiorPontuacao(desafioRealizado);
        RetornoDesafioDto mensagemComOResultado = formatarRespotaService.formatarRespostaSemOVencedorDoDesafio(maoVencedora, desafioRealizado);
        registrarResultadoNaBase.salvarRegistroSemJogador(new CardDeckSemJogadorEntity(null, mensagemComOResultado.getMaoVencedora(), LocalDateTime.now()));

        return mensagemComOResultado;
    }

    @Override
    public RetornoPartidaCartasDto jogarComParticipante(PartidaCartasDto partida) {

        validarInputDaMaoDaAposta(partida);

        Map<String, Integer> desafioRealizado = desafioCartasService.realizarDesafio();
        RetornoPartidaCartasDto respostaFormatadaComVencedor = formatarRespotaService.formatarRespostaComOVencedorDoDesafio(desafioRealizado, partida);
        registrarResultadoDoJogoComJogadorNaBase(partida, respostaFormatadaComVencedor.getResultado(), respostaFormatadaComVencedor.getMaoVencedora());

        return respostaFormatadaComVencedor;
    }

    private void registrarResultadoDoJogoComJogadorNaBase(PartidaCartasDto partida, String resultado, String jogadorVencedor) {
        if (resultado.contains(Constantes.PARABENS_ACERTOU_A_MAO_VENCEDORA)) {
            registrarResultadoNaBase.salvarRegistroComJogador(new CardDeckComJogadorEntity(null, partida.getNomeJogador(), partida.getMaoDaAposta(), StatusAposta.ACERTOU, jogadorVencedor, LocalDateTime.now()));
        } else {
            registrarResultadoNaBase.salvarRegistroComJogador(new CardDeckComJogadorEntity(null, partida.getNomeJogador(), partida.getMaoDaAposta(), StatusAposta.ERROU, jogadorVencedor, LocalDateTime.now()));
        }
    }

    private static void validarInputDaMaoDaAposta(PartidaCartasDto partida) {
        if (!CommonsUtil.opcoesValidasParaAsMaosDasApostas().contains(partida.getMaoDaAposta()))
            throw new DadosInputIncorretoException(Constantes.VALOR_INVALIDO_MAO_DA_APOSTA);
    }
}
