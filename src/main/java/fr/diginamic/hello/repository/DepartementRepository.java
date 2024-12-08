package fr.diginamic.hello.repository;

import fr.diginamic.hello.entites.Departement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

/**
 * Repository pour les opérations CRUD et paginées sur les entités Département.
 */
public interface DepartementRepository extends CrudRepository<Departement, Integer>, PagingAndSortingRepository<Departement, Integer> {

    /**
     * Récupère tous les départements avec pagination.
     *
     * @param pageable paramètre de pagination.
     * @return Page contenant les départements.
     */
    Page<Departement> findAll(Pageable pageable);

    /**
     * Récupère un département par son identifiant.
     *
     * @param id identifiant du département.
     * @return Département optionnel.
     */
    Optional<Departement> findById(Integer id);

    /**
     * Récupère un département par son nom.
     *
     * @param nom nom du département.
     * @return Département correspondant au nom.
     */
    Departement findByNom(String nom);

    /**
     * Calcule la population totale pour un département donné.
     *
     * @param departementId identifiant du département.
     * @return Population totale du département.
     */
    @Query("SELECT SUM(v.nbHabitants) FROM Ville v WHERE v.departement.id = :departementId")
    Integer getPopulationTotale(Integer departementId);

}

