package com.gui.padroes.service;

import com.gui.padroes.dto.AgendamentoRequest;
import com.gui.padroes.event.AgendamentoConfirmadoEvent;
import com.gui.padroes.factory.ServicoFactory;
import com.gui.padroes.model.Agendamento;
import com.gui.padroes.model.Servico;
import com.gui.padroes.model.TipoDesconto;
import com.gui.padroes.model.TipoServico;
import com.gui.padroes.repository.AgendamentoRepository;
import com.gui.padroes.strategy.DescontoStrategy;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Orquestra os padrões:
 * - Factory Method: cria o Servico escolhido
 * - Strategy: aplica o desconto escolhido
 * - Observer: publica o evento de confirmação
 * - Singleton + DI: este bean é único e recebe tudo por construtor (IoC do Spring)
 * - Proxy: @Transactional funciona via proxy criado pelo Spring
 */
@Service
public class AgendamentoService {

    private final AgendamentoRepository repository;
    private final ApplicationEventPublisher publisher;
    private final Map<TipoServico, ServicoFactory> fabricas;
    private final Map<TipoDesconto, DescontoStrategy> descontos;

    // O Spring injeta TODAS as implementações encontradas nas listas.
    public AgendamentoService(AgendamentoRepository repository,
                              ApplicationEventPublisher publisher,
                              List<ServicoFactory> fabricas,
                              List<DescontoStrategy> descontos) {
        this.repository = repository;
        this.publisher = publisher;
        this.fabricas = fabricas.stream()
                .collect(Collectors.toMap(ServicoFactory::tipo, Function.identity()));
        this.descontos = descontos.stream()
                .collect(Collectors.toMap(DescontoStrategy::tipo, Function.identity()));
    }

    @Transactional
    public Agendamento agendar(AgendamentoRequest request) {
        Servico servico = fabricas.get(request.tipoServico()).criarServico();

        TipoDesconto tipoDesconto = request.tipoDesconto() != null
                ? request.tipoDesconto()
                : TipoDesconto.NENHUM;
        BigDecimal valorFinal = descontos.get(tipoDesconto).aplicar(servico.preco());

        Agendamento salvo = repository.save(new Agendamento(
                request.clienteNome(),
                request.clienteEmail(),
                request.tipoServico(),
                tipoDesconto,
                request.dataHora(),
                servico.preco(),
                valorFinal
        ));

        publisher.publishEvent(new AgendamentoConfirmadoEvent(
                salvo.getId(), salvo.getClienteNome(), salvo.getClienteEmail(), salvo.getDataHora()));

        return salvo;
    }

    @Transactional(readOnly = true)
    public List<Agendamento> listar() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Agendamento buscar(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Agendamento não encontrado"));
    }
}
