package fr.diginamic.hello.services;

import java.util.List;
import fr.diginamic.hello.entites.Departement;
import fr.diginamic.hello.repository.DepartementRepository;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class DepartementService {

    @PersistenceContext
    private DepartementRepository departementRepository;

    public List<Departement> extraireDepartements(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return departementRepository.findAll(pageable).getContent();
    }

    public Departement extraireDepartementParCode(Integer id) {
        return departementRepository.findById(id).get();
    }

    public Departement extraireDepartementParNom(String nom) {
        return departementRepository.findByNom(nom);
    }

    public Integer extraireNbHabitants(int departementId) {
        return departementRepository.getPopulationTotale(departementId);
    }

    @Transactional
    public Departement insererDepartement(Departement nouvelleDepartement) {
        return departementRepository.save(nouvelleDepartement);
    }

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

    @Transactional
    public void supprimerDepartement(int id) {
        Departement departement = extraireDepartementParCode(id);
        if (departement != null) {
            departementRepository.delete(departement);
        }
    }
}
