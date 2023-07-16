package br.ufsm.csi.flutter_back.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufsm.csi.flutter_back.db.AnimesDAO;
import br.ufsm.csi.flutter_back.model.Animes;

@RestController
@RequestMapping("/animes")
public class AnimesController {
    @GetMapping
    public ArrayList<Animes> getAnimes(){
        return new AnimesDAO().getAnimes();
    }

    @PostMapping
    public void addAnime(@RequestBody Animes animes){
        if(new AnimesDAO().addAnime(animes)){
            System.out.println("Produto adicionado com sucesso!");
        } else {
            System.out.println("Erro ao adicionar produto!");
        }
    }

    @GetMapping("/{id}")
    public Animes getAnime(@PathVariable int id){
        return new AnimesDAO().getAnime(id);
    }
}
