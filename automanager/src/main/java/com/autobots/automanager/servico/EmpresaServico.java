package com.autobots.automanager.servico;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.controles.dtos.CriarEmpresaDTO;
import com.autobots.automanager.controles.dtos.CriarMercadoriaDTO;
import com.autobots.automanager.entidades.Empresa;
import com.autobots.automanager.entidades.Servico;
import com.autobots.automanager.repositorios.EmpresaRepositorio;

@Service
public class EmpresaServico {

  @Autowired
  private EmpresaRepositorio repositorioEmpresa;

  public Empresa criarEmpresa(CriarEmpresaDTO empresaDTO) {
    empresaDTO.getEmpresa().setCadastro(new Date());
    empresaDTO.getEmpresa().setEndereco(empresaDTO.getEndereco());

    empresaDTO.getTelefones().forEach(novoTelefone -> {
      empresaDTO.getEmpresa().getTelefones().add(novoTelefone);
    });

    Empresa novaEmpresa = repositorioEmpresa.save(empresaDTO.getEmpresa());
    return novaEmpresa;
  }

  public Empresa encontrarEmpresa(String razaoSocial) {
    Optional<Empresa> empresa = repositorioEmpresa.findByRazaoSocial(razaoSocial);

    if (empresa.isEmpty()) {
      return null;
    }
    return empresa.get();
  }

  public Empresa criarMercadoria(CriarMercadoriaDTO mercadoriaDTO) {
    Empresa empresa = encontrarEmpresa(mercadoriaDTO.getRazaoSocial());

    if (empresa == null) {
      new Exception("Não foi possível encontrar empresa, tente novamente");
    }

    mercadoriaDTO.mercadoria.setCadastro(new Date());
    mercadoriaDTO.mercadoria.setFabricao(new Date(mercadoriaDTO.dataFabricacaoEmTexto));
    mercadoriaDTO.mercadoria.setValidade(new Date(mercadoriaDTO.dataValidadeEmTexto));

    empresa.getMercadorias().add(mercadoriaDTO.mercadoria);

    return repositorioEmpresa.save(empresa);
  }

  public Empresa criarServico(Servico servico, String razaoSocial) {
    Empresa empresa = encontrarEmpresa(razaoSocial);

    if (empresa == null) {
      new Exception("Não foi possível encontrar empresa, tente novamente");
    }

    empresa.getServicos().add(servico);

    return repositorioEmpresa.save(empresa);
  }
}
