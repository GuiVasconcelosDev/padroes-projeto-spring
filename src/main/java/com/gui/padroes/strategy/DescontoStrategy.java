package com.gui.padroes.strategy;

import com.gui.padroes.model.TipoDesconto;

import java.math.BigDecimal;

/**
 * PADRÃO: Strategy (comportamental).
 * Cada implementação encapsula uma regra de desconto diferente.
 */
public interface DescontoStrategy {

    TipoDesconto tipo();

    BigDecimal aplicar(BigDecimal valor);
}
