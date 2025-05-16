package br.com.joaocarloslima.design_pattern_helpdesk.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.joaocarloslima.design_pattern_helpdesk.exception.SolicitacaoInvalidaException;
import br.com.joaocarloslima.design_pattern_helpdesk.model.Notificacao;
import br.com.joaocarloslima.design_pattern_helpdesk.model.Solicitacao;
import br.com.joaocarloslima.design_pattern_helpdesk.model.TipoNotificacao;
import br.com.joaocarloslima.design_pattern_helpdesk.model.TipoSolicitacao;
import br.com.joaocarloslima.design_pattern_helpdesk.model.Urgencia;
import br.com.joaocarloslima.design_pattern_helpdesk.repository.NotificacaoRepository;
import br.com.joaocarloslima.design_pattern_helpdesk.repository.SolicitacaoRepository;

@Service
public class SolicitacaoService {

    @Autowired
    private SolicitacaoRepository solicitacaoRepository;

    @Autowired
    private NotificacaoRepository notificacaoRepository;

    public void processar(Solicitacao solicitacao) {

        // 🔗 Lógica acoplada (refatore aplicando o Design Pattern Chain of Respondability)
        
        // Mensagem deve ter no mínimo 10 caracteres
        if (solicitacao.getMensagem() == null || solicitacao.getMensagem().length() < 10) {
            throw new SolicitacaoInvalidaException("Mensagem deve ter no mínimo 10 caracteres");
        }
        
        // Dia útil?
        // Para testar, altere o dia da semana
        // Exemplo: hoje = DayOfWeek.SATURDAY;
        DayOfWeek hoje = LocalDate.now().getDayOfWeek();
        if (hoje == DayOfWeek.SATURDAY || hoje == DayOfWeek.SUNDAY) {
            throw new SolicitacaoInvalidaException("Solicitação não pode ser processada em fins de semana");
        }

        // Horário
        // Para testar, altere o horário
        // Exemplo: agora = LocalTime.of(23, 0);
        LocalTime agora = LocalTime.now();

        // Urgência fora do horário → recusa
        if (solicitacao.getUrgencia() == Urgencia.ALTA &&
            (agora.isBefore(LocalTime.of(8, 0)) || agora.isAfter(LocalTime.of(22, 0)))) {
            throw new SolicitacaoInvalidaException("Solicitação de urgência alta não pode ser processada fora do horário");
        }

        // Financeiro só em horário comercial
        if (solicitacao.getTipo() == TipoSolicitacao.FINANCEIRO &&
            (agora.isBefore(LocalTime.of(8, 0)) || agora.isAfter(LocalTime.of(18, 0)))) {
            throw new SolicitacaoInvalidaException("Solicitação financeira só pode ser processada em horário comercial");
        }

        solicitacaoRepository.save(solicitacao);

        // 👀 Lógica acoplada de notificação (refatore aplicando o Design Pattern Observer)
        Notificacao paraUsuario = Notificacao.builder()
            .tipo(TipoNotificacao.USUARIO)
            .mensagem("Solicitação registrada: " + solicitacao.getMensagem())
            .build();

        notificacaoRepository.save(paraUsuario);

        Notificacao paraLog = Notificacao.builder()
            .tipo(TipoNotificacao.LOG)
            .mensagem("Solicitação registrada: " + solicitacao.getMensagem())
            .build();
            
        notificacaoRepository.save(paraLog);
    }
}