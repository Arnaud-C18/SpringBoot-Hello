package fr.diginamic.hello.repository;

import fr.diginamic.hello.entites.Departement;
import fr.diginamic.hello.entites.Ville;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

/**
 * Repository pour les opérations CRUD et paginées sur les entités Ville.
 */
public interface VilleRepository extends CrudRepository<Ville, Integer>, PagingAndSortingRepository<Ville, Integer> {

    /**
     * Récupère toutes les villes avec pagination.
     *
     * @param pageable paramètre de pagination.
     * @return Page contenant les villes.
     */
    Page<Ville> findAll(Pageable pageable);

    /**
     * Récupère une ville par son identifiant.
     *
     * @param id identifiant de la ville.
     * @return Ville optionnelle.
     */
    Optional<Ville> findById(Integer id);

    /**
     * Récupère une ville par son nom.
     *
     * @param nom nom de la ville.
     * @return Ville correspondant au nom.
     */
    Ville findByNom(String nom);

    /**
     * Récupère une ville par son département et son nom.
     *
     * @param departement département associé.
     * @param nom nom de la ville.
     * @return Ville correspondant au département et au nom.
     */
    Ville findByDepartementAndNom(Departement departement, String nom);

    /**
     * Récupère toutes les villes dont le nom commence par une chaîne donnée.
     *
     * @param chaine chaîne de caractères.
     * @return Liste des villes correspondantes.
     */
    List<Ville> findByNomStartingWith(String chaine);

    /**
     * Récupère toutes les villes ayant un nombre d'habitants supérieur à une valeur donnée.
     *
     * @param nbHabitants seuil minimal d'habitants.
     * @return Liste des villes correspondantes.
     */
    List<Ville> findByNbHabitantsGreaterThan(Integer nbHabitants);

    /**
     * Récupère toutes les villes ayant un nombre d'habitants compris entre deux valeurs.
     *
     * @param nbHabitantsMin nombre minimal d'habitants.
     * @param nbHabitantsMax nombre maximal d'habitants.
     * @return Liste des villes correspondantes.
     */
    List<Ville> findByNbHabitantsBetween(Integer nbHabitantsMin, Integer nbHabitantsMax);

    /**
     * Récupère toutes les villes d'un département ayant un nombre d'habitants supérieur à une valeur donnée.
     *
     * @param departementId identifiant du département.
     * @param nbHabitants seuil minimal d'habitants.
     * @return Liste des villes correspondantes.
     */
    List<Ville> findByDepartementIdAndNbHabitantsIsGreaterThan(Integer departementId, Integer nbHabitants);

    /**
     * Récupère toutes les villes d'un département ayant un nombre d'habitants compris entre deux valeurs.
     *
     * @param departementId identifiant du département.
     * @param nbHabitantsMin nombre minimal d'habitants.
     * @param nbHabitantsMax nombre maximal d'habitants.
     * @return Liste des villes correspondantes.
     */
    List<Ville> findByDepartementIdAndNbHabitantsBetween(Integer departementId, Integer nbHabitantsMin, Integer nbHabitantsMax);

    /**
     * Récupère toutes les villes d'un département triées par nombre d'habitants décroissant.
     *
     * @param departementId identifiant du département.
     * @param pageable paramètre de pagination.
     * @return Liste des villes correspondantes triées.
     */
    List<Ville> findByDepartementIdOrderByNbHabitantsDesc(Integer departementId, Pageable pageable);
}
