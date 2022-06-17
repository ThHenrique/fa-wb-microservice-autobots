package com.autobots.automanager.controles.Empresa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autobots.automanager.servico.EmpresaServico;

@RestController
@RequestMapping("/empresa/funcionario")
public class EmpresaFuncionario {
  @Autowired
  EmpresaServico servicoEmpresa;

}
