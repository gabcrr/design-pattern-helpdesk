package br.com.joaocarloslima.design_pattern_helpdesk.observer;

import java.util.ArrayList;
import java.util.List;

import br.com.joaocarloslima.design_pattern_helpdesk.model.Solicitacao;
import org.springframework.stereotype.Component;

@Component
public class Notificador {
    private List<Observer> observadores = new ArrayList<>();

    public void adicionarObservador(Observer observador) {
        observadores.add(observador);
    }

    public void notificar(Solicitacao solicitacao) {
        for (Observer observador : observadores) {
            observador.notificar(solicitacao);
        }
    }
}
