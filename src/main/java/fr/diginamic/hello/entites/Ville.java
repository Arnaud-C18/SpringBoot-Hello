package fr.diginamic.hello.entites;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Objects;

@Entity
@Table(name = "ville")
public class Ville {

    @Column(name = "ID_REGION")
    private String idRegion;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "NAME", length = 50)
    @NotEmpty(message = "Le nom de la ville est obligatoire")
    @Size(min = 2, message = "Le nom de la ville doit comporter au moins 2 lettres")
    private String nom;

    @ManyToOne
    @JoinColumn(name = "DEPARTMENT_ID")
    @JsonBackReference
    private Departement departement;

    @Column(name = "NB_INHABITANTS")
    @Min(value = 10, message = "La ville doit avoir au moins 10 habitants")
    private Integer nbHabitants;


    public Ville() {}

    /**
     * Getter
     *
     * @return idRegion
     */
    public String getIdRegion() {
        return idRegion;
    }

    /**
     * Setter
     *
     * @param idRegion
     */
    public void setIdRegion(String idRegion) {
        this.idRegion = idRegion;
    }

    /**
     * Getter
     *
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Setter
     *
     * @param id
     */
    public void setId(Integer id) {
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
     * @return departement
     */
    public Departement getDepartement() {
        return departement;
    }

    /**
     * Setter
     *
     * @param departement
     */
    public void setDepartement(Departement departement) {
        this.departement = departement;
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
     * @return Integer
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
