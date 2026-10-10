package com.gui.padroes.strategy;

import com.gui.padroes.model.TipoDesconto;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

/** 20% de desconto em campanhas promocionais. */
@Component
public class DescontoPromocional implements DescontoStrategy {

    private static final BigDecimal FATOR = new BigDecimal("0.80");

    @Override
    public TipoDesconto tipo() {
        return TipoDesconto.PROMOCIONAL;
    }

    @Override
    public BigDecimal aplicar(BigDecimal valor) {
        return valor.multiply(FATOR).setScale(2, RoundingMode.HALF_UP);
    }
}
