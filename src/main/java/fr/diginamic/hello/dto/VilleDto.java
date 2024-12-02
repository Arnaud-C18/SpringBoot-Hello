package fr.diginamic.hello.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class VilleDto {

    private Integer code;

    @Min(value = 10, message = "La ville doit avoir au moins 10 habitants")
    private Integer nbHabitants;

    @Size(min = 2, max = 2, message = "Le code département doit comporter exactement 2 caractères")
    private Integer codeDepartement;

    @NotEmpty(message = "Le nom de la ville est obligatoire")
    @Size(min = 2, message = "Le nom de la ville doit comporter au moins 2 lettres")
    private String nom;

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

    /**
     * Getter
     *
     * @return codeDepartement
     */
    public Integer getCodeDepartement() {
        return codeDepartement;
    }

    /**
     * Setter
     *
     * @param codeDepartement
     */
    public void setCodeDepartement(Integer codeDepartement) {
        this.codeDepartement = codeDepartement;
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
}
