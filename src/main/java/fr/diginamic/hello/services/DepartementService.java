package fr.diginamic.hello.services;

import java.util.List;
import fr.diginamic.hello.entites.Departement;
import fr.diginamic.hello.repository.DepartementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service pour la gestion des départements.
 */
@Service
public class DepartementService {

    @Autowired
    private DepartementRepository departementRepository;

    /**
     * Ajoute un nouveau département dans la base de données.
     *
     * @param nouvelleDepartement le département à ajouter.
     * @return le département ajouté.
     */
    @Transactional
    public Departement insererDepartement(Departement nouvelleDepartement) {
        return departementRepository.save(nouvelleDepartement);
    }

    /**
     * Modifie un département existant dans la base de données.
     *
     * @param id l'identifiant du département à modifier.
     * @param departementModifiee les nouvelles informations du département.
     * @return le département modifié.
     */
    @Transactional
    public Departement modifierDepartement(Integer id, Departement departementModifiee) {
        Departement departement = extraireDepartementParCode(id);
        if (departement == null) {
            return null;
        } else {
            departementModifiee.setId(id);
            return insererDepartement(departementModifiee);
        }
    }

    /**
     * Supprime un département de la base de données.
     *
     * @param id l'identifiant du département à supprimer.
     */
    @Transactional
    public void supprimerDepartement(int id) {
        Departement departement = extraireDepartementParCode(id);
        if (departement != null) {
            departementRepository.delete(departement);
        }
    }

    /**
     * Récupère une liste paginée de départements.
     *
     * @param page numéro de la page à récupérer.
     * @param size taille de la page.
     * @return une liste de départements.
     */
    public List<Departement> extraireDepartements(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return departementRepository.findAll(pageable).getContent();
    }

    /**
     * Récupère un département par son identifiant.
     *
     * @param id l'identifiant du département.
     * @return le département correspondant.
     */
    public Departement extraireDepartementParCode(Integer id) {
        return departementRepository.findById(id).orElse(null);
    }

    /**
     * Récupère un département par son nom.
     *
     * @param nom le nom du département.
     * @return le département correspondant.
     */
    public Departement extraireDepartementParNom(String nom) {
        return departementRepository.findByNom(nom);
    }

    /**
     * Récupère la population totale d'un département.
     *
     * @param departementId l'identifiant du département.
     * @return la population totale du département.
     */
    public Integer extraireNbHabitants(int departementId) {
        return departementRepository.getPopulationTotale(departementId);
    }
}
