package com.gui.padroes.repository;

import com.gui.padroes.model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * PADRÃO: Repository (abstração do Spring Data).
 * O Spring gera a implementação em tempo de execução.
 */
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
}
