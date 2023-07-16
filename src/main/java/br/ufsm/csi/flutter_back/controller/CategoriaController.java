package br.ufsm.csi.flutter_back.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufsm.csi.flutter_back.db.CategoriaDAO;
import br.ufsm.csi.flutter_back.model.Categoria;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {
    @GetMapping
    public ArrayList<Categoria> getCategorias(){
        ArrayList<Categoria> categorias = new CategoriaDAO().getCategorias();
        return categorias;
    }

    @PostMapping
    public void addCategoria(@RequestBody Categoria categoria){
        if(new CategoriaDAO().addCategoria(categoria)){
            System.out.println("Categoria adicionado com sucesso!");
        } else {
            System.out.println("Erro ao adicionar categoria!");
        }
    }

    @GetMapping("/{id}")
    public Categoria getCategoria(int codigo){
        return new CategoriaDAO().getCategoria(codigo);
    }
}
