package com.gui.padroes.strategy;

import com.gui.padroes.model.TipoDesconto;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class SemDesconto implements DescontoStrategy {

    @Override
    public TipoDesconto tipo() {
        return TipoDesconto.NENHUM;
    }

    @Override
    public BigDecimal aplicar(BigDecimal valor) {
        return valor;
    }
}
