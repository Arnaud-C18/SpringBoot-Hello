package fr.diginamic.hello.entites;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Objects;

@Schema(description = "Représente une ville avec ses caractéristiques principales.")
@Entity
@Table(name = "ville")
public class Ville {

    @Schema(description = "Identifiant de la région à laquelle appartient la ville", example = "11")
    @Column(name = "ID_REGION")
    private String idRegion;

    @Schema(description = "Identifiant unique de la ville", example = "101")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Schema(description = "Nom de la ville (au moins 2 caractères)", example = "Paris", minLength = 2)
    @Column(name = "NAME", length = 50)
    @NotEmpty(message = "Le nom de la ville est obligatoire")
    @Size(min = 2, message = "Le nom de la ville doit comporter au moins 2 lettres")
    private String nom;

    @Schema(description = "Département auquel appartient la ville")
    @ManyToOne
    @JoinColumn(name = "DEPARTMENT_ID")
    @JsonBackReference
    private Departement departement;

    @Schema(description = "Nombre d'habitants dans la ville (minimum 10)", example = "2500000", minimum = "10")
    @Column(name = "NB_INHABITANTS")
    @Min(value = 10, message = "La ville doit avoir au moins 10 habitants")
    private Integer nbHabitants;

    public Ville() {}

    // Getters and Setters

    public String getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(String idRegion) {
        this.idRegion = idRegion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Ville ville = (Ville) o;
        return Objects.equals(id, ville.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
