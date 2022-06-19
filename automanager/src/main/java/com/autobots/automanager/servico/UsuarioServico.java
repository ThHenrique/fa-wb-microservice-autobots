package com.autobots.automanager.servico;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.entidades.Usuario;
import com.autobots.automanager.entidades.Veiculo;
import com.autobots.automanager.repositorios.EmpresaRepositorio;
import com.autobots.automanager.repositorios.UsuarioRepositorio;
import com.autobots.automanager.repositorios.VeiculoRepositorio;

@Service
public class UsuarioServico {

  @Autowired
  EmpresaRepositorio repositorioEmpresa;

  @Autowired
  UsuarioRepositorio repositorioUsuario;

  @Autowired
  VeiculoRepositorio repositorioVeiculo;

  public Usuario encontrarUsuario(Long id) {
    Optional<Usuario> usuario = repositorioUsuario.findById(id);

    if (usuario.isEmpty()) {
      return null;
    }

    return usuario.get();
  }

  public void cadastrarVeiculo(List<Veiculo> veiculos, Long idUsuario) {
    Usuario cliente = encontrarUsuario(idUsuario);

    veiculos.forEach(veiculo -> {
      veiculo.setProprietario(cliente);
      cliente.getVeiculos().add(veiculo);

      repositorioVeiculo.save(veiculo);
    });
  }

  public Veiculo encontrarVeiculo(Usuario usuario, String placa) {
    Set<Veiculo> veiculos = usuario.getVeiculos();

    Veiculo veiculoEncontrado = null;

    for (Veiculo veiculo : veiculos) {
      if (veiculo.getPlaca().equals(placa)) {
        veiculoEncontrado = veiculo;
      }
    }

    return veiculoEncontrado;
  }
}
