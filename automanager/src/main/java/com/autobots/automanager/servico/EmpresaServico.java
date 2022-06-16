package com.autobots.automanager.servico;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.controles.dtos.CriarEmpresaDTO;
import com.autobots.automanager.controles.dtos.CriarFornecedorDTO;
import com.autobots.automanager.controles.dtos.CriarUsuarioDTO;
import com.autobots.automanager.controles.dtos.CriarMercadoriaDTO;
import com.autobots.automanager.entidades.Empresa;
import com.autobots.automanager.entidades.Servico;
import com.autobots.automanager.enumeracoes.PerfilUsuario;
import com.autobots.automanager.repositorios.EmpresaRepositorio;

@Service
public class EmpresaServico {

  @Autowired
  private EmpresaRepositorio repositorioEmpresa;

  public Empresa criarEmpresa(CriarEmpresaDTO empresaDTO) {
    empresaDTO.getEmpresa().setCadastro(new Date());
    empresaDTO.getEmpresa().setEndereco(empresaDTO.getEndereco());

    empresaDTO.getTelefones().forEach(novoTelefone -> {
      empresaDTO.getEmpresa().getTelefones().add(novoTelefone);
    });

    Empresa novaEmpresa = repositorioEmpresa.save(empresaDTO.getEmpresa());
    return novaEmpresa;
  }

  public Empresa encontrarEmpresa(String razaoSocial) {
    Optional<Empresa> empresa = repositorioEmpresa.findByRazaoSocial(razaoSocial);

    if (empresa.isEmpty()) {
      return null;
    }
    return empresa.get();
  }

  public Empresa criarMercadoria(CriarMercadoriaDTO mercadoriaDTO) {
    Empresa empresa = encontrarEmpresa(mercadoriaDTO.getRazaoSocial());

    if (empresa == null) {
      new Exception("Não foi possível encontrar empresa, tente novamente");
    }

    mercadoriaDTO.mercadoria.setCadastro(new Date());
    mercadoriaDTO.mercadoria.setFabricao(new Date(mercadoriaDTO.dataFabricacaoEmTexto));
    mercadoriaDTO.mercadoria.setValidade(new Date(mercadoriaDTO.dataValidadeEmTexto));

    empresa.getMercadorias().add(mercadoriaDTO.mercadoria);

    return repositorioEmpresa.save(empresa);
  }

  public Empresa criarServico(Servico servico, String razaoSocial) {
    Empresa empresa = encontrarEmpresa(razaoSocial);

    if (empresa == null) {
      new Exception("Não foi possível encontrar empresa, tente novamente");
    }

    empresa.getServicos().add(servico);

    return repositorioEmpresa.save(empresa);
  }

  public Empresa criarFuncionario(CriarUsuarioDTO funcionarioDTO) {
    Empresa empresa = encontrarEmpresa(funcionarioDTO.getRazaoSocial());

    if (empresa == null) {
      new Exception("Não foi possível encontrar empresa, tente novamente");
    }

    funcionarioDTO.getUsuario().getPerfis().add(PerfilUsuario.FUNCIONARIO);
    funcionarioDTO.getUsuario().getEmails().add(funcionarioDTO.getEmail());

    funcionarioDTO.getUsuario().setEndereco(funcionarioDTO.getEndereco());

    funcionarioDTO.getTelefones().forEach(novoTelefone -> {
      funcionarioDTO.getUsuario().getTelefones().add(novoTelefone);
    });

    funcionarioDTO.getDocumentos().forEach(novoDocumento -> {
      Date dataEmissao = new Date();
      novoDocumento.setDataEmissao(dataEmissao);
      funcionarioDTO.getUsuario().getDocumentos().add(novoDocumento);
    });

    funcionarioDTO.getCredencial().setCriacao(new Date());
    funcionarioDTO.getCredencial().setUltimoAcesso(new Date());
    funcionarioDTO.getCredencial().setInativo(false);

    funcionarioDTO.getUsuario().getCredenciais().add(funcionarioDTO.getCredencial());

    empresa.getUsuarios().add(funcionarioDTO.getUsuario());

    return repositorioEmpresa.save(empresa);
  }

  public Empresa criarFornecedor(CriarFornecedorDTO fornecedorDTO) {
    Empresa empresa = encontrarEmpresa(fornecedorDTO.getRazaoSocial());

    if (empresa == null) {
      new Exception("Não foi possível encontrar empresa, tente novamente");
    }

    fornecedorDTO.getUsuario().getPerfis().add(PerfilUsuario.FORNECEDOR);
    fornecedorDTO.getUsuario().getEmails().add(fornecedorDTO.getEmail());

    fornecedorDTO.getUsuario().setEndereco(fornecedorDTO.getEndereco());

    fornecedorDTO.getTelefones().forEach(novoTelefone -> {
      fornecedorDTO.getUsuario().getTelefones().add(novoTelefone);
    });

    fornecedorDTO.getDocumentos().forEach(novoDocumento -> {
      Date dataEmissao = new Date();
      novoDocumento.setDataEmissao(dataEmissao);
      fornecedorDTO.getUsuario().getDocumentos().add(novoDocumento);
    });

    fornecedorDTO.getMercadorias().forEach(novaMercadoria -> {
      novaMercadoria.setCadastro(new Date());
      novaMercadoria.setFabricao(new Date());
      novaMercadoria.setValidade(new Date());

      fornecedorDTO.getUsuario().getMercadorias().add(novaMercadoria);
    });

    fornecedorDTO.getCredencial().setCriacao(new Date());
    fornecedorDTO.getCredencial().setUltimoAcesso(new Date());
    fornecedorDTO.getCredencial().setInativo(false);

    fornecedorDTO.getUsuario().getCredenciais().add(fornecedorDTO.getCredencial());

    empresa.getUsuarios().add(fornecedorDTO.getUsuario());

    return repositorioEmpresa.save(empresa);
  }

}
