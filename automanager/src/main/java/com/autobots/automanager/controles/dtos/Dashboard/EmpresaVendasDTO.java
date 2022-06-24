package com.autobots.automanager.controles.dtos.Dashboard;

import java.util.Set;

import com.autobots.automanager.entidades.Empresa;
import com.autobots.automanager.entidades.Venda;

import lombok.Data;

@Data
public class EmpresaVendasDTO {
  private String nomeFantasia;
  private String razaoSocial;
  private Set<Venda> vendas;

  public EmpresaVendasDTO(Empresa empresa) {
    this.nomeFantasia = empresa.getNomeFantasia();
    this.razaoSocial = empresa.getRazaoSocial();
    this.vendas = empresa.getVendas();
  }
}
