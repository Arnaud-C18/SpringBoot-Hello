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
        return em.find(Ville.class, nom);
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


//    private List<Ville> villes = new ArrayList<>(Arrays.asList(
//            new Ville("Paris",2133111),
//            new Ville("Marseille", 873076),
//            new Ville("Lyon", 522250)
//    ));
//
//    public List<Ville> getAllVilles(){
//        return this.villes;
//    }
//
//    public boolean addVille(Ville town) {
//        Ville result = findVilleByNom(town.getNom());
//        if (result!=null) {
//            return false;
//        }
//        this.villes.add(town);
//        return true;
//    }
//
//    public boolean updateVille(Ville town) {
//        Ville result = findVilleById(town.getId());
//        if (result!=null) {
//            result.setNom(town.getNom());
//            result.setNbHabitants(town.getNbHabitants());
//            return true;
//        }
//        return false;
//    }
//
//    public boolean deleteVille(Long id) {
//        Ville result = findVilleById(id);
//        if (result!=null) {
//            this.villes.remove(result);
//            return true;
//        }
//        return false;
//    }
//
//    public Ville findVilleById(Long id) {
//        Ville result = this.villes.stream()
//                .filter(element -> id.equals(element.getId()))
//                .findAny().orElse(null);
//        return result;
//    }
//
//    private Ville findVilleByNom(String name) {
//        Ville result = this.villes.stream()
//                .filter(element -> name.equals(element.getNom()))
//                .findAny().orElse(null);
//        return result;
//    }


}