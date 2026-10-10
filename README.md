# Padrões de Projeto na prática: GoF + Spring

Projeto desenvolvido para o desafio da DIO sobre **Padrões de Projeto**. É uma API REST de
**agendamento de barbearia** (simplificada) feita com **Java 17 + Spring Boot**, onde cada
decisão de design usa um padrão clássico do GoF ou uma abstração do Spring.

## Tecnologias

- Java 17
- Spring Boot 3 (Web, Data JPA, Validation)
- H2 (banco em memória)
- JUnit 5 + AssertJ

## Como rodar

```bash
mvn spring-boot:run
```

A API sobe em `http://localhost:8080`. Console do H2: `http://localhost:8080/h2-console`
(JDBC URL: `jdbc:h2:mem:barbearia`, usuário `sa`, senha vazia).

Rodar os testes:

```bash
mvn test
```

## Endpoints

| Método | Rota                 | Descrição                 |
|--------|----------------------|---------------------------|
| POST   | `/agendamentos`      | Cria um agendamento       |
| GET    | `/agendamentos`      | Lista os agendamentos     |
| GET    | `/agendamentos/{id}` | Busca um agendamento      |

Exemplo:

```bash
curl -X POST http://localhost:8080/agendamentos \
  -H "Content-Type: application/json" \
  -d '{
    "clienteNome": "Gui",
    "clienteEmail": "gui@email.com",
    "tipoServico": "CORTE",
    "dataHora": "2030-12-01T14:30:00",
    "tipoDesconto": "FIDELIDADE"
  }'
```

- `tipoServico`: `CORTE`, `BARBA` ou `COMBO`
- `tipoDesconto` (opcional): `NENHUM`, `FIDELIDADE` (10%) ou `PROMOCIONAL` (20%)

Ao criar, o console mostra os logs `[NOTIFICACAO]` e `[AUDITORIA]`: são os observadores reagindo ao evento.

## Padrões utilizados

### GoF

| Padrão | Categoria | Onde está | Por que usei |
|--------|-----------|-----------|--------------|
| **Strategy** | Comportamental | `strategy/` (`DescontoStrategy` + 3 implementações) | Cada regra de desconto fica isolada. Para criar um novo desconto, basta uma nova classe, sem mexer no serviço. |
| **Factory Method** | Criacional | `factory/` (`ServicoFactory` + `CorteFactory`, `BarbaFactory`, `ComboFactory`) | A criação de cada serviço (nome, preço, duração) fica na fábrica correspondente. O serviço não conhece os detalhes. |
| **Observer** | Comportamental | `event/` (`AgendamentoConfirmadoEvent` + 2 listeners) | O `AgendamentoService` apenas publica o evento. Notificação e auditoria reagem sem que o serviço saiba que existem. |

### Spring Framework

| Abstração | Onde está | O que demonstra |
|-----------|-----------|-----------------|
| **IoC / Injeção de Dependência** | Construtor do `AgendamentoService` | O Spring injeta as dependências e todas as implementações de `ServicoFactory` e `DescontoStrategy` |
| **Singleton** | Beans `@Component`, `@Service` | Escopo padrão do Spring: uma instância por contexto |
| **Observer** | `ApplicationEventPublisher` + `@EventListener` | Eventos nativos do Spring |
| **Repository** | `AgendamentoRepository` (`JpaRepository`) | Spring Data gera a implementação em tempo de execução |
| **Proxy** | `@Transactional` no serviço | O Spring cria um proxy que abre e fecha a transação |

## Estrutura

```
src/main/java/com/gui/padroes/
├── controller/   AgendamentoController
├── dto/          AgendamentoRequest, AgendamentoResponse
├── event/        AgendamentoConfirmadoEvent, NotificacaoListener, AuditoriaListener
├── factory/      ServicoFactory, CorteFactory, BarbaFactory, ComboFactory
├── model/        Agendamento, Servico, TipoServico, TipoDesconto
├── repository/   AgendamentoRepository
├── service/      AgendamentoService
└── strategy/     DescontoStrategy, SemDesconto, DescontoFidelidade, DescontoPromocional
```

## Possíveis evoluções

- Adicionar o padrão **Decorator** para serviços extras (hidratação, sobrancelha)
- Trocar o H2 por PostgreSQL
- Adicionar autenticação com Spring Security

## Autor

Guilherme Vasconcelos ([@GuiVasconcelosDev](https://github.com/GuiVasconcelosDev))
