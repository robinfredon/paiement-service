package com.microservice.paiementservice.web.controller;

import com.microservice.paiementservice.dao.PaiementDAO;
import com.microservice.paiementservice.model.Paiement;
import com.microservice.paiementservice.web.exceptions.PaiementIntrouvableException;
import com.microservice.paiementservice.web.exceptions.PrixPaiementIncorrectException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.List;

@RestController
@Api(description = "API pour les opérations CRUD sur les paiements")
public class PaiementController {

	@Autowired
	private PaiementDAO PaiementDAO;
  private static final Logger logger = LoggerFactory.getLogger (PaiementController.class);
  @Autowired
  private HttpServletRequest requestContext ;

	@RequestMapping(value = "/Paiements", method = RequestMethod.GET)
	public List<Paiement> listePaiements() {
	  logger.info("Début d'appel au service Paiement pour la requête : " + requestContext.getHeader("req-id"));
	  List<Paiement> paiements = PaiementDAO.findAll();
	  return paiements;
	}

	@ApiOperation(value = "Récupère une paiement grâce à son ID")
	@RequestMapping(value = "/Paiements/{id}", method = RequestMethod.GET)
	public Paiement afficherUnePaiement(@PathVariable int id) {
	   logger.info("Début d'appel au service Paiements pour la requête : " + requestContext.getHeader("req-id"));
    Paiement paiements = PaiementDAO.findById(id);
			if (paiements == null)
				throw new PaiementIntrouvableException("Paiement introuvable");
		 return paiements;

	}

	@DeleteMapping (value = "/Paiements/{id}")
	public void supprimerPaiement(@PathVariable int id) {
    PaiementDAO.deleteById(id);
	}

	@PutMapping (value = "/Paiements")
	public void updatePaiement(@RequestBody Paiement paiement) {
    PaiementDAO.save(paiement);
	}

	@PostMapping(value = "/Paiements")
	public ResponseEntity<Void> ajouterComande(@RequestBody Paiement paiement) {
		if (paiement.getMontant() == 0)
			throw new PrixPaiementIncorrectException("Paiement nul impossible");
    Paiement paiementAdded = PaiementDAO.save(paiement);
		 if (paiementAdded == null)
			 return ResponseEntity.noContent().build();
				 URI location = ServletUriComponentsBuilder.fromCurrentRequest()
						 .path("/{id}").buildAndExpand(paiementAdded.getId()).toUri();
		 return ResponseEntity.created(location).build();
	 }

}
