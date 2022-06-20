package com.autobots.automanager.controles;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autobots.automanager.entidades.Empresa;
import com.autobots.automanager.entidades.Servico;
import com.autobots.automanager.servico.EmpresaServico;
import com.autobots.automanager.servico.ServicoEmpServico;

@RestController
@RequestMapping("/servico")
public class ServicoControle {

  @Autowired
  public ServicoEmpServico servicoEmpServico;

  @Autowired
  public EmpresaServico servicoEmpresa;

  @PostMapping("/criar/{razaoSocial}")
  public ResponseEntity<?> cadastroServico(@RequestBody Servico servico, @PathVariable String razaoSocial) {
    try {
      servicoEmpServico.criarServico(servico, razaoSocial);

      return new ResponseEntity<>(HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

  }

  @GetMapping("/{id}")
  public ResponseEntity<Servico> obterServico(@PathVariable Long id) {
    Servico servico = servicoEmpServico.encontrarServico(id);

    if (servico == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<Servico>(servico, HttpStatus.FOUND);
  }

  @GetMapping("/empresa/{idEmpresa}")
  public ResponseEntity<Set<Servico>> obterServicos(@PathVariable Long idEmpresa) {
    Empresa empresa = servicoEmpresa.encontrarEmpresa(idEmpresa);

    if (empresa == null || empresa.getServicos().isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<Set<Servico>>(empresa.getServicos(), HttpStatus.FOUND);
  }

  @DeleteMapping("/excluir/{razaoSocial}/{idServico}")
  public ResponseEntity<Servico> excluirServicoEmpresa(
      @PathVariable String razaoSocial,
      @PathVariable Long idServico) {

    try {
      servicoEmpServico.excluirServico(razaoSocial, idServico);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

  }
}
