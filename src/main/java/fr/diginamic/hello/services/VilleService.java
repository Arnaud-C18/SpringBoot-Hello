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

/**
 * Service pour la gestion des villes.
 */
@Service
public class VilleService {

    @Autowired
    private VilleRepository villeRepository;

    /**
     * Ajoute une nouvelle ville dans la base de données.
     * Vérifie que la ville a au moins 10 habitants et que son nom comporte au moins 2 lettres.
     * Si une ville avec le même nom existe déjà dans le même département, une exception est lancée.
     *
     * @param nouvelleVille la nouvelle ville à ajouter.
     * @return la ville ajoutée.
     * @throws CustomException si la ville ne respecte pas les contraintes.
     */
    @Transactional
    public Ville insererVille(Ville nouvelleVille) throws CustomException {
        if (nouvelleVille.getNbHabitants() < 10) {
            throw new CustomException("La ville doit avoir au moins 10 habitants");
        } else if (nouvelleVille.getNom().length() < 2) {
            throw new CustomException("Le nom de la ville doit comporter au moins 2 lettres");
        } else if (villeRepository.findByDepartementAndNom(nouvelleVille.getDepartement(), nouvelleVille.getNom()) != null) {
            throw new CustomException("Une ville portant ce nom existe déjà dans ce département");
        }
        return villeRepository.save(nouvelleVille);
    }

    /**
     * Modifie une ville existante dans la base de données.
     * Vérifie si la ville à modifier existe avant d'appliquer les modifications.
     *
     * @param id l'identifiant de la ville à modifier.
     * @param villeModifiee les nouvelles informations de la ville.
     * @return la ville modifiée.
     * @throws CustomException si la ville à modifier n'existe pas.
     */
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

    /**
     * Supprime une ville de la base de données.
     *
     * @param id l'identifiant de la ville à supprimer.
     */
    @Transactional
    public void supprimerVille(Integer id) {
        Ville ville = extraireVilleParId(id);
        if (ville != null) {
            villeRepository.delete(ville);
        }
    }

    /**
     * Récupère une liste paginée de toutes les villes.
     *
     * @param page le numéro de la page.
     * @param size la taille de la page.
     * @return une liste de villes.
     */
    public List<Ville> extraireVilles(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return villeRepository.findAll(pageable).getContent();
    }

    /**
     * Récupère une ville par son identifiant.
     *
     * @param id l'identifiant de la ville.
     * @return la ville correspondante.
     */
    public Ville extraireVilleParId(Integer id) {
        return villeRepository.findById(id).orElse(null);
    }

    /**
     * Récupère une ville par son nom.
     *
     * @param nom le nom de la ville.
     * @return la ville correspondante.
     */
    public Ville extraireVilleParNom(String nom) {
        return villeRepository.findByNom(nom);
    }

    /**
     * Récupère les villes dont le nom commence par une chaîne donnée.
     *
     * @param chaine la chaîne à rechercher au début du nom des villes.
     * @return une liste de villes dont le nom commence par la chaîne donnée.
     * @throws CustomException si aucune ville n'est trouvée.
     */
    public List<Ville> extraireVilleParChaine(String chaine) throws CustomException {
        List<Ville> villes = villeRepository.findByNomStartingWith(chaine);
        if (villes.isEmpty()) {
            throw new CustomException("Aucune ville dont le nom commence par " + chaine + " n’a été trouvée");
        } else {
            return villes;
        }
    }

    /**
     * Récupère les villes ayant un nombre d'habitants supérieur à une valeur donnée.
     *
     * @param nbHabitantsMin le nombre minimum d'habitants.
     * @return une liste de villes avec une population supérieure à nbHabitantsMin.
     * @throws CustomException si aucune ville n'est trouvée.
     */
    public List<Ville> extraireVilleNbHabitantsMin(int nbHabitantsMin) throws CustomException {
        List<Ville> villes = villeRepository.findByNbHabitantsGreaterThan(nbHabitantsMin);
        if (villes.isEmpty()) {
            throw new CustomException("Aucune ville n’a une population supérieure à " + nbHabitantsMin + " habitants");
        } else {
            return villes;
        }
    }

    /**
     * Récupère les villes ayant une population comprise entre deux valeurs données.
     *
     * @param nbHabitantsMin le nombre minimum d'habitants.
     * @param nbHabitantsMax le nombre maximum d'habitants.
     * @return une liste de villes avec une population comprise entre nbHabitantsMin et nbHabitantsMax.
     * @throws CustomException si aucune ville n'est trouvée.
     */
    public List<Ville> extraireVilleNbHabitantsEntre(int nbHabitantsMin, int nbHabitantsMax) throws CustomException {
        List<Ville> villes = villeRepository.findByNbHabitantsBetween(nbHabitantsMin, nbHabitantsMax);
        if (villes.isEmpty()) {
            throw new CustomException("Aucune ville n’a une population comprise entre " + nbHabitantsMin + " et " + nbHabitantsMax + " habitants");
        } else {
            return villes;
        }
    }

    /**
     * Récupère les villes d'un département avec une population supérieure à une valeur donnée.
     *
     * @param departementId l'identifiant du département.
     * @param nbHabitantsMin le nombre minimum d'habitants.
     * @return une liste de villes dans le département avec une population supérieure à nbHabitantsMin.
     * @throws CustomException si aucune ville n'est trouvée.
     */
    public List<Ville> extraireVilleDepartementNbHabitantsMin(int departementId, int nbHabitantsMin) throws CustomException {
        List<Ville> villes = villeRepository.findByDepartementIdAndNbHabitantsIsGreaterThan(departementId, nbHabitantsMin);
        if (villes.isEmpty()) {
            throw new CustomException("Aucune ville n’a une population supérieure à " + nbHabitantsMin + " dans le département " + departementId);
        } else {
            return villes;
        }
    }

    /**
     * Récupère les villes d'un département ayant une population comprise entre deux valeurs données.
     *
     * @param departementId l'identifiant du département.
     * @param nbHabitantsMin le nombre minimum d'habitants.
     * @param nbHabitantsMax le nombre maximum d'habitants.
     * @return une liste de villes dans le département avec une population comprise entre nbHabitantsMin et nbHabitantsMax.
     * @throws CustomException si aucune ville n'est trouvée.
     */
    public List<Ville> extraireVilleDepartementNbHabitantsEntre(int departementId, int nbHabitantsMin, int nbHabitantsMax) throws CustomException {
        List<Ville> villes = villeRepository.findByDepartementIdAndNbHabitantsBetween(departementId, nbHabitantsMin, nbHabitantsMax);
        if (villes.isEmpty()) {
            throw new CustomException("Aucune ville n’a une population comprise entre " + nbHabitantsMin + " et " + nbHabitantsMax + " dans le département " + departementId);
        } else {
            return villes;
        }
    }

    /**
     * Récupère les n villes les plus peuplées d'un département.
     *
     * @param departementId l'identifiant du département.
     * @param n le nombre de villes à récupérer.
     * @return une liste des n villes les plus peuplées du département.
     * @throws CustomException si aucune ville n'est trouvée.
     */
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
