package com.autobots.automanager.controles;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autobots.automanager.controles.dtos.Dashboard.EmpresaUsuariosDTO;
import com.autobots.automanager.entidades.Empresa;
import com.autobots.automanager.repositorios.EmpresaRepositorio;

@RestController
@RequestMapping("/dashboard")
public class DashboardControle {

  @Autowired
  EmpresaRepositorio repositorioEmpresa;

  @GetMapping("/usuarios")
  public ResponseEntity<List<EmpresaUsuariosDTO>> obterUsuarios() {
    List<Empresa> empresas = repositorioEmpresa.findAll();

    if (empresas.isEmpty()) {
      ResponseEntity<List<EmpresaUsuariosDTO>> resposta = new ResponseEntity<>(HttpStatus.NOT_FOUND);
      return resposta;
    } else {
      List<EmpresaUsuariosDTO> empresaUsuarios = new ArrayList<>();

      for (Empresa empresa : empresas) {
        EmpresaUsuariosDTO empresaUsuarioDto = new EmpresaUsuariosDTO(empresa);
        empresaUsuarios.add(empresaUsuarioDto);
      }

      ResponseEntity<List<EmpresaUsuariosDTO>> resposta = new ResponseEntity<>(empresaUsuarios, HttpStatus.FOUND);
      return resposta;
    }
  }
}
