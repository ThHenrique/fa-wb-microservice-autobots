package com.autobots.automanager.controles.dtos.Dashboard;

import java.util.Set;

import com.autobots.automanager.entidades.Empresa;
import com.autobots.automanager.entidades.Mercadoria;
import com.autobots.automanager.entidades.Servico;

import lombok.Data;

@Data
public class EmpresaMercadoria_Servico {
  private String nomeFantasia;
  private String razaoSocial;
  private Set<Mercadoria> mercadorias;
  private Set<Servico> servicos;

  public EmpresaMercadoria_Servico(Empresa empresa) {
    this.nomeFantasia = empresa.getNomeFantasia();
    this.razaoSocial = empresa.getRazaoSocial();
    this.mercadorias = empresa.getMercadorias();
    this.servicos = empresa.getServicos();
  }
}
