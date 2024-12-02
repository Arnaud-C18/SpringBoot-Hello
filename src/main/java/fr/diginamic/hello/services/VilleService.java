package fr.diginamic.hello.services;

import java.util.List;

import fr.diginamic.hello.exception.CustomException;
import fr.diginamic.hello.repository.VilleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import fr.diginamic.hello.entites.Ville;
import org.springframework.transaction.annotation.Transactional;


@Service
public class VilleService {

    @Transactional
    public Ville insererVille(Ville nouvelleVille) throws CustomException {
        if (nouvelleVille.getNbHabitants() < 10) {
            throw new CustomException("La ville doit avoir au moins 10 habitants");
        } else if (nouvelleVille.getNom().length() < 2) {
            throw new CustomException("Le nom de la ville doit comporter au moins 2 lettres");
        } else if (villeRepository.findByDepartementAndNom(nouvelleVille.getDepartement(), nouvelleVille.getNom()) != null) {
            throw new CustomException("Une ville portant ce nom existe déja dans ce département");
        }
        return villeRepository.save(nouvelleVille);
    }

    @Autowired
    private VilleRepository villeRepository;

    @Transactional
    public Ville modifierVille(Integer id, Ville villeModifiee) throws CustomException {
        Ville ville = extraireVilleParId(id);
        if (ville == null) {
            throw new CustomException("La ville à modifier n'existe pas");
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

    public List<Ville> extraireVilleParChaine(String chaine) throws CustomException {
        List<Ville> villes = villeRepository.findByNomStartingWith(chaine);
        if (villes.isEmpty()) {
            throw new CustomException("Aucune ville dont le nom commence par " + chaine + " n’a été trouvée");
        } else {
            return villes;
        }
    }

    public List<Ville> extraireVilleNbHabitantsMin(int nbHabitantsMin) throws CustomException {
        List<Ville> villes = villeRepository.findByNbHabitantsGreaterThan(nbHabitantsMin);
        if (villes.isEmpty()) {
            throw new CustomException("Aucune ville n’a une population supérieure à " + nbHabitantsMin + "habitants");
        } else {
            return villes;
        }
    }

    public List<Ville> extraireVilleNbHabitantsEntre(int nbHabitantsMin, int nbHabitantsMax) throws CustomException {
        List<Ville> villes = villeRepository.findByNbHabitantsBetween(nbHabitantsMin, nbHabitantsMax);
        if (villes.isEmpty()) {
            throw new CustomException("Aucune ville n’a une population comprise entre " + nbHabitantsMin + " et " + nbHabitantsMax + "habitants");
        } else {
            return villes;
        }
    }

    public List<Ville> extraireVilleDepartementNbHabitantsMin(int departementId, int nbHabitantsMin) throws CustomException {
        List<Ville> villes = villeRepository.findByDepartementIdAndNbHabitantsIsGreaterThan(departementId, nbHabitantsMin);
        if (villes.isEmpty()) {
            throw new CustomException("Aucune ville n’a une population supérieure à " + nbHabitantsMin + " dans le département " + departementId);
        } else {
            return villes;
        }
    }

    public List<Ville> extraireVilleDepartementNbHabitantsEntre(int departementId, int nbHabitantsMin, int nbHabitantsMax) throws CustomException {
        List<Ville> villes = villeRepository.findByDepartementIdAndNbHabitantsBetween(departementId, nbHabitantsMin, nbHabitantsMax);
        if (villes.isEmpty()) {
            throw new CustomException("Aucune ville n’a une population comprise entre " + nbHabitantsMin + " et " + nbHabitantsMax + " dans le département " + departementId);
        } else {
            return villes;
        }
    }

    public List<Ville> extraireNVillePlusPeupleeDepartement(int departementId, int n) throws CustomException {
        Pageable pageable = PageRequest.of(0, n);
        List<Ville> villes = villeRepository.findByDepartementIdOrderByNbHabitantsDesc(departementId, pageable);
        if (villes.isEmpty()) {
            throw new CustomException("Aucune ville dans le département " + departementId);
        } else {
            return villes;
        }
    }
}