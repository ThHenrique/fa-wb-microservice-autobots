package com.autobots.automanager.controles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autobots.automanager.entidades.Venda;
import com.autobots.automanager.servico.VendaServico;

@RestController
@RequestMapping("/vendas")
public class VendaControle {

  @Autowired
  private VendaServico servicoVenda;

  @PostMapping("/cadastrar/{idEmpresa}")
  public ResponseEntity<?> criarVenda(@RequestBody Venda venda, @PathVariable Long idEmpresa) {
    try {
      servicoVenda.criarVenda(venda, idEmpresa);

      return new ResponseEntity<>(HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }
}
