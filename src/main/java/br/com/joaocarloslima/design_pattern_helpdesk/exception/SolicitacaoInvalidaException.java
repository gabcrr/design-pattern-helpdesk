package br.com.joaocarloslima.design_pattern_helpdesk.exception;

public class SolicitacaoInvalidaException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public SolicitacaoInvalidaException(String mensagem) {
        super(mensagem);
    }

    public SolicitacaoInvalidaException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
    
}
