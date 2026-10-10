package com.gui.padroes.factory;

import com.gui.padroes.model.Servico;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ServicoFactoryTest {

    @Test
    void corteFactoryCriaCorte() {
        Servico s = new CorteFactory().criarServico();
        assertThat(s.nome()).isEqualTo("Corte");
        assertThat(s.preco()).isEqualByComparingTo("40.00");
    }

    @Test
    void comboFactoryCriaCombo() {
        Servico s = new ComboFactory().criarServico();
        assertThat(s.nome()).isEqualTo("Corte + Barba");
        assertThat(s.duracaoMinutos()).isEqualTo(50);
    }

    @Test
    void resumoUsaOProdutoCriado() {
        assertThat(new BarbaFactory().resumo()).contains("Barba").contains("30.00");
    }
}
