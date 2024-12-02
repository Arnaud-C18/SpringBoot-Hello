package fr.diginamic.hello.controleurs;

import java.util.List;
import fr.diginamic.hello.entites.Ville;
import fr.diginamic.hello.exception.CustomException;
import fr.diginamic.hello.repository.VilleRepository;
import fr.diginamic.hello.services.VilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/villes")
public class VilleControleur {

    @Autowired
    private VilleService villeService;

    @PostMapping
    public Ville createVille(@RequestBody Ville ville) throws CustomException {
        return villeService.insererVille(ville);
    }

    @PutMapping("/{id}")
    public Ville updateVille(@PathVariable Integer id, @RequestBody Ville nouvelleVille) throws CustomException {
        return villeService.modifierVille(id, nouvelleVille);
    }

    @DeleteMapping("/{id}")
    public void deleteVille(@PathVariable Integer id) {
        villeService.supprimerVille(id);
    }
    @GetMapping
    public List<Ville> readVilles(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return villeService.extraireVilles(page, size);
    }

    @GetMapping("/id/{id}")
    public Ville readVilleId(@PathVariable Integer id) {
        return villeService.extraireVilleParId(id);
    }

    @GetMapping("/nom/{nom}")
    public Ville readVilleNom(@PathVariable String nom) {
        return villeService.extraireVilleParNom(nom);
    }

    @GetMapping("/rechercheParNom")
    public List<Ville> readVilleChaine(@RequestParam String chaine) throws CustomException {
        return villeService.extraireVilleParChaine(chaine);
    }

    @GetMapping("/rechercheParHabitantsMin")
    public List<Ville> readVilleNbHabitantsMin(@RequestParam Integer nbHabitantsMin ) throws CustomException {
        return villeService.extraireVilleNbHabitantsMin(nbHabitantsMin);
    }

    @GetMapping("/rechercheParHabitantsEntre")
    public List<Ville> readVilleNbHabitantsEntre(@RequestParam Integer nbHabitantsMin, @RequestParam Integer nbHabitantsMax) throws CustomException {
        return villeService.extraireVilleNbHabitantsEntre(nbHabitantsMin, nbHabitantsMax);
    }

    @GetMapping("/rechercheParDepartementsHabitantsMin")
    public List<Ville> readVilleDeDepartementAvecNbHabitantsMin(@RequestParam Integer departementId, @RequestParam Integer nbHabitantsMin) throws CustomException {
        return villeService.extraireVilleDepartementNbHabitantsMin(departementId, nbHabitantsMin);
    }

    @GetMapping("/rechercheParDepartementNbHabitantsEntre")
    public List<Ville> readVilleDeDepartementAvecNbHabitantsEntreMinEtMax(@RequestParam Integer departementId, @RequestParam Integer nbHabitantsMin, @RequestParam Integer nbHabitantsMax) throws CustomException {
        return villeService.extraireVilleDepartementNbHabitantsEntre(departementId, nbHabitantsMin, nbHabitantsMax);
    }

    @GetMapping("/recherchePlusPeupleeParDepartement")
    public List<Ville> readNVillePlusPeupleDeDepartement(@RequestParam Integer departementId, @RequestParam int n) throws CustomException {
        return villeService.extraireNVillePlusPeupleeDepartement(departementId, n);
    }
}