package br.com.joaocarloslima.design_pattern_helpdesk.chain;

import java.time.DayOfWeek;
import java.time.LocalDate;

import br.com.joaocarloslima.design_pattern_helpdesk.exception.SolicitacaoInvalidaException;
import br.com.joaocarloslima.design_pattern_helpdesk.model.Solicitacao;

public class DiaUtilHandler implements Handler {
    private Handler proximo;

    public void setProximo(Handler proximo) {
        this.proximo = proximo;
    }

    public void verificar(Solicitacao solicitacao) {
        DayOfWeek hoje = LocalDate.now().getDayOfWeek();
        if (hoje == DayOfWeek.SATURDAY || hoje == DayOfWeek.SUNDAY) {
            throw new SolicitacaoInvalidaException("Solicitação não pode ser processada em fins de semana");
        }
        if (proximo != null) {
            proximo.verificar(solicitacao);
        }
    }
}
