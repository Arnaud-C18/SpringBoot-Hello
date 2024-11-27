package fr.diginamic.hello.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import fr.diginamic.hello.entites.Ville;


@Service
public class VilleService {

    private List<Ville> villes = new ArrayList<>(Arrays.asList(
            new Ville("Paris",2133111),
            new Ville("Marseille", 873076),
            new Ville("Lyon", 522250)
    ));

    public List<Ville> getAllVilles(){
        return this.villes;
    }

    public boolean addVille(Ville town) {
        Ville result = findVilleByNom(town.getNom());
        if (result!=null) {
            return false;
        }
        this.villes.add(town);
        return true;
    }

    public boolean updateVille(Ville town) {
        Ville result = findVilleById(town.getId());
        if (result!=null) {
            result.setNom(town.getNom());
            result.setNbHabitants(town.getNbHabitants());
            return true;
        }
        return false;
    }

    public boolean deleteVille(Long id) {
        Ville result = findVilleById(id);
        if (result!=null) {
            this.villes.remove(result);
            return true;
        }
        return false;
    }

    public Ville findVilleById(Long id) {
        Ville result = this.villes.stream()
                .filter(element -> id.equals(element.getId()))
                .findAny().orElse(null);
        return result;
    }

    private Ville findVilleByNom(String name) {
        Ville result = this.villes.stream()
                .filter(element -> name.equals(element.getNom()))
                .findAny().orElse(null);
        return result;
    }


}