package br.com.joaocarloslima.design_pattern_helpdesk.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Notificacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mensagem;

    @Enumerated(EnumType.STRING)
    private TipoNotificacao tipo;

    public Notificacao() {}

    public Notificacao(TipoNotificacao tipo, String mensagem) {
        this.tipo = tipo;
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }

    public TipoNotificacao getTipo() {
        return tipo;
    }
}
