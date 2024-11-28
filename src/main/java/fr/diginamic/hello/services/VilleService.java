package fr.diginamic.hello.services;

import java.util.List;

import fr.diginamic.hello.repository.VilleRepository;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import fr.diginamic.hello.entites.Ville;
import org.springframework.transaction.annotation.Transactional;


@Service
public class VilleService {

    @PersistenceContext
    private VilleRepository villeRepository;

    public List<Ville> extraireVilles(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return villeRepository.findAll(pageable).getContent();
    }

    public Ville extraireVilleParId(Integer id) {
        return villeRepository.findById(id).get();
    }

    public Ville extraireVilleParNom(String nom) {
        return villeRepository.findByNom(nom);
    }

    public List<Ville> extraireVilleParChaine(String chaine) {
        return villeRepository.findByNomStartingWith(chaine);
    }

    public List<Ville> extraireVilleNbHabitantsMin(int nbHabitants) {
        return villeRepository.findByNbHabitantsGreaterThan(nbHabitants);
    }

    public List<Ville> extraireVilleNbHabitantsEntre(int nbHabitantsMin, int nbHabitantsMax) {
        return villeRepository.findByNbHabitantsBetween(nbHabitantsMin, nbHabitantsMax);
    }

    public List<Ville> extraireVilleDepartementNbHabitantsMin(int departementId, int nbHabitantsMin) {
        return villeRepository.findByDepartementIdAndNbHabitantsIsGreaterThan(departementId, nbHabitantsMin);
    }

    public List<Ville> extraireVilleDepartementNbHabitantsEntre(int departementId, int nbHabitantsMin, int nbHabitantsMax) {
        return villeRepository.findByDepartementIdAndNbHabitantsBetween(departementId, nbHabitantsMin, nbHabitantsMax);
    }

    public List<Ville> extraireNVillePlusPeupleeDepartement(int departementId, int n) {
        Pageable pageable = PageRequest.of(0, n);
        return villeRepository.findByDepartementIdOrderByNbHabitantsDesc(departementId, pageable);
    }

    @Transactional
    public Ville insererVille(Ville nouvelleVille) {
        return villeRepository.save(nouvelleVille);
    }

    @Transactional
    public Ville modifierVille(Integer id, Ville villeModifiee) {
        Ville ville = extraireVilleParId(id);
        if (ville == null) {
            return null;
        } else {
            villeModifiee.setId(id);
            return insererVille(villeModifiee);
        }
    }

    @Transactional
    public void supprimerVille(Integer id) {
        Ville ville = extraireVilleParId(id);
        if (ville != null) {
            villeRepository.delete(ville);
        }
    }
}