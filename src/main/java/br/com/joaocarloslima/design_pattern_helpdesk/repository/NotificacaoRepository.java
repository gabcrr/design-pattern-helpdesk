package br.com.joaocarloslima.design_pattern_helpdesk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.joaocarloslima.design_pattern_helpdesk.model.Notificacao;
import br.com.joaocarloslima.design_pattern_helpdesk.model.TipoNotificacao;

public interface NotificacaoRepository extends JpaRepository<Notificacao, Long> {

    List<Notificacao> findByTipo(TipoNotificacao tipo);
        
}
