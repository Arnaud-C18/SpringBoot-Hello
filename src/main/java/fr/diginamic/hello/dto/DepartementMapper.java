package fr.diginamic.hello.dto;

import fr.diginamic.hello.entites.Departement;
import fr.diginamic.hello.services.DepartementService;

public class DepartementMapper {
    public DepartementDto toDto(Departement departement) {
        DepartementDto dto = new DepartementDto();
        DepartementService departementService = new DepartementService();

        dto.setCode(departement.getId());
        dto.setNom(departement.getNom());
        dto.setNbHabitants(departementService.extraireNbHabitants(departement.getId()));

        return dto;
    }

    public Departement toBean(DepartementDto dto) {
        Departement departement = new Departement();
        DepartementService departementService = new DepartementService();

        departement.setId(dto.getCode());
        departement.setNom(dto.getNom());

        return departement;
    }
}
