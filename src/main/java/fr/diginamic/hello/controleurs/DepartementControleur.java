package fr.diginamic.hello.controleurs;

import java.util.List;
import fr.diginamic.hello.entites.Departement;
import fr.diginamic.hello.services.DepartementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/departements")
public class DepartementControleur {

    @Autowired
    private DepartementService departementService;

    @Operation(summary = "Créer un département", description = "Permet de créer un nouveau département.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Le département a été créé avec succès."),
            @ApiResponse(responseCode = "400", description = "Données invalides.")
    })
    @PostMapping
    public void createDepartement(@RequestBody Departement departement) {
        departementService.insererDepartement(departement);
    }

    @Operation(summary = "Mettre à jour un département", description = "Met à jour un département existant en fonction de son code.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Le département a été mis à jour avec succès."),
            @ApiResponse(responseCode = "400", description = "Données invalides."),
            @ApiResponse(responseCode = "404", description = "Département non trouvé.")
    })
    @PutMapping("/{code}")
    public void updateDepartement(
            @Parameter(description = "Le code du département à mettre à jour") @PathVariable Integer code,
            @RequestBody Departement nouvelleDepartement) {
        departementService.modifierDepartement(code, nouvelleDepartement);
    }

    @Operation(summary = "Supprimer un département", description = "Supprime un département en fonction de son code.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Le département a été supprimé avec succès."),
            @ApiResponse(responseCode = "404", description = "Département non trouvé.")
    })
    @DeleteMapping("/{code}")
    public void deleteDepartement(@Parameter(description = "Le code du département à supprimer") @PathVariable Integer code) {
        departementService.supprimerDepartement(code);
    }

    @Operation(summary = "Lire tous les départements", description = "Récupère la liste des départements avec la possibilité de paginer les résultats.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des départements récupérée avec succès."),
            @ApiResponse(responseCode = "400", description = "Paramètres de pagination invalides.")
    })
    @GetMapping
    public List<Departement> readDepartements(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return departementService.extraireDepartements(page, size);
    }

    @Operation(summary = "Lire un département par code", description = "Récupère un département spécifique par son code.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Département récupéré avec succès."),
            @ApiResponse(responseCode = "404", description = "Département non trouvé.")
    })
    @GetMapping("/code/{code}")
    public Departement readDepartementId(@Parameter(description = "Le code du département à récupérer") @PathVariable Integer code) {
        return departementService.extraireDepartementParCode(code);
    }

    @Operation(summary = "Lire un département par nom", description = "Récupère un département spécifique par son nom.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Département récupéré avec succès."),
            @ApiResponse(responseCode = "404", description = "Département non trouvé.")
    })
    @GetMapping("/nom/{nom}")
    public Departement readDepartementNom(@Parameter(description = "Le nom du département à récupérer") @PathVariable String nom) {
        return departementService.extraireDepartementParNom(nom);
    }
}