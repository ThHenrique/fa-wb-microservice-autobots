package com.autobots.automanager.servico;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.entidades.Empresa;
import com.autobots.automanager.entidades.Servico;
import com.autobots.automanager.repositorios.EmpresaRepositorio;
import com.autobots.automanager.repositorios.ServicoRepositorio;

@Service
public class ServicoEmpServico {

  @Autowired
  ServicoRepositorio repositorioServico;

  @Autowired
  EmpresaRepositorio repositorioEmpresa;

  @Autowired
  EmpresaServico servicoEmpresa;

  public Empresa criarServico(Servico servico, String razaoSocial) {
    Empresa empresa = servicoEmpresa.encontrarEmpresa(razaoSocial);

    if (empresa == null) {
      new Exception("Não foi possível encontrar empresa, tente novamente");
    }

    empresa.getServicos().add(servico);

    return repositorioEmpresa.save(empresa);
  }

  public Servico encontrarServico(Long id) {
    Optional<Servico> servico = repositorioServico.findById(id);

    if (servico.isEmpty()) {
      return null;
    }

    return servico.get();
  }
}
