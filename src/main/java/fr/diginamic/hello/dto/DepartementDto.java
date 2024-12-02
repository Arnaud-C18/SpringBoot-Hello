package fr.diginamic.hello.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class DepartementDto {

    @Size(min = 2, max = 3, message = "Le code département doit comporter entre 2 et 3 caractères")
    private Integer code;

    @NotEmpty(message = "Le nom du département est obligatoire")
    @Size(min = 3, message = "Le nom du département doit comporter au moins 3 lettres")
    private String nom;

    private Integer nbHabitants;

    /**
     * Getter
     *
     * @return code
     */
    public Integer getCode() {
        return code;
    }

    /**
     * Setter
     *
     * @param code
     */
    public void setCode(Integer code) {
        this.code = code;
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
    public Integer getNbHabitants() {
        return nbHabitants;
    }

    /**
     * Setter
     *
     * @param nbHabitants
     */
    public void setNbHabitants(Integer nbHabitants) {
        this.nbHabitants = nbHabitants;
    }
}
