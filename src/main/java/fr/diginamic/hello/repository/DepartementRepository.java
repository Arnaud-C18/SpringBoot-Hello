package fr.diginamic.hello.repository;

import fr.diginamic.hello.entites.Departement;
import fr.diginamic.hello.entites.Ville;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface DepartementRepository extends CrudRepository<Departement, Integer>, PagingAndSortingRepository<Departement, Integer> {

    Page<Departement> findAll(Pageable pageable);

    Optional<Departement> findById(Integer id);

    Departement findByNom(String nom);

    @Query("SELECT SUM(v.nbHabitants) FROM Ville v WHERE v.departement.id = :departementId")
    Integer getPopulationTotale(Integer departementId);

}
