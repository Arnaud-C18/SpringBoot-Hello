package fr.diginamic.hello.controleurs;

import fr.diginamic.hello.entites.Ville;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/villes")
public class VilleControleur {

    List <Ville> villes = new ArrayList<Ville>();

    @GetMapping
    public List<Ville> getVilles() {
//        villes.add(new Ville("Paris", 1_000_000));
//        villes.add(new Ville("Bordeaux", 500_000));
//        villes.add(new Ville("Tours", 200_000));
        return villes;
    }

    @PostMapping
    public ResponseEntity<String> setVille(@RequestBody Ville ville) {
        if (villes.contains(ville)) {
            return ResponseEntity.badRequest().body("La ville existe déjà");
        } else {
            villes.add(ville);
            return ResponseEntity.ok("Ville insérée avec succès");
        }
    }
}
