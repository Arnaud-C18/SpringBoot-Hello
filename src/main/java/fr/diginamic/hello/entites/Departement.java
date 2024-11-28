package fr.diginamic.hello.entites;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "departement")
public class Departement {

    @Id
    private int id;

    @Column(name = "CODE")
    private String code;

    @Column(name = "NAME", length = 50)
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
