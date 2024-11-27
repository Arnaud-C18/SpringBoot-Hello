package fr.diginamic.hello.services;

import java.util.List;
import fr.diginamic.hello.entites.Departement;
import fr.diginamic.hello.entites.Ville;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class DepartementService {

    @PersistenceContext
    private EntityManager em;

    public List<Departement> extraireDepartements() {
        TypedQuery<Departement> query = em.createQuery("SELECT d FROM Departement d", Departement.class);
        return query.getResultList();
    }

    public Departement extraireDepartementParCode(int id) {
        return em.find(Departement.class, id);
    }

    public Departement extraireDepartementParNom(String nom) {
        return em.createQuery("SELECT d FROM Departement d WHERE d.nom = :nom", Departement.class)
                .setParameter("nom", nom)
                .getSingleResult();
    }

    public List<Ville> nPlusGrandeVilles(int code, int n) {
        return em.createQuery("SELECT v FROM Ville v WHERE v.departement.code = :code ORDER BY v.nbHabitants DESC", Ville.class)
                .setParameter("code", code)
                .setMaxResults(n)
                .getResultList();

    }

    public List<Ville> villesPopEntreMinEtMax(int code, Long min, Long max) {
        return em.createQuery("SELECT v FROM Ville v WHERE v.departement.code = :code AND v.nbHabitants >= :min AND v.nbHabitants <= :max", Ville.class)
                .setParameter("code", code)
                .setParameter("min", min)
                .setParameter("max", max)
                .getResultList();
    }

    @Transactional
    public List<Departement> insererDepartement(Departement nouvelleDepartement) {
        em.persist(nouvelleDepartement);
        return extraireDepartements();
    }

    @Transactional
    public List<Departement> modifierDepartement(int code, Departement departementModifiee) {
        Departement departement = extraireDepartementParCode(code);
        if (departement == null) {
            return null;
        } else {
            departement.setNom(departementModifiee.getNom());
            return extraireDepartements();
        }
    }

    @Transactional
    public List<Departement> supprimerDepartement(int id) {
        Departement departement = extraireDepartementParCode(id);
        if (departement == null) {
            return null;
        } else {
            em.remove(departement);
            return extraireDepartements();
        }
    }
}
