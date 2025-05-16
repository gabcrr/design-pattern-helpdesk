package br.com.joaocarloslima.design_pattern_helpdesk.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Solicitacao {
    
    @Id @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    private String mensagem;

    @Enumerated(EnumType.STRING)
    private TipoSolicitacao tipo; 

    @Enumerated(EnumType.STRING)
    private Urgencia urgencia; 

    
}