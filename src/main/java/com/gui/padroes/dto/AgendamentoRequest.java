package com.gui.padroes.dto;

import com.gui.padroes.model.TipoDesconto;
import com.gui.padroes.model.TipoServico;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AgendamentoRequest(
        @NotBlank String clienteNome,
        @NotBlank @Email String clienteEmail,
        @NotNull TipoServico tipoServico,
        @NotNull @Future LocalDateTime dataHora,
        TipoDesconto tipoDesconto
) {
}
