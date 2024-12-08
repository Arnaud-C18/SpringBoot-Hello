package fr.diginamic.hello.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Schema(description = "Représente une ville avec ses caractéristiques.")
public class VilleDto {

    @Schema(description = "Identifiant unique de la ville", example = "123")
    private Integer code;

    @Schema(description = "Nombre d'habitants dans la ville", example = "25000", minimum = "10")
    @Min(value = 10, message = "La ville doit avoir au moins 10 habitants")
    private Integer nbHabitants;

    @Schema(description = "Code du département auquel appartient la ville (2 caractères)", example = "75")
    @Size(min = 2, max = 2, message = "Le code département doit comporter exactement 2 caractères")
    private Integer codeDepartement;

    @Schema(description = "Nom de la ville", example = "Paris", minLength = 2)
    @NotEmpty(message = "Le nom de la ville est obligatoire")
    @Size(min = 2, message = "Le nom de la ville doit comporter au moins 2 lettres")
    private String nom;

    // Getters and Setters

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getNbHabitants() {
        return nbHabitants;
    }

    public void setNbHabitants(Integer nbHabitants) {
        this.nbHabitants = nbHabitants;
    }

    public Integer getCodeDepartement() {
        return codeDepartement;
    }

    public void setCodeDepartement(Integer codeDepartement) {
        this.codeDepartement = codeDepartement;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
