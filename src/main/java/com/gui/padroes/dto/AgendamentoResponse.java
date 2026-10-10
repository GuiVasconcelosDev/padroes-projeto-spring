package com.gui.padroes.dto;

import com.gui.padroes.model.Agendamento;
import com.gui.padroes.model.TipoDesconto;
import com.gui.padroes.model.TipoServico;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record AgendamentoResponse(
        Long id,
        String clienteNome,
        String clienteEmail,
        TipoServico tipoServico,
        TipoDesconto tipoDesconto,
        LocalDateTime dataHora,
        BigDecimal valorOriginal,
        BigDecimal valorFinal
) {
    public static AgendamentoResponse from(Agendamento a) {
        return new AgendamentoResponse(
                a.getId(),
                a.getClienteNome(),
                a.getClienteEmail(),
                a.getTipoServico(),
                a.getTipoDesconto(),
                a.getDataHora(),
                a.getValorOriginal(),
                a.getValorFinal()
        );
    }
}
