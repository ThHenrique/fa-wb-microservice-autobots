package com.autobots.automanager.controles.Empresa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autobots.automanager.controles.dtos.CriarMercadoriaDTO;
import com.autobots.automanager.entidades.Empresa;
import com.autobots.automanager.servico.MercadoriaServico;

@RestController
@RequestMapping("/empresa/mercadorias")
public class EmpresaMercadorias {

  @Autowired
  public MercadoriaServico servicoMercadoria;

  @PostMapping("/criar")
  public ResponseEntity<?> cadastrarMercadoria(@RequestBody CriarMercadoriaDTO mercadoria) {
    try {
      Empresa novaMercadoria = servicoMercadoria.criarMercadoria(mercadoria);

      return new ResponseEntity<>(novaMercadoria.getMercadorias(), HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }
}
