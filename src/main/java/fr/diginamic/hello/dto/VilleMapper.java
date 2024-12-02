package fr.diginamic.hello.dto;

import fr.diginamic.hello.entites.Departement;
import fr.diginamic.hello.entites.Ville;
import fr.diginamic.hello.services.DepartementService;

public class VilleMapper {
    public VilleDto toDto(Ville ville) {
        VilleDto dto = new VilleDto();

        dto.setCode(ville.getId());
        dto.setNbHabitants(ville.getNbHabitants());
        dto.setCodeDepartement(ville.getDepartement().getId());
        dto.setNom(ville.getNom());

        return dto;
    }

    public Ville toBean(VilleDto dto) {
        Ville ville = new Ville();

        DepartementService departementService = new DepartementService();
        Departement departement = departementService.extraireDepartementParCode(dto.getCode());

        ville.setId(dto.getCode());
        ville.setNbHabitants(dto.getNbHabitants());
        ville.setDepartement(departement);
        ville.setNom(dto.getNom());

        return ville;
    }
}
