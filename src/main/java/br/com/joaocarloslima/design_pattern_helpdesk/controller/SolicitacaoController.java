package br.com.joaocarloslima.design_pattern_helpdesk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.joaocarloslima.design_pattern_helpdesk.model.Notificacao;
import br.com.joaocarloslima.design_pattern_helpdesk.model.Solicitacao;
import br.com.joaocarloslima.design_pattern_helpdesk.model.TipoNotificacao;
import br.com.joaocarloslima.design_pattern_helpdesk.model.TipoSolicitacao;
import br.com.joaocarloslima.design_pattern_helpdesk.model.Urgencia;
import br.com.joaocarloslima.design_pattern_helpdesk.repository.NotificacaoRepository;
import br.com.joaocarloslima.design_pattern_helpdesk.service.SolicitacaoService;

@Controller
public class SolicitacaoController {

    @Autowired
    private NotificacaoRepository notificacaoRepository;

    @Autowired
    private SolicitacaoService solicitacaoService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("solicitacao", new Solicitacao());
        model.addAttribute("tipos", TipoSolicitacao.values());
        model.addAttribute("urgencias", Urgencia.values());
        model.addAttribute("notificacoes", notificacaoRepository.findByTipo(TipoNotificacao.USUARIO));
        return "index";
    }

    @PostMapping("/solicitacao")
    public String enviar(@ModelAttribute Solicitacao solicitacao) {
        solicitacaoService.processar(solicitacao);
        return "redirect:/";
    }

    @GetMapping("/log")
    public String verLog(Model model) {
        List<Notificacao> notificacoes = notificacaoRepository.findByTipo(TipoNotificacao.LOG);
        model.addAttribute("notificacoes", notificacoes);
        return "log";
    }
}
