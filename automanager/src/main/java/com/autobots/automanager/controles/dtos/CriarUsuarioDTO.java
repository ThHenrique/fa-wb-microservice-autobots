package com.autobots.automanager.controles.dtos;

import java.util.List;

import com.autobots.automanager.entidades.CredencialUsuarioSenha;
import com.autobots.automanager.entidades.Documento;
import com.autobots.automanager.entidades.Email;
import com.autobots.automanager.entidades.Endereco;
import com.autobots.automanager.entidades.Telefone;
import com.autobots.automanager.entidades.Usuario;

import lombok.Data;

@Data
public class CriarUsuarioDTO {
  private String razaoSocial;
  private Usuario usuario;
  private Endereco endereco;
  private List<Telefone> telefones;
  private Email email;
  private List<Documento> documentos;
  private CredencialUsuarioSenha credencial;
}
