package com.autobots.automanager.controles.dtos;

import com.autobots.automanager.entidades.Mercadoria;

import lombok.Data;

@Data
public class CriarMercadoriaDTO {
  public String razaoSocial;
  public Mercadoria mercadoria;

  public String dataValidadeEmTexto;
  public String dataFabricacaoEmTexto;
}
