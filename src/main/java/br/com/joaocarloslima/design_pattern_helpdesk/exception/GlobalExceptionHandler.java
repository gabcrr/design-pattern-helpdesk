package br.com.joaocarloslima.design_pattern_helpdesk.exception;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SolicitacaoInvalidaException.class)
    public String tratarErroRegra(SolicitacaoInvalidaException ex, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("erro", ex.getMessage());
        return "redirect:/";
    }
}