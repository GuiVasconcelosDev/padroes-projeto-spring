package com.gui.padroes.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String clienteNome;
    private String clienteEmail;

    @Enumerated(EnumType.STRING)
    private TipoServico tipoServico;

    @Enumerated(EnumType.STRING)
    private TipoDesconto tipoDesconto;

    private LocalDateTime dataHora;

    @Column(precision = 10, scale = 2)
    private BigDecimal valorOriginal;

    @Column(precision = 10, scale = 2)
    private BigDecimal valorFinal;

    protected Agendamento() {
        // exigido pelo JPA
    }

    public Agendamento(String clienteNome, String clienteEmail, TipoServico tipoServico,
                       TipoDesconto tipoDesconto, LocalDateTime dataHora,
                       BigDecimal valorOriginal, BigDecimal valorFinal) {
        this.clienteNome = clienteNome;
        this.clienteEmail = clienteEmail;
        this.tipoServico = tipoServico;
        this.tipoDesconto = tipoDesconto;
        this.dataHora = dataHora;
        this.valorOriginal = valorOriginal;
        this.valorFinal = valorFinal;
    }

    public Long getId() { return id; }
    public String getClienteNome() { return clienteNome; }
    public String getClienteEmail() { return clienteEmail; }
    public TipoServico getTipoServico() { return tipoServico; }
    public TipoDesconto getTipoDesconto() { return tipoDesconto; }
    public LocalDateTime getDataHora() { return dataHora; }
    public BigDecimal getValorOriginal() { return valorOriginal; }
    public BigDecimal getValorFinal() { return valorFinal; }
}
