package br.ufsm.csi.flutter_back.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Categoria {
    private int codigo;
    private Animes animes;
    private String descricao;
    
}
