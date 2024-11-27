package fr.diginamic.hello.services;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Service;
import fr.diginamic.hello.entites.Ville;
import org.springframework.transaction.annotation.Transactional;


@Service
public class VilleService {

    @PersistenceContext
    private EntityManager em;

    public List<Ville> extraireVilles() {
        TypedQuery<Ville> query = em.createQuery("SELECT v FROM Ville v", Ville.class);
        return query.getResultList();
    }

    public Ville extraireVilleParId(Long id) {
        return em.find(Ville.class, id);
    }

    public Ville extraireVilleParNom(String nom) {
        return em.createQuery("SELECT v FROM Ville v WHERE v.nom = :nom", Ville.class)
                .setParameter("nom", nom)
                .getSingleResult();
    }

    @Transactional
    public List<Ville> insererVille(Ville nouvelleVille) {
        em.persist(nouvelleVille);
        return extraireVilles();
    }

    @Transactional
    public List<Ville> modifierVille(Long id, Ville villeModifiee) {
        Ville ville = extraireVilleParId(id);
        if (ville == null) {
            return null;
        } else {
            ville.setNom(villeModifiee.getNom());
            ville.setNbHabitants(villeModifiee.getNbHabitants());
            return extraireVilles();
        }
    }

    @Transactional
    public List<Ville> supprimerVille(Long id) {
        Ville ville = extraireVilleParId(id);
        if (ville == null) {
            return null;
        } else {
            em.remove(ville);
            return extraireVilles();
        }
    }
}