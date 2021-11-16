package br.com.springBootWeb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import br.com.springBootWeb.beans.Usuario;
import br.com.springBootWeb.dao.UsuarioDAO;

@Controller
public class UsuarioController {

	@Autowired
	private UsuarioDAO dao;

	@GetMapping("/index")
	public String index() {
		return "login";
	}
	

	@GetMapping("/teste")
	public String test() {
		return "<h2> asdasdasdas </h2>";
	}


	@PostMapping("/login")
	public ResponseEntity<Usuario> getAllByEmailAndSenha(@RequestBody Usuario usuario) {
		System.out.println(usuario);
		System.out.println("Email: " + usuario.getEmail());
		System.out.println("Senha: " + usuario.getSenha());
		Usuario resposta = dao.findByEmailAndSenha(usuario.getEmail(), usuario.getSenha());

//		if(resposta == null) { return ResponseEntity.status(404).build();}
		System.out.println(resposta);
		return ResponseEntity.ok(resposta);
	}

	@GetMapping("/usuarios")
	public ResponseEntity<List<Usuario>> getAll() {
		List<Usuario> list = (List<Usuario>) dao.findAll();

		if (list.size() == 0) {
			return ResponseEntity.status(404).build();
		}

		return ResponseEntity.ok(list);

	}

	@GetMapping("/usuarios/{userId}")
	public ResponseEntity<Usuario> getAllById(@PathVariable("userId") int id) {
		Usuario listId = dao.findById(id).orElse(null);

		if (listId == null) {
			return ResponseEntity.status(404).build();
		}

		return ResponseEntity.ok(listId);
	}

	@PostMapping("/usuarios/add/")
	public ResponseEntity<Usuario> getSave(@RequestBody Usuario usuario) {
		try {
			dao.save(usuario);
			return ResponseEntity.ok(usuario);

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(404).build();
		}
	}

	@PostMapping("/loginPostman")
	public ResponseEntity<Usuario> getAllByEmailAndSenha1(@RequestBody Usuario usuario) {
		Usuario resposta = dao.findByEmailAndSenha(usuario.getEmail(), usuario.getSenha());

		if (resposta == null) {
			return ResponseEntity.status(404).build();
		}
		System.out.println(resposta);
		return ResponseEntity.ok(resposta);
	}

}
