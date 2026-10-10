package com.gui.padroes.controller;

import com.gui.padroes.dto.AgendamentoRequest;
import com.gui.padroes.dto.AgendamentoResponse;
import com.gui.padroes.service.AgendamentoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {

    private final AgendamentoService service;

    public AgendamentoController(AgendamentoService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AgendamentoResponse criar(@Valid @RequestBody AgendamentoRequest request) {
        return AgendamentoResponse.from(service.agendar(request));
    }

    @GetMapping
    public List<AgendamentoResponse> listar() {
        return service.listar().stream().map(AgendamentoResponse::from).toList();
    }

    @GetMapping("/{id}")
    public AgendamentoResponse buscar(@PathVariable Long id) {
        return AgendamentoResponse.from(service.buscar(id));
    }
}
