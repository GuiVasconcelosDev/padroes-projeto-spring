package com.gui.padroes.strategy;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class DescontoStrategyTest {

    @Test
    void semDescontoMantemValor() {
        assertThat(new SemDesconto().aplicar(new BigDecimal("50.00"))).isEqualByComparingTo("50.00");
    }

    @Test
    void fidelidadeAplica10Porcento() {
        assertThat(new DescontoFidelidade().aplicar(new BigDecimal("50.00"))).isEqualByComparingTo("45.00");
    }

    @Test
    void promocionalAplica20Porcento() {
        assertThat(new DescontoPromocional().aplicar(new BigDecimal("50.00"))).isEqualByComparingTo("40.00");
    }
}
