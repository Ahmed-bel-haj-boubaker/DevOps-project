package tn.esprit.rh.achat.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.rh.achat.entities.*;
import tn.esprit.rh.achat.repositories.*;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
@Transactional
public class FactureServiceImpl implements IFactureService {

	FactureRepository factureRepository;

	OperateurRepository operateurRepository;

	DetailFactureRepository detailFactureRepository;

	FournisseurRepository fournisseurRepository;

	ProduitRepository produitRepository;

    ReglementServiceImpl reglementService;
	
	@Override
	public List<Facture> retrieveAllFactures() {
		List<Facture> factures =  factureRepository.findAll();
		for (Facture facture : factures) {
			log.info(" facture : " + facture);
		}
		return factures;
	}

	
	public Facture addFacture(Facture f) {
		return factureRepository.save(f);
	}

	/*
	 * calculer les montants remise et le montant total d'un détail facture
	 * ainsi que les montants d'une facture
	 */
	private Facture addDetailsFacture(Facture f, Set<DetailFacture> detailsFacture) {
		float montantFacture = 0;
		float montantRemise = 0;
		for (DetailFacture detail : detailsFacture) {
			//Récuperer le produit 
			Produit produit = produitRepository.findById(detail.getProduit().getIdProduit()).get();
			//Calculer le montant total pour chaque détail Facture
			float prixTotalDetail = detail.getQteCommandee() * produit.getPrix();
			//Calculer le montant remise pour chaque détail Facture
			float montantRemiseDetail = (prixTotalDetail * detail.getPourcentageRemise()) / 100;
			float prixTotalDetailRemise = prixTotalDetail - montantRemiseDetail;
			detail.setMontantRemise(montantRemiseDetail);
			detail.setPrixTotalDetail(prixTotalDetailRemise);
			//Calculer le montant total pour la facture
			montantFacture = montantFacture + prixTotalDetailRemise;
			//Calculer le montant remise pour la facture
			montantRemise = montantRemise + montantRemiseDetail;
			detailFactureRepository.save(detail);
		}
		f.setMontantFacture(montantFacture);
		f.setMontantRemise(montantRemise);
		return f;
	}

	@Override
	public void cancelFacture(Long factureId) {

		Facture facture = factureRepository.findById(factureId).orElse(new Facture());
		facture.setArchivee(true);
		factureRepository.save(facture);
		//Méthode 02 (Avec JPQL)
		factureRepository.updateFacture(factureId);
	}

	@Override
	public Facture retrieveFacture(Long factureId) {

		Facture facture = factureRepository.findById(factureId).orElse(null);
		log.info("facture :" + facture);
		return facture;
	}

	@Override
	public List<Facture> getFacturesByFournisseur(Long idFournisseur) {
		Fournisseur fournisseur = fournisseurRepository.findById(idFournisseur).orElse(null);
		return (List<Facture>) fournisseur.getFactures();
	}

	@Override
	public void assignOperateurToFacture(Long idOperateur, Long idFacture) {
		Facture facture = factureRepository.findById(idFacture).orElse(null);
		Operateur operateur = operateurRepository.findById(idOperateur).orElse(null);
		operateur.getFactures().add(facture);
		operateurRepository.save(operateur);
	}

	@Override
	public float pourcentageRecouvrement(Date startDate, Date endDate) {
		float totalFacturesEntreDeuxDates = factureRepository.getTotalFacturesEntreDeuxDates(startDate,endDate);
		float totalRecouvrementEntreDeuxDates =reglementService.getChiffreAffaireEntreDeuxDate(startDate,endDate);
		float pourcentage=(totalRecouvrementEntreDeuxDates/totalFacturesEntreDeuxDates)*100;
		return pourcentage;
	}
	

}