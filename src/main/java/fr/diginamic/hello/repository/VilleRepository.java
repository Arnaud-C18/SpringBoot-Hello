package fr.diginamic.hello.repository;

import fr.diginamic.hello.entites.Ville;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;


import java.util.List;
import java.util.Optional;

public interface VilleRepository extends CrudRepository<Ville, Integer>, PagingAndSortingRepository<Ville, Integer> {

    Page<Ville> findAll(Pageable pageable);

    Optional<Ville> findById(Integer id);

    Ville findByNom(String nom);

    List<Ville> findByNomStartingWith(String chaine);

    List<Ville> findByNbHabitantsGreaterThan(Integer nbHabitants);

    List<Ville> findByNbHabitantsBetween(Integer nbHabitantsMin, Integer nbHabitantsMax);

    List<Ville> findByDepartementIdAndNbHabitantsIsGreaterThan(Integer departementId, Integer nbHabitants);

    List<Ville> findByDepartementIdAndNbHabitantsBetween(Integer departementId, Integer nbHabitantsMin, Integer nbHabitantsMax);

    List<Ville> findByDepartementIdOrderByNbHabitantsDesc(Integer departementId, Pageable pageable);
}
