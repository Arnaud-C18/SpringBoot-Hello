package fr.diginamic.hello.entites;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.checkerframework.common.aliasing.qual.Unique;

import java.util.List;

@Entity
@Table(name = "departement")
public class Departement {

    @Id
    private Integer id;

    @Column(name = "CODE")
    @Size(min = 2, max = 3, message = "Le code département doit comporter entre 2 et 3 caractères")
    private String code;

    @Column(name = "NAME", length = 50)
    @NotEmpty(message = "Le nom du département est obligatoire")
    @Size(min = 3, message = "Le nom du département doit comporter au moins 3 lettres")
    private String nom;

    @OneToMany(mappedBy = "departement", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Ville> villes;

    public Departement() {}

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
     * @return code
     */
    public String getCode() {
        return code;
    }

    /**
     * Setter
     *
     * @param code
     */
    public void setCode(String code) {
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
     * @return villes
     */
    public List<Ville> getVilles() {
        return villes;
    }

    /**
     * Setter
     *
     * @param villes
     */
    public void setVilles(List<Ville> villes) {
        this.villes = villes;
    }
}
