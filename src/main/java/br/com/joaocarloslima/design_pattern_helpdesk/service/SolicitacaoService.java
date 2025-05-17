package br.com.joaocarloslima.design_pattern_helpdesk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.joaocarloslima.design_pattern_helpdesk.chain.DiaUtilHandler;
import br.com.joaocarloslima.design_pattern_helpdesk.chain.FinanceiroHorarioHandler;
import br.com.joaocarloslima.design_pattern_helpdesk.chain.Handler;
import br.com.joaocarloslima.design_pattern_helpdesk.chain.MensagemTamanhoHandler;
import br.com.joaocarloslima.design_pattern_helpdesk.chain.UrgenciaHorarioHandler;
import br.com.joaocarloslima.design_pattern_helpdesk.model.Solicitacao;
import br.com.joaocarloslima.design_pattern_helpdesk.observer.LogObserver;
import br.com.joaocarloslima.design_pattern_helpdesk.observer.Notificador;
import br.com.joaocarloslima.design_pattern_helpdesk.observer.UsuarioObserver;
import br.com.joaocarloslima.design_pattern_helpdesk.repository.SolicitacaoRepository;

@Service
public class SolicitacaoService {

    @Autowired
    private SolicitacaoRepository solicitacaoRepository;

    @Autowired
    private Notificador notificador;

    @Autowired
    private UsuarioObserver usuarioObserver;

    @Autowired
    private LogObserver logObserver;

    @jakarta.annotation.PostConstruct
    public void initObservers() {
        notificador.adicionarObservador(usuarioObserver);
        notificador.adicionarObservador(logObserver);
    }

    public void processar(Solicitacao solicitacao) {
        Handler mensagemHandler = new MensagemTamanhoHandler();
        Handler diaUtilHandler = new DiaUtilHandler();
        Handler urgenciaHandler = new UrgenciaHorarioHandler();
        Handler financeiroHandler = new FinanceiroHorarioHandler();

        mensagemHandler.setProximo(diaUtilHandler);
        diaUtilHandler.setProximo(urgenciaHandler);
        urgenciaHandler.setProximo(financeiroHandler);

        mensagemHandler.verificar(solicitacao);

        solicitacaoRepository.save(solicitacao);
        notificador.notificar(solicitacao);
    }
}