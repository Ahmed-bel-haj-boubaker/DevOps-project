package tn.esprit.rh.achat.controllers;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.rh.achat.entities.CategorieProduit;
import tn.esprit.rh.achat.services.ICategorieProduitService;

import java.util.List;
import java.util.Optional;

@RestController
@Api(tags = "Gestion des categories Produit")
@RequestMapping("/categorieProduit")
public class CategorieProduitController {

	@Autowired
	ICategorieProduitService categorieProduitService;

	@GetMapping("/retrieve-all-categorieProduit")
	@ResponseBody
	public List<CategorieProduit> getCategorieProduit() {
		List<CategorieProduit> list = categorieProduitService.retrieveAllCategorieProduits();
		return list;
	}

	// http://localhost:8089/SpringMVC/categorieProduit/retrieve-categorieProduit/8
	@GetMapping("/retrieve-categorieProduit/{categorieProduit-id}")
	@ResponseBody
	public Optional<CategorieProduit> retrieveCategorieProduit(@PathVariable("categorieProduit-id") Long categorieProduitId) {
		return Optional.ofNullable(categorieProduitService.retrieveCategorieProduit(categorieProduitId).get());
	}

	// http://localhost:8089/SpringMVC/categorieProduit/add-categorieProduit
	@PostMapping("/add-categorieProduit")
	@ResponseBody
	public CategorieProduit addCategorieProduit(@RequestBody CategorieProduit cp) {
		CategorieProduit categorieProduit = categorieProduitService.addCategorieProduit(cp);
		return categorieProduit;
	}

	// http://localhost:8089/SpringMVC/categorieProduit/remove-categorieProduit/{categorieProduit-id}
	@DeleteMapping("/remove-categorieProduit/{categorieProduit-id}")
	@ResponseBody
	public void removeCategorieProduit(@PathVariable("categorieProduit-id") Long categorieProduitId) {
		categorieProduitService.deleteCategorieProduit(categorieProduitId);
	}

	// http://localhost:8089/SpringMVC/categorieProduit/modify-categorieProduit
	@PutMapping("/modify-categorieProduit")
	@ResponseBody
	public CategorieProduit modifyCategorieProduit(@RequestBody CategorieProduit categorieProduit) {
		return categorieProduitService.updateCategorieProduit(categorieProduit);
	}

	
}
