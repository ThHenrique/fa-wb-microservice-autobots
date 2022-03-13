package com.autobots.automanager.controles;

import java.util.List;
import java.util.Optional;

import com.autobots.automanager.entidades.Cliente;
import com.autobots.automanager.entidades.Endereco;
import com.autobots.automanager.modelos.cliente.ClienteSelecionador;
import com.autobots.automanager.modelos.endereco.AdicionadorLinkEndereco;
import com.autobots.automanager.modelos.endereco.EnderecoSelecionador;
import com.autobots.automanager.repositorios.ClienteRepositorio;
import com.autobots.automanager.repositorios.EnderecoRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/endereco")
public class EnderecoControle {
  @Autowired // não precisa colocar = new Nome da classe
  private EnderecoRepositorio repositorio;
  @Autowired
  private AdicionadorLinkEndereco adicionadorLink;
  @Autowired
  private EnderecoSelecionador selecionador;

  @Autowired
  private ClienteRepositorio repositorioCliente;
  @Autowired
  private ClienteSelecionador selecionadorCliente;

  @GetMapping("/")
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

  @GetMapping("/{id}")
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

  @PutMapping("/cadastro")
  public void cadastrarCliente(@RequestBody Cliente cliente) {
    List<Cliente> clientes = repositorioCliente.findAll();
    Cliente selecionado = selecionadorCliente.selecionar(clientes, cliente.getId());
    selecionado.setEndereco(cliente.getEndereco());
    repositorioCliente.save(selecionado);
  }

  @DeleteMapping("/excluir/{id}")
  public void deletarEndereco(@PathVariable long id) {
    List<Cliente> clientes = repositorioCliente.findAll();
    Cliente selecionado = selecionadorCliente.selecionar(clientes, id);
    selecionado.setEndereco(null);
    repositorioCliente.save(selecionado);
  }
}
