package fr.diginamic.hello.controleurs;

import fr.diginamic.hello.entites.Ville;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/villes")
public class VilleControleur {

    List<Ville> villes = new ArrayList<Ville>();

    @GetMapping
    public List<Ville> readVilles() {
        return villes;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> readVille(@PathVariable int id) {
        for (Ville ville : villes) {
            if (ville.getId() == id) {
                return ResponseEntity.ok(ville);
            }
        }
        return ResponseEntity.badRequest().body("Aucune ville avec l'id " + id);
    }

    @PostMapping
    public ResponseEntity<String> createVille(@RequestBody Ville ville) {
        for (Ville v : villes) {
            if (v.getId() == ville.getId()) {
                return ResponseEntity.badRequest().body("Une ville avec l'id " + ville.getId() + " existe déjà");
            }
        }
        villes.add(ville);
        return ResponseEntity.ok("Ville insérée avec succès");
    }

    @PutMapping
    public ResponseEntity<String> updateVille(@RequestBody Ville nouvelleVille) {
        for (Ville ville : villes) {
            if (ville.getId() == nouvelleVille.getId()) {
                ville.setNom(nouvelleVille.getNom());
                ville.setNbHabitants(nouvelleVille.getNbHabitants());
                return ResponseEntity.ok("Ville modifiée");
            }
        }
        return ResponseEntity.badRequest().body("Aucune ville avec l'id " + nouvelleVille.getId());
    }

    @DeleteMapping
    public ResponseEntity<String> deleteVille(@RequestBody Ville villeASupprimer) {
        for (Ville ville : villes) {
            if (ville.getId() == ville.getId()) {
                villes.remove(ville);
                return ResponseEntity.ok("Ville supprimée");
            }
        }
        return ResponseEntity.badRequest().body("Aucune ville avec l'id " + villeASupprimer.getId());
    }

}
