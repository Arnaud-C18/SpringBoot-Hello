package fr.diginamic.hello.Dto;

public class DepartementDto {

    private Integer code;
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
