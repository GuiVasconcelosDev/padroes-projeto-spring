package com.gui.padroes.factory;

import com.gui.padroes.model.Servico;
import com.gui.padroes.model.TipoServico;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class BarbaFactory extends ServicoFactory {

    @Override
    public TipoServico tipo() {
        return TipoServico.BARBA;
    }

    @Override
    protected Servico fabricar() {
        return new Servico("Barba", new BigDecimal("30.00"), 20);
    }
}
