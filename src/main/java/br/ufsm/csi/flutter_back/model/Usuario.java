package br.ufsm.csi.flutter_back.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {
    private int codigo;
    private String nome;
    private String email;
    private String senha;
    private String token;
    private String permissao;
}
