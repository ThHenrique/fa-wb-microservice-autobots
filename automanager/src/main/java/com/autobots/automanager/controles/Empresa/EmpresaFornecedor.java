package com.autobots.automanager.controles.Empresa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autobots.automanager.controles.dtos.CriarFornecedorDTO;
import com.autobots.automanager.servico.EmpresaServico;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/empresa/fornecedor")
public class EmpresaFornecedor {
  @Autowired
  EmpresaServico servicoEmpresa;

  @PostMapping("/criar")
  public ResponseEntity<?> cadastrarFornecedor(@RequestBody CriarFornecedorDTO fornecedorDTO) {
    try {
      servicoEmpresa.criarFornecedor(fornecedorDTO);

      return new ResponseEntity<>(HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

  }
}
