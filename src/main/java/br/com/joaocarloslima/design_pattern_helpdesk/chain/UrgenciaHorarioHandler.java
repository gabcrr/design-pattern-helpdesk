package br.com.joaocarloslima.design_pattern_helpdesk.chain;

import java.time.LocalTime;

import br.com.joaocarloslima.design_pattern_helpdesk.exception.SolicitacaoInvalidaException;
import br.com.joaocarloslima.design_pattern_helpdesk.model.Solicitacao;
import br.com.joaocarloslima.design_pattern_helpdesk.model.Urgencia;

public class UrgenciaHorarioHandler implements Handler {
    private Handler proximo;

    public void setProximo(Handler proximo) {
        this.proximo = proximo;
    }

    public void verificar(Solicitacao solicitacao) {
        LocalTime agora = LocalTime.now();
        if (solicitacao.getUrgencia() == Urgencia.ALTA &&
                (agora.isBefore(LocalTime.of(8, 0)) || agora.isAfter(LocalTime.of(22, 0)))) {
            throw new SolicitacaoInvalidaException("Solicitação de urgência alta não pode ser processada fora do horário");
        }
        if (proximo != null) {
            proximo.verificar(solicitacao);
        }
    }
}
