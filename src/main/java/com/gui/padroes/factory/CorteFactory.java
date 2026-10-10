package com.gui.padroes.factory;

import com.gui.padroes.model.Servico;
import com.gui.padroes.model.TipoServico;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CorteFactory extends ServicoFactory {

    @Override
    public TipoServico tipo() {
        return TipoServico.CORTE;
    }

    @Override
    protected Servico fabricar() {
        return new Servico("Corte", new BigDecimal("40.00"), 30);
    }
}
