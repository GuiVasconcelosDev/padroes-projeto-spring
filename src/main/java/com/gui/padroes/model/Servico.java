package com.gui.padroes.model;

import java.math.BigDecimal;

/**
 * Produto criado pelo Factory Method.
 */
public record Servico(String nome, BigDecimal preco, int duracaoMinutos) {
}
