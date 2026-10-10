package com.gui.padroes.service;

import com.gui.padroes.dto.AgendamentoRequest;
import com.gui.padroes.model.Agendamento;
import com.gui.padroes.model.TipoDesconto;
import com.gui.padroes.model.TipoServico;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AgendamentoServiceTest {

    @Autowired
    private AgendamentoService service;

    @Test
    void agendaCorteComDescontoFidelidade() {
        Agendamento a = service.agendar(new AgendamentoRequest(
                "Gui", "gui@email.com", TipoServico.CORTE,
                LocalDateTime.now().plusDays(1), TipoDesconto.FIDELIDADE));

        assertThat(a.getId()).isNotNull();
        assertThat(a.getValorOriginal()).isEqualByComparingTo("40.00");
        assertThat(a.getValorFinal()).isEqualByComparingTo("36.00");
    }

    @Test
    void semDescontoInformadoUsaValorCheio() {
        Agendamento a = service.agendar(new AgendamentoRequest(
                "Gui", "gui@email.com", TipoServico.COMBO,
                LocalDateTime.now().plusDays(2), null));

        assertThat(a.getTipoDesconto()).isEqualTo(TipoDesconto.NENHUM);
        assertThat(a.getValorFinal()).isEqualByComparingTo("60.00");
    }
}
