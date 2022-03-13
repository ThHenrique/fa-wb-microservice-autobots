package com.autobots.automanager.modelos.endereco;

import java.util.List;

import com.autobots.automanager.entidades.Endereco;

import org.springframework.stereotype.Component;

@Component
public class EnderecoSelecionador {
  public Endereco selecionar(List<Endereco> enderecos, long id) {
    Endereco selecionado = null;
    for (Endereco endereco : enderecos) {
      if (endereco.getId() == id) {
        selecionado = endereco;
      }
    }
    return selecionado;
  }
}
