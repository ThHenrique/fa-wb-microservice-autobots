package com.autobots.automanager.controles.dtos.Dashboard;

import java.util.Set;

import com.autobots.automanager.entidades.Empresa;
import com.autobots.automanager.entidades.Usuario;

import lombok.Data;

@Data
public class EmpresaUsuariosDTO {
  private String razaoSocial;
  private Set<Usuario> usuarios;

  public EmpresaUsuariosDTO(Empresa empresa) {
    this.razaoSocial = empresa.getRazaoSocial();
    this.usuarios = empresa.getUsuarios();
  }
}
