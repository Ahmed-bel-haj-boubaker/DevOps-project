package tn.esprit.rh.achat.services;

import tn.esprit.rh.achat.entities.CategorieProduit;

import java.util.List;
import java.util.Optional;


public interface ICategorieProduitService {

	List<CategorieProduit> retrieveAllCategorieProduits();

	CategorieProduit addCategorieProduit(CategorieProduit cp);

	void deleteCategorieProduit(Long id);

	CategorieProduit updateCategorieProduit(CategorieProduit cp);

	Optional<CategorieProduit> retrieveCategorieProduit(Long id);

}
