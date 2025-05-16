# 🧪 Missão: HelpDesk

Este projeto simula uma **Central de Ajuda Escolar**, onde alunos podem registrar solicitações e o sistema processa essas entradas com regras de negócio e notificações.

Atualmente, o sistema funciona, mas está **acoplado** e **não aplica Design Patterns**.

Seu desafio é refatorar este código aplicando dois dos padrões mais importantes da orientação a objetos: **Chain of Responsibility** e **Observer**.

---

## 🎯 Objetivos do exercício

### ✅ 1. Refatorar a lógica de verificação com **Chain of Responsibility**

Atualmente, várias regras estão implementadas com `if` dentro do `SolicitacaoService`.

Exemplo de regras:
- Solicitação só pode ser feita em **dias úteis**
- Solicitações **urgentes** só podem ser enviadas entre 8h e 22h
- Solicitações do tipo **FINANCEIRO** só podem ser feitas em horário comercial
- A mensagem deve conter pelo menos **10 caracteres**

**Sua missão:** transformar cada uma dessas verificações em um **Handler** encadeado usando Chain of Responsibility.

---

### ✅ 2. Refatorar as notificações com **Observer**

Atualmente, o service chama diretamente os métodos que criam notificações para:
- O **usuário final**
- O **log interno do sistema**

**Sua missão:** criar uma estrutura onde o service **notifica um evento**, e múltiplos **observadores** reagem de forma independente, gerando diferentes tipos de notificações.


Boa sorte e lembre-se: Design Patterns existem para **organizar o código, não para complicá-lo** 😉
