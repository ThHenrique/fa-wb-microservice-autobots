package com.autobots.automanager.controles;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autobots.automanager.controles.dtos.Dashboard.EmpresaMercadoria_Servico;
import com.autobots.automanager.controles.dtos.Dashboard.EmpresaUsuariosDTO;
import com.autobots.automanager.controles.dtos.Dashboard.EmpresaVeiculosDTO;
import com.autobots.automanager.controles.dtos.Dashboard.EmpresaVendasDTO;
import com.autobots.automanager.entidades.Empresa;
import com.autobots.automanager.entidades.Veiculo;
import com.autobots.automanager.entidades.Venda;
import com.autobots.automanager.repositorios.EmpresaRepositorio;

@RestController
@RequestMapping("/dashboard")
public class DashboardControle {

  @Autowired
  EmpresaRepositorio repositorioEmpresa;

  @GetMapping("/usuarios")
  public ResponseEntity<List<EmpresaUsuariosDTO>> obterUsuarios() {
    List<Empresa> empresas = repositorioEmpresa.findAll();

    if (empresas.isEmpty()) {
      ResponseEntity<List<EmpresaUsuariosDTO>> resposta = new ResponseEntity<>(HttpStatus.NOT_FOUND);
      return resposta;
    } else {
      List<EmpresaUsuariosDTO> empresaUsuarios = new ArrayList<>();

      for (Empresa empresa : empresas) {
        EmpresaUsuariosDTO empresaUsuarioDto = new EmpresaUsuariosDTO(empresa);
        empresaUsuarios.add(empresaUsuarioDto);
      }

      ResponseEntity<List<EmpresaUsuariosDTO>> resposta = new ResponseEntity<>(empresaUsuarios, HttpStatus.FOUND);
      return resposta;
    }
  }

  @GetMapping("/vendas")
  public ResponseEntity<List<EmpresaVendasDTO>> obterVendasPorEmpresa() {
    List<Empresa> listaEmpresas = repositorioEmpresa.findAll();
    if (listaEmpresas.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    List<EmpresaVendasDTO> vendasPorEmpresa = new ArrayList<>();
    for (Empresa empresa : listaEmpresas) {
      EmpresaVendasDTO empresaVendaDto = new EmpresaVendasDTO(empresa);
      vendasPorEmpresa.add(empresaVendaDto);
    }

    return new ResponseEntity<List<EmpresaVendasDTO>>(vendasPorEmpresa, HttpStatus.FOUND);
  }

  @GetMapping("/mercadorias-servicos")
  public ResponseEntity<List<EmpresaMercadoria_Servico>> obterMercadoriaServicoPorEmpresa() {
    List<Empresa> listaEmpresas = repositorioEmpresa.findAll();
    if (listaEmpresas.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    List<EmpresaMercadoria_Servico> mercadoriasServicosPorEmpresa = new ArrayList<>();
    for (Empresa empresa : listaEmpresas) {
      EmpresaMercadoria_Servico empresaMercadoriaServicosDto = new EmpresaMercadoria_Servico(empresa);
      mercadoriasServicosPorEmpresa.add(empresaMercadoriaServicosDto);
    }

    return new ResponseEntity<List<EmpresaMercadoria_Servico>>(mercadoriasServicosPorEmpresa, HttpStatus.FOUND);
  }

  @GetMapping("/veiculos")
  public ResponseEntity<List<EmpresaVeiculosDTO>> obterVeiculosAtendidoPorEmpresa() {
    List<Empresa> listaEmpresas = repositorioEmpresa.findAll();
    if (listaEmpresas.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    List<EmpresaVeiculosDTO> veiculosPorEmpresaDTOs = new ArrayList<>();
    for (Empresa empresa : listaEmpresas) {
      Set<Venda> vendas = empresa.getVendas();
      List<Veiculo> veiculos = new ArrayList<Veiculo>();
      for (Venda venda : vendas) {
        Veiculo veiculoDaVenda = venda.getVeiculo();
        veiculos.add(veiculoDaVenda);
      }
      EmpresaVeiculosDTO veiculoPorEmpresaDto = new EmpresaVeiculosDTO(empresa, veiculos);
      veiculosPorEmpresaDTOs.add(veiculoPorEmpresaDto);
    }

    return new ResponseEntity<List<EmpresaVeiculosDTO>>(veiculosPorEmpresaDTOs, HttpStatus.FOUND);
  }
}
