package com.autobots.automanager.controles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autobots.automanager.entidades.Cliente;
import com.autobots.automanager.entidades.Empresa;
import com.autobots.automanager.entidades.Usuario;
import com.autobots.automanager.entidades.Veiculo;
import com.autobots.automanager.modelos.cliente.AdicionadorLinkCliente;
import com.autobots.automanager.modelos.cliente.ClienteAtualizador;
import com.autobots.automanager.modelos.cliente.ClienteSelecionador;
import com.autobots.automanager.repositorios.ClienteRepositorio;
import com.autobots.automanager.servico.UsuarioServico;
import com.autobots.automanager.servico.EmpresaServico;

@RestController
@RequestMapping("/cliente")
public class ClienteControle {
	@Autowired
	private ClienteRepositorio repositorio;
	@Autowired
	private ClienteSelecionador selecionador;
	@Autowired
	private AdicionadorLinkCliente adicionadorLink;

	@Autowired
	private EmpresaServico servicoEmpresa;

	@Autowired
	private UsuarioServico servicoUsuario;

	@GetMapping("/{id}")
	public ResponseEntity<Usuario> obterCliente(@PathVariable long id) {
		try {
			Usuario usuario = servicoUsuario.encontrarUsuario(id);
			// adicionadorLink.adicionarLink(cliente);

			return new ResponseEntity<Usuario>(usuario, HttpStatus.FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// @PutMapping("/atualizar")
	// public ResponseEntity<?> atualizarCliente(@RequestBody Cliente atualizacao) {
	// HttpStatus status = HttpStatus.CONFLICT;
	// Cliente cliente = repositorio.getById(atualizacao.getId());
	// if (cliente != null) {
	// ClienteAtualizador atualizador = new ClienteAtualizador();
	// atualizador.atualizar(cliente, atualizacao);
	// repositorio.save(cliente);
	// status = HttpStatus.OK;
	// } else {
	// status = HttpStatus.BAD_REQUEST;
	// }
	// return new ResponseEntity<>(status);
	// }

	@PostMapping("/veiculo/cadastrar/{idUsuario}")
	public ResponseEntity<?> criarVeiculo(
			@RequestBody List<Veiculo> veiculos,
			@PathVariable Long idUsuario) {

		try {
			servicoUsuario.cadastrarVeiculo(veiculos, idUsuario);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getCause(), HttpStatus.BAD_REQUEST);
		}

	}

	// @PostMapping("/veiculo/listar/{idUsuario}")
	// public ResponseEntity<?> encontrarVeiculo(@PathVariable Long idUsuario) {

	// HttpStatus status = HttpStatus.CONFLICT;

	// try {
	// servicoUsuario.cadastrarVeiculo(veiculos, idUsuario);
	// status = HttpStatus.OK;
	// } catch (Exception e) {
	// status = HttpStatus.BAD_REQUEST;
	// }

	// return new ResponseEntity<>(status);
	// }

	// @DeleteMapping("/excluir/{id}")
	// public ResponseEntity<?> excluirCliente(@PathVariable long id) {
	// HttpStatus status = HttpStatus.BAD_REQUEST;
	// Cliente cliente = repositorio.getById(id);
	// if (cliente != null) {
	// repositorio.delete(cliente);
	// status = HttpStatus.OK;
	// }
	// return new ResponseEntity<>(status);
	// }
}
