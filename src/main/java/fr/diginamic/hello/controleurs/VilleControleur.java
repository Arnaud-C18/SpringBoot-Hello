package fr.diginamic.hello.controleurs;

import java.util.List;
import fr.diginamic.hello.entites.Ville;
import fr.diginamic.hello.exception.CustomException;
import fr.diginamic.hello.services.VilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/villes")
public class VilleControleur {

    @Autowired
    private VilleService villeService;

    @Operation(summary = "Créer une nouvelle ville", description = "Permet de créer une nouvelle ville dans la base de données.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ville créée avec succès.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Ville.class))),
            @ApiResponse(responseCode = "400", description = "Si la ville est mal formatée ou contient des erreurs.", content = @Content)
    })
    @PostMapping
    public Ville createVille(@RequestBody Ville ville) throws CustomException {
        return villeService.insererVille(ville);
    }

    @Operation(summary = "Mettre à jour une ville", description = "Permet de mettre à jour une ville existante.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ville mise à jour avec succès.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Ville.class))),
            @ApiResponse(responseCode = "400", description = "Si les données de la ville à modifier sont invalides.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Si la ville n'existe pas.", content = @Content)
    })
    @PutMapping("/{id}")
    public Ville updateVille(@PathVariable Integer id, @RequestBody Ville nouvelleVille) throws CustomException {
        return villeService.modifierVille(id, nouvelleVille);
    }

    @Operation(summary = "Supprimer une ville", description = "Permet de supprimer une ville par son identifiant.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Ville supprimée avec succès."),
            @ApiResponse(responseCode = "404", description = "Si la ville n'existe pas.", content = @Content)
    })
    @DeleteMapping("/{id}")
    public void deleteVille(@PathVariable Integer id) {
        villeService.supprimerVille(id);
    }

    @Operation(summary = "Lister les villes", description = "Permet de récupérer une liste paginée des villes.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des villes récupérée avec succès.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Ville.class)))
    })
    @GetMapping
    public List<Ville> readVilles(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return villeService.extraireVilles(page, size);
    }

    @Operation(summary = "Récupérer une ville par son identifiant", description = "Permet de récupérer une ville spécifique en utilisant son identifiant.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ville récupérée avec succès.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Ville.class))),
            @ApiResponse(responseCode = "404", description = "Si la ville n'existe pas.", content = @Content)
    })
    @GetMapping("/id/{id}")
    public Ville readVilleId(@PathVariable Integer id) {
        return villeService.extraireVilleParId(id);
    }

    @Operation(summary = "Récupérer une ville par son nom", description = "Permet de récupérer une ville en utilisant son nom.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ville récupérée avec succès.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Ville.class))),
            @ApiResponse(responseCode = "404", description = "Si la ville n'existe pas.", content = @Content)
    })
    @GetMapping("/nom/{nom}")
    public Ville readVilleNom(@PathVariable String nom) {
        return villeService.extraireVilleParNom(nom);
    }

    @Operation(summary = "Recherche de villes par chaîne de caractères", description = "Permet de rechercher des villes contenant une chaîne spécifique dans leur nom.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des villes correspondantes.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Ville.class))),
            @ApiResponse(responseCode = "400", description = "Si la chaîne de recherche est mal formatée.", content = @Content)
    })
    @GetMapping("/rechercheParNom")
    public List<Ville> readVilleChaine(@RequestParam String chaine) throws CustomException {
        return villeService.extraireVilleParChaine(chaine);
    }

    @Operation(summary = "Recherche de villes par nombre minimum d'habitants", description = "Permet de récupérer les villes dont le nombre d'habitants est supérieur à un certain minimum.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des villes récupérées avec succès.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Ville.class))),
            @ApiResponse(responseCode = "400", description = "Si le nombre d'habitants minimum est mal formaté.", content = @Content)
    })
    @GetMapping("/rechercheParHabitantsMin")
    public List<Ville> readVilleNbHabitantsMin(@RequestParam Integer nbHabitantsMin ) throws CustomException {
        return villeService.extraireVilleNbHabitantsMin(nbHabitantsMin);
    }

    @Operation(summary = "Recherche de villes par nombre d'habitants entre deux valeurs", description = "Permet de récupérer les villes dont le nombre d'habitants est compris entre deux valeurs.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des villes récupérées avec succès.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Ville.class))),
            @ApiResponse(responseCode = "400", description = "Si les bornes du nombre d'habitants sont mal formatées.", content = @Content)
    })
    @GetMapping("/rechercheParHabitantsEntre")
    public List<Ville> readVilleNbHabitantsEntre(@RequestParam Integer nbHabitantsMin, @RequestParam Integer nbHabitantsMax) throws CustomException {
        return villeService.extraireVilleNbHabitantsEntre(nbHabitantsMin, nbHabitantsMax);
    }

    @Operation(summary = "Recherche de villes par département et nombre minimum d'habitants", description = "Permet de récupérer les villes d'un département avec un nombre d'habitants supérieur à un certain minimum.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des villes récupérées avec succès.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Ville.class))),
            @ApiResponse(responseCode = "400", description = "Si le département ou le nombre d'habitants est mal formaté.", content = @Content)
    })
    @GetMapping("/rechercheParDepartementsHabitantsMin")
    public List<Ville> readVilleDeDepartementAvecNbHabitantsMin(@RequestParam Integer departementId, @RequestParam Integer nbHabitantsMin) throws CustomException {
        return villeService.extraireVilleDepartementNbHabitantsMin(departementId, nbHabitantsMin);
    }

    @Operation(summary = "Recherche de villes par département et nombre d'habitants entre deux valeurs", description = "Permet de récupérer les villes d'un département avec un nombre d'habitants compris entre deux valeurs.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des villes récupérées avec succès.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Ville.class))),
            @ApiResponse(responseCode = "400", description = "Si le département ou les bornes du nombre d'habitants sont mal formatées.", content = @Content)
    })
    @GetMapping("/rechercheParDepartementNbHabitantsEntre")
    public List<Ville> readVilleDeDepartementAvecNbHabitantsEntreMinEtMax(@RequestParam Integer departementId, @RequestParam Integer nbHabitantsMin, @RequestParam Integer nbHabitantsMax) throws CustomException {
        return villeService.extraireVilleDepartementNbHabitantsEntre(departementId, nbHabitantsMin, nbHabitantsMax);
    }

    @Operation(summary = "Récupérer les N villes les plus peuplées d'un département", description = "Permet de récupérer les N villes les plus peuplées d'un département.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des villes les plus peuplées récupérée avec succès.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Ville.class))),
            @ApiResponse(responseCode = "400", description = "Si le nombre N de villes est mal formaté.", content = @Content)
    })
    @GetMapping("/recherchePlusPeupleeParDepartement")
    public List<Ville> readNVillePlusPeupleDeDepartement(@RequestParam Integer departementId, @RequestParam int n) throws CustomException {
        return villeService.extraireNVillePlusPeupleeDepartement(departementId, n);
    }
}
