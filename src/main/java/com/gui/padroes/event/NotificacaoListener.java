package com.gui.padroes.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * PADRÃO: Observer (comportamental), via eventos do Spring.
 * Observador 1: envia (simula) a notificação ao cliente.
 */
@Component
public class NotificacaoListener {

    private static final Logger log = LoggerFactory.getLogger(NotificacaoListener.class);

    @EventListener
    public void aoConfirmar(AgendamentoConfirmadoEvent event) {
        log.info("[NOTIFICACAO] E-mail simulado para {} <{}>: agendamento #{} confirmado para {}",
                event.clienteNome(), event.clienteEmail(), event.agendamentoId(), event.dataHora());
    }
}
