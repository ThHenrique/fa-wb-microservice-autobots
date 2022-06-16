package com.autobots.automanager.servico;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.controles.dtos.CriarFornecedorDTO;
import com.autobots.automanager.controles.dtos.CriarUsuarioDTO;
import com.autobots.automanager.entidades.Empresa;
import com.autobots.automanager.enumeracoes.PerfilUsuario;
import com.autobots.automanager.repositorios.EmpresaRepositorio;

@Service
public class CriarUsuarioServico {

  @Autowired
  private EmpresaRepositorio repositorioEmpresa;

  @Autowired
  private EmpresaServico servicoEmpresa;

  public CriarUsuarioDTO criarUsuario(CriarUsuarioDTO usuarioDTO, PerfilUsuario perfilUsuario) {
    usuarioDTO.getUsuario().getPerfis().add(perfilUsuario);
    usuarioDTO.getUsuario().getEmails().add(usuarioDTO.getEmail());

    usuarioDTO.getUsuario().setEndereco(usuarioDTO.getEndereco());

    usuarioDTO.getTelefones().forEach(novoTelefone -> {
      usuarioDTO.getUsuario().getTelefones().add(novoTelefone);
    });

    usuarioDTO.getDocumentos().forEach(novoDocumento -> {
      Date dataEmissao = new Date();
      novoDocumento.setDataEmissao(dataEmissao);
      usuarioDTO.getUsuario().getDocumentos().add(novoDocumento);
    });

    usuarioDTO.getCredencial().setCriacao(new Date());
    usuarioDTO.getCredencial().setUltimoAcesso(new Date());
    usuarioDTO.getCredencial().setInativo(false);

    usuarioDTO.getUsuario().getCredenciais().add(usuarioDTO.getCredencial());

    return usuarioDTO;
  }

  public Empresa criarFuncionario(CriarUsuarioDTO funcionarioDTO) {
    Empresa empresa = encontrarEmpresa(funcionarioDTO.getRazaoSocial());

    CriarUsuarioDTO novoUsuario = criarUsuario(funcionarioDTO, PerfilUsuario.FUNCIONARIO);

    empresa.getUsuarios().add(novoUsuario.getUsuario());

    return repositorioEmpresa.save(empresa);
  }

  public Empresa criarFornecedor(CriarFornecedorDTO fornecedorDTO) {
    Empresa empresa = encontrarEmpresa(fornecedorDTO.getRazaoSocial());

    CriarFornecedorDTO novoUsuario = (CriarFornecedorDTO) criarUsuario(fornecedorDTO, PerfilUsuario.FORNECEDOR);

    novoUsuario.getMercadorias().forEach(novaMercadoria -> {
      novaMercadoria.setCadastro(new Date());
      novaMercadoria.setFabricao(new Date());
      novaMercadoria.setValidade(new Date());

      novoUsuario.getUsuario().getMercadorias().add(novaMercadoria);
    });

    empresa.getUsuarios().add(novoUsuario.getUsuario());

    return repositorioEmpresa.save(empresa);
  }

  public Empresa criarCliente(CriarUsuarioDTO clienteDTO) {
    Empresa empresa = encontrarEmpresa(clienteDTO.getRazaoSocial());

    CriarUsuarioDTO novoUsuario = criarUsuario(clienteDTO, PerfilUsuario.CLIENTE);

    empresa.getUsuarios().add(novoUsuario.getUsuario());

    return repositorioEmpresa.save(empresa);
  }

  public Empresa encontrarEmpresa(String razaoSocial) {
    Empresa empresa = servicoEmpresa.encontrarEmpresa(razaoSocial);

    if (empresa == null) {
      new Exception("Não foi possível encontrar empresa, tente novamente");
    }

    return empresa;
  }

}
