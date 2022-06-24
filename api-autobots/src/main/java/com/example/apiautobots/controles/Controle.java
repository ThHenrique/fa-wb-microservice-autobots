package com.example.apiautobots.controles;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.apiautobots.modelos.Usuario;

@RestController
@RequestMapping("/dashboard")
public class Controle {

  @GetMapping("/todos-usuarios")
  public ResponseEntity<?> obterUsuarios() {
    List<Usuario> usuarios = new ArrayList<>();

    ResponseEntity<? extends List> resposta = new RestTemplate()
        .getForEntity("http://localhost:8080/dashboard/usuarios", usuarios.getClass());
    usuarios = resposta.getBody();

    return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.FOUND);
  }
}
