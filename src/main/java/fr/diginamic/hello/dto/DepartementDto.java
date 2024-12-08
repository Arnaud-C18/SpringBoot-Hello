package fr.diginamic.hello.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class DepartementDto {

    @Schema(description = "Code du département", example = "75", required = true, minLength = 2, maxLength = 3)
    @Size(min = 2, max = 3, message = "Le code département doit comporter entre 2 et 3 caractères")
    private Integer code;

    @Schema(description = "Nom du département", example = "Paris", required = true, minLength = 3)
    @NotEmpty(message = "Le nom du département est obligatoire")
    @Size(min = 3, message = "Le nom du département doit comporter au moins 3 lettres")
    private String nom;

    @Schema(description = "Nombre d'habitants du département", example = "2200000")
    private Integer nbHabitants;

    // Getters et setters

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getNbHabitants() {
        return nbHabitants;
    }

    public void setNbHabitants(Integer nbHabitants) {
        this.nbHabitants = nbHabitants;
    }
}

