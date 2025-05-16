package br.com.joaocarloslima.design_pattern_helpdesk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.joaocarloslima.design_pattern_helpdesk.model.Solicitacao;

public interface SolicitacaoRepository extends JpaRepository<Solicitacao, Long> {
    
}
