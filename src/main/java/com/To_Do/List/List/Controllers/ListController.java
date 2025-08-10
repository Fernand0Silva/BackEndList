package com.To_Do.List.List.Controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.To_Do.List.List.Models.ListModel;
import com.To_Do.List.List.Repositories.IList;

@RequestMapping("/list")
@RestController
public class ListController {
	
	@Autowired
	
	private IList repository;
	
	@GetMapping("/teste")
	public String teste() {
		return "servidor rodando!!!";
	}
	@GetMapping()
	public List<ListModel> lista(){
		return(List<ListModel>)repository.findAll();
	}
	@PostMapping()
	public ListModel inserir(@RequestBody ListModel dao) {
		ListModel novaTarefa = repository.save(dao);
		 return novaTarefa;
	}
	@PutMapping()
	public ResponseEntity<?> atualizar(@RequestBody ListModel dao) {
	    if (dao.getId() == null) {
	        return ResponseEntity.badRequest().body(Map.of("error", "ID da tarefa é obrigatório para atualização."));
	    }

	    if (!repository.existsById(dao.getId())) {
	        return ResponseEntity.status(404).body(Map.of("error", "Tarefa não encontrada."));
	    }

	    ListModel novaTarefa = repository.save(dao);
	    return ResponseEntity.ok(novaTarefa);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, String>> deleta(@PathVariable Integer id) {
		Optional<ListModel>dao= repository.findById(id);
		if(dao.isPresent()) {
			repository.deleteById(id);
			return ResponseEntity.ok().body(Map.of("message", "Tarefa com ID " + id + " deletada com sucesso!"));
		}else {
	        return ResponseEntity.status(404).body(Map.of("error", "Tarefa com ID " + id + " não encontrada"));
		}
		
	}

}
