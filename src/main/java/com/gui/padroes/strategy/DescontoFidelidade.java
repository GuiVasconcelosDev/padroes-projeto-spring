package com.gui.padroes.strategy;

import com.gui.padroes.model.TipoDesconto;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

/** 10% de desconto para clientes fiéis. */
@Component
public class DescontoFidelidade implements DescontoStrategy {

    private static final BigDecimal FATOR = new BigDecimal("0.90");

    @Override
    public TipoDesconto tipo() {
        return TipoDesconto.FIDELIDADE;
    }

    @Override
    public BigDecimal aplicar(BigDecimal valor) {
        return valor.multiply(FATOR).setScale(2, RoundingMode.HALF_UP);
    }
}
