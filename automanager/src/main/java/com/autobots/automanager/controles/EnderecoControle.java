package com.autobots.automanager.controles;

import java.util.List;
import java.util.Optional;

import com.autobots.automanager.entidades.Endereco;
import com.autobots.automanager.modelos.endereco.AdicionadorLinkEndereco;
import com.autobots.automanager.modelos.endereco.EnderecoSelecionador;
import com.autobots.automanager.repositorios.EnderecoRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class EnderecoControle {
  @Autowired // não precisa colocar = new Nome da classe
  private EnderecoRepositorio repositorio;
  @Autowired
  private AdicionadorLinkEndereco adicionadorLink;
  @Autowired
  private EnderecoSelecionador selecionador;

  @GetMapping("/enderecos")
  public ResponseEntity<List<Endereco>> obterEnderecos() {
    HttpStatus status = HttpStatus.NOT_FOUND;
    List<Endereco> enderecos = repositorio.findAll();

    if (enderecos == null) {
      return new ResponseEntity<List<Endereco>>(status);
    } else {
      adicionadorLink.adicionarLink(enderecos);
      status = HttpStatus.FOUND;
      return new ResponseEntity<List<Endereco>>(enderecos, status);
    }

  }

  @GetMapping("/endereco/{id}")
  public ResponseEntity<Endereco> obterEndereco(@PathVariable long id) {
    HttpStatus status = HttpStatus.NOT_FOUND;
    List<Endereco> enderecos = repositorio.findAll();
    Endereco endereco = selecionador.selecionar(enderecos, id);

    if (endereco == null) {
      return new ResponseEntity<Endereco>(status);
    } else {
      adicionadorLink.adicionarLink(endereco);
      status = HttpStatus.FOUND;
      return new ResponseEntity<Endereco>(endereco, status);
    }

  }
}
