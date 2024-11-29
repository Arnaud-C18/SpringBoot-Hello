package fr.diginamic.hello.Dto;

public class VilleDto {

    private Integer code;
    private Integer nbHabitants;
    private Integer codeDepartement;
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
