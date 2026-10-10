package com.gui.padroes.factory;

import com.gui.padroes.model.Servico;
import com.gui.padroes.model.TipoServico;

/**
 * PADRÃO: Factory Method (criacional).
 * A classe "Creator" define o método de fábrica abstrato (fabricar) e
 * operações que usam o produto (resumo). Cada subclasse decide qual
 * Servico concreto criar.
 */
public abstract class ServicoFactory {

    /** Qual tipo de serviço esta fábrica produz (usado para localizar a fábrica certa). */
    public abstract TipoServico tipo();

    /** Factory Method: subclasses decidem o que criar. */
    protected abstract Servico fabricar();

    public Servico criarServico() {
        return fabricar();
    }

    public String resumo() {
        Servico s = criarServico();
        return "%s - R$ %s (%d min)".formatted(s.nome(), s.preco(), s.duracaoMinutos());
    }
}
