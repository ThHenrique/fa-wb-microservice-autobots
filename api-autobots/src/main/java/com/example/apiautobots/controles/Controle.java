package com.example.apiautobots.controles;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class Controle {

  @GetMapping("/")
  public ResponseEntity<String> obterUsuarios() {
    return new ResponseEntity<String>("API - Online", HttpStatus.OK);
  }
}
