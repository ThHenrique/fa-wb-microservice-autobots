package com.example.apiautobots.controles;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.apiautobots.modelos.Mercadoria_Servico;
import com.example.apiautobots.modelos.Usuario;
import com.example.apiautobots.modelos.Veiculo;
import com.example.apiautobots.modelos.Venda;

@RestController
@RequestMapping("/dashboard")
public class DashboardControle {
  @GetMapping("/todos-usuarios")
  public ResponseEntity<?> obterUsuarios() {
    List<Usuario> usuarios = new ArrayList<>();

    ResponseEntity<? extends List> resposta = new RestTemplate()
        .getForEntity("http://localhost:8080/dashboard/usuarios", usuarios.getClass());
    usuarios = resposta.getBody();

    return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.FOUND);
  }

  @SuppressWarnings({ "rawtypes", "unchecked" })
  @GetMapping("/todas-vendas")
  public ResponseEntity<?> obterVendas() {
    List<Venda> vendas = new ArrayList<>();

    ResponseEntity<? extends List> resposta = new RestTemplate()
        .getForEntity("http://localhost:8080/dashboard/vendas", vendas.getClass());
    vendas = resposta.getBody();

    return new ResponseEntity<List<Venda>>(vendas, HttpStatus.FOUND);
  }

  @SuppressWarnings({ "rawtypes", "unchecked" })
  @GetMapping("/todos-veiculos")
  public ResponseEntity<?> obterVeiculos() {
    List<Veiculo> veiculos = new ArrayList<>();

    ResponseEntity<? extends List> resposta = new RestTemplate()
        .getForEntity("http://localhost:8080/dashboard/veiculos", veiculos.getClass());
    veiculos = resposta.getBody();

    return new ResponseEntity<List<Veiculo>>(veiculos, HttpStatus.FOUND);
  }

  @SuppressWarnings({ "rawtypes", "unchecked" })
  @GetMapping("/todas-mercadorias-servicos")
  public ResponseEntity<?> obterMercadoriaServico() {
    List<Mercadoria_Servico> mercadoriaServico = new ArrayList<>();

    ResponseEntity<? extends List> resposta = new RestTemplate()
        .getForEntity("http://localhost:8080/dashboard/mercadorias-servicos",
            mercadoriaServico.getClass());
    mercadoriaServico = resposta.getBody();

    return new ResponseEntity<List<Mercadoria_Servico>>(mercadoriaServico,
        HttpStatus.FOUND);
  }
}
