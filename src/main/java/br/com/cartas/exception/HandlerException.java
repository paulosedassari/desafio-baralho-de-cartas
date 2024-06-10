package br.com.cartas.exception;

import br.com.cartas.dto.error.ErroDto;
import br.com.cartas.dto.error.PadraoErroDto;
import br.com.cartas.util.Constantes;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import static java.lang.String.format;

@ControllerAdvice
public class HandlerException {

    private final Logger logger = LoggerFactory.getLogger(HandlerException.class);

    private ResponseEntity<PadraoErroDto> respostaPadraDeErro(String erro, HttpStatus status, Exception e) {
        PadraoErroDto padraoErroDto = new PadraoErroDto(new ErroDto(status.value(), erro, e.getMessage()));
        return ResponseEntity.status(status).body(padraoErroDto);
    }

    @ExceptionHandler(CardDeckException.class)
    public ResponseEntity<PadraoErroDto> manipuladorCardDeckException(CardDeckException e) {
        logger.error(e.getMessage(), e);
        return this.respostaPadraDeErro(Constantes.ERRO_AO_PROCESSAR_A_OPERACAO, HttpStatus.INTERNAL_SERVER_ERROR, e);
    }

    @ExceptionHandler(DadosInputIncorretoException.class)
    public ResponseEntity<PadraoErroDto> manipuladorDadosInputIncorretoException(DadosInputIncorretoException e) {
        logger.error(e.getMessage(), e);
        return this.respostaPadraDeErro(Constantes.DADOS_DE_INPUT_INVALIDOS, HttpStatus.INTERNAL_SERVER_ERROR, e);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<PadraoErroDto> manipuladorNoHandlerFoundException(NoHandlerFoundException e, HttpServletRequest request) {
        logger.error(e.getMessage(), e);
        return this.respostaPadraDeErro(format(Constantes.RECURSO_NAO_ENCONTRADO, request.getRequestURI()), HttpStatus.NOT_FOUND, e);
    }
}
