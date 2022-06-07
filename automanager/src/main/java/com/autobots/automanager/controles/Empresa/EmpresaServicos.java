package com.autobots.automanager.controles.Empresa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autobots.automanager.entidades.Empresa;
import com.autobots.automanager.entidades.Servico;
import com.autobots.automanager.servico.EmpresaServico;

@RestController
@RequestMapping("/empresa/servicos")
public class EmpresaServicos {

  @Autowired
  public EmpresaServico servicoEmpresa;

  @PostMapping("/criar/{razaoSocial}")
  public ResponseEntity<?> criarServico(@RequestBody Servico servico, @PathVariable String razaoSocial) {
    try {
      Empresa empresaAtualizada = servicoEmpresa.criarServico(servico, razaoSocial);

      return new ResponseEntity<>(empresaAtualizada.getServicos(), HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

  }
}
