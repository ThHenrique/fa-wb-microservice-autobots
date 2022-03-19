package com.autobots.automanager.modelos.telefone;

import java.util.List;

import com.autobots.automanager.entidades.Cliente;
import com.autobots.automanager.entidades.Telefone;

import org.springframework.stereotype.Component;

@Component
public class TelefoneSelecionador {
  public Telefone selecionar(Cliente cliente, long telefoneId) {
    Telefone selecionado = null;
    List<Telefone> listaTelefone = cliente.getTelefones();

    for (Telefone telefone : listaTelefone) {
      if (telefone.getId() == telefoneId) {
        selecionado = telefone;
      }
    }
    return selecionado;
  }
}
