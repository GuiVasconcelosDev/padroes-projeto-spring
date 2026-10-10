package com.gui.padroes.event;

import java.time.LocalDateTime;

/**
 * Evento publicado quando um agendamento é confirmado.
 * É a "notificação" do padrão Observer.
 */
public record AgendamentoConfirmadoEvent(
        Long agendamentoId,
        String clienteNome,
        String clienteEmail,
        LocalDateTime dataHora
) {
}
