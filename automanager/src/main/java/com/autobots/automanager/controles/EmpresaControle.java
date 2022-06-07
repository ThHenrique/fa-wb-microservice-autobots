package com.autobots.automanager.controles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autobots.automanager.controles.dtos.CriarEmpresaDTO;
import com.autobots.automanager.entidades.Empresa;
import com.autobots.automanager.servico.EmpresaServico;

@RestController
@RequestMapping("/empresa")
public class EmpresaControle {

  @Autowired
  public EmpresaServico servicoEmpresa;

  @PostMapping("/criar")
  public ResponseEntity<Empresa> criarEmpresa(@RequestBody CriarEmpresaDTO empresa) {

    try {
      Empresa novaEmpresa = servicoEmpresa.criarEmpresa(empresa);

      return new ResponseEntity<Empresa>(novaEmpresa, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<Empresa>(HttpStatus.BAD_REQUEST);
    }

  }

  @GetMapping("/mostrar/{razaoSocial}")
  public ResponseEntity<Empresa> mostrarEmpresa(@PathVariable String razaoSocial) {
    Empresa empresa = servicoEmpresa.encontrarEmpresa(razaoSocial);

    if (empresa == null) {
      return new ResponseEntity<Empresa>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<Empresa>(empresa, HttpStatus.FOUND);
  }

}
