package com.autobots.automanager.servico;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.entidades.Documento;
import com.autobots.automanager.entidades.Empresa;
import com.autobots.automanager.entidades.Usuario;
import com.autobots.automanager.entidades.Veiculo;
import com.autobots.automanager.repositorios.EmpresaRepositorio;

@Service
public class ClienteServico {

  @Autowired
  EmpresaRepositorio repositorioEmpresa;

  @Autowired
  EmpresaServico servicoEmpresa;

  public Usuario encontrarCliente(Set<Usuario> usuarios, String cpf, String razaoSocial) {
    Usuario cliente = null;
    for (Usuario usuario : usuarios) {
      for (Documento documento : usuario.getDocumentos()) {
        if (documento.getNumero().equals(cpf)) {
          cliente = usuario;
        }
      }
    }
    return cliente;
  }

  public void cadastrarVeiculo(List<Veiculo> veiculos, String cpfCliente, String razaoSocial) {
    Empresa empresa = servicoEmpresa.encontrarEmpresa(razaoSocial);

    if (empresa == null) {
      new Exception("Não foi possível encontrar empresa, tente novamente");
    }

    Usuario cliente = encontrarCliente(empresa.getUsuarios(), cpfCliente, razaoSocial);

    veiculos.forEach(veiculo -> {
      veiculo.setProprietario(cliente);
      cliente.getVeiculos().add(veiculo);
    });

    repositorioEmpresa.save(empresa);
  }
}
