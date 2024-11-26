package fr.diginamic.hello.entites;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;

public class Ville {

    private static int compteurId = 0;
    @Min(1)
    int id;
    @NotNull
    @Size(min = 2, max = 50)
    String nom;
    @Min(1)
    int nbHabitants;

    public Ville(String nom, int nbHabitants) {
        this.id = compteurId++;
        this.nom = nom;
        this.nbHabitants = nbHabitants;
    }

    /**
     * Getter
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Setter
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter
     *
     * @return nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Setter
     *
     * @param nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Getter
     *
     * @return nbHabitants
     */
    public int getNbHabitants() {
        return nbHabitants;
    }

    /**
     * Setter
     *
     * @param nbHabitants
     */
    public void setNbHabitants(int nbHabitants) {
        this.nbHabitants = nbHabitants;
    }

    /**
     * @param o
     *
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Ville ville = (Ville) o;
        return Objects.equals(id, ville.id);
    }

    /**
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
