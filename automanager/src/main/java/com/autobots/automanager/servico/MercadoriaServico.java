package com.autobots.automanager.servico;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.controles.dtos.CriarMercadoriaDTO;
import com.autobots.automanager.entidades.Empresa;
import com.autobots.automanager.entidades.Mercadoria;
import com.autobots.automanager.repositorios.EmpresaRepositorio;
import com.autobots.automanager.repositorios.MercadoriaRepositorio;

@Service
public class MercadoriaServico {

  @Autowired
  EmpresaRepositorio repositorioEmpresa;

  @Autowired
  MercadoriaRepositorio repositorioMercadoria;

  @Autowired
  EmpresaServico servicoEmpresa;

  public Empresa criarMercadoria(CriarMercadoriaDTO mercadoriaDTO) {
    Empresa empresa = servicoEmpresa.encontrarEmpresa(mercadoriaDTO.getRazaoSocial());

    if (empresa == null) {
      new Exception("Não foi possível encontrar empresa, tente novamente");
    }

    mercadoriaDTO.mercadoria.setCadastro(new Date());
    mercadoriaDTO.mercadoria.setFabricao(new Date(mercadoriaDTO.dataFabricacaoEmTexto));
    mercadoriaDTO.mercadoria.setValidade(new Date(mercadoriaDTO.dataValidadeEmTexto));

    empresa.getMercadorias().add(mercadoriaDTO.mercadoria);

    return repositorioEmpresa.save(empresa);
  }

  public Mercadoria encontrarMercadoria(Long id) {
    Optional<Mercadoria> mercadoria = repositorioMercadoria.findById(id);

    if (mercadoria.isEmpty()) {
      return null;
    }
    return mercadoria.get();
  }
}
