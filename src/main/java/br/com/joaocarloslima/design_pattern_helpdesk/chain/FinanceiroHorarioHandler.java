package br.com.joaocarloslima.design_pattern_helpdesk.chain;

import java.time.LocalTime;

import br.com.joaocarloslima.design_pattern_helpdesk.exception.SolicitacaoInvalidaException;
import br.com.joaocarloslima.design_pattern_helpdesk.model.Solicitacao;
import br.com.joaocarloslima.design_pattern_helpdesk.model.TipoSolicitacao;

public class FinanceiroHorarioHandler implements Handler {
    private Handler proximo;

    public void setProximo(Handler proximo) {
        this.proximo = proximo;
    }

    public void verificar(Solicitacao solicitacao) {
        LocalTime agora = LocalTime.now();
        if (solicitacao.getTipo() == TipoSolicitacao.FINANCEIRO &&
                (agora.isBefore(LocalTime.of(8, 0)) || agora.isAfter(LocalTime.of(18, 0)))) {
            throw new SolicitacaoInvalidaException("Solicitação financeira só pode ser processada em horário comercial");
        }
        if (proximo != null) {
            proximo.verificar(solicitacao);
        }
    }
}
