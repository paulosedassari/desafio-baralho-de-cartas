package br.com.cartas.service.impl;

import br.com.cartas.dto.InformacoesDesafioDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class InformacoesServiceImplTest {

    @Test
    public void testEstruturarInformacoesSobreODesafioReturnsNonNullObject() {
        InformacoesServiceImpl service = new InformacoesServiceImpl();
        InformacoesDesafioDto result = service.estruturarInformacoesSobreODesafio();
        assertNotNull(result);
    }

}