package com.gui.padroes.factory;

import com.gui.padroes.model.Servico;
import com.gui.padroes.model.TipoServico;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ComboFactory extends ServicoFactory {

    @Override
    public TipoServico tipo() {
        return TipoServico.COMBO;
    }

    @Override
    protected Servico fabricar() {
        return new Servico("Corte + Barba", new BigDecimal("60.00"), 50);
    }
}
