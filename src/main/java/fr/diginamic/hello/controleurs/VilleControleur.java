package fr.diginamic.hello.controleurs;

import java.util.List;
import fr.diginamic.hello.entites.Ville;
import fr.diginamic.hello.services.VilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/villes")
public class VilleControleur {
    @Autowired
    private VilleService villeService;

    @GetMapping
    public List<Ville> readVilles() {
        return villeService.extraireVilles();
    }

    @GetMapping("/id/{id}")
    public Ville readVilleId(@PathVariable Long id) {
        return villeService.extraireVilleParId(id);
    }

    @GetMapping("/nom/{nom}")
    public Ville readVilleNom(@PathVariable String nom) {
        return villeService.extraireVilleParNom(nom);
    }

    @PostMapping
    public void createVille(@RequestBody Ville ville) {
        villeService.insererVille(ville);
    }

    @PutMapping("/{id}")
    public void updateVille(@PathVariable Long id, @RequestBody Ville nouvelleVille) {
        villeService.modifierVille(id, nouvelleVille);
    }

    @DeleteMapping("/{id}")
    public void deleteVille(@PathVariable Long id) {
        villeService.supprimerVille(id);
    }
}