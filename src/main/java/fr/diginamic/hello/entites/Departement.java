package fr.diginamic.hello.entites;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

@Schema(description = "Représente un département avec ses caractéristiques et ses villes.")
@Entity
@Table(name = "departement")
public class Departement {

    @Schema(description = "Identifiant unique du département", example = "1")
    @Id
    private Integer id;

    @Schema(description = "Code du département (entre 2 et 3 caractères)", example = "75", minLength = 2, maxLength = 3)
    @Column(name = "CODE")
    @Size(min = 2, max = 3, message = "Le code département doit comporter entre 2 et 3 caractères")
    private String code;

    @Schema(description = "Nom du département (au moins 3 lettres)", example = "Paris", minLength = 3)
    @Column(name = "NAME", length = 50)
    @NotEmpty(message = "Le nom du département est obligatoire")
    @Size(min = 3, message = "Le nom du département doit comporter au moins 3 lettres")
    private String nom;

    @Schema(description = "Liste des villes appartenant à ce département")
    @OneToMany(mappedBy = "departement", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Ville> villes;

    public Departement() {}

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Ville> getVilles() {
        return villes;
    }

    public void setVilles(List<Ville> villes) {
        this.villes = villes;
    }
}
