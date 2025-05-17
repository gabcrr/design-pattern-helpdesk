package br.com.joaocarloslima.design_pattern_helpdesk.chain;

import br.com.joaocarloslima.design_pattern_helpdesk.model.Solicitacao;

public interface Handler {
    void setProximo(Handler proximo);
    void verificar(Solicitacao solicitacao);
}
