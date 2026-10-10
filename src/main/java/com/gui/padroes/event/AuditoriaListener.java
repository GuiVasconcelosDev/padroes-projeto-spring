package com.gui.padroes.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * PADRÃO: Observer (comportamental), via eventos do Spring.
 * Observador 2: registra o evento para auditoria.
 * O serviço que publica o evento não conhece nenhum dos observadores.
 */
@Component
public class AuditoriaListener {

    private static final Logger log = LoggerFactory.getLogger(AuditoriaListener.class);

    @EventListener
    public void aoConfirmar(AgendamentoConfirmadoEvent event) {
        log.info("[AUDITORIA] Agendamento #{} criado para o cliente {}", event.agendamentoId(), event.clienteEmail());
    }
}
