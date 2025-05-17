package br.com.joaocarloslima.design_pattern_helpdesk.observer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.joaocarloslima.design_pattern_helpdesk.model.Notificacao;
import br.com.joaocarloslima.design_pattern_helpdesk.model.Solicitacao;
import br.com.joaocarloslima.design_pattern_helpdesk.model.TipoNotificacao;
import br.com.joaocarloslima.design_pattern_helpdesk.repository.NotificacaoRepository;

@Component
public class UsuarioObserver implements Observer {
    @Autowired
    private NotificacaoRepository notificacaoRepository;

    public void notificar(Solicitacao solicitacao) {
        Notificacao notificacao = new Notificacao(TipoNotificacao.USUARIO, "Solicitação registrada: " + solicitacao.getMensagem());
        notificacaoRepository.save(notificacao);
    }
}