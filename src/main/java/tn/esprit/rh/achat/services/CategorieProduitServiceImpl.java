package tn.esprit.rh.achat.services;

import org.springframework.stereotype.Service;
import tn.esprit.rh.achat.entities.CategorieProduit;
import tn.esprit.rh.achat.repositories.CategorieProduitRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategorieProduitServiceImpl implements ICategorieProduitService {

	CategorieProduitRepository categorieProduitRepository;
	@Override
	public List<CategorieProduit> retrieveAllCategorieProduits() {
		
		return categorieProduitRepository.findAll();
	}

	@Override
	public CategorieProduit addCategorieProduit(CategorieProduit cp) {
		return categorieProduitRepository.save(cp);
	}

	@Override
	public void deleteCategorieProduit(Long id) {
		categorieProduitRepository.deleteById(id);
		
	}

	@Override
	public CategorieProduit updateCategorieProduit(CategorieProduit cp) {
		categorieProduitRepository.save(cp);
		return cp;
	}

	@Override
	public Optional<CategorieProduit> retrieveCategorieProduit(Long id) {
		return categorieProduitRepository.findById(id);
	}

}
