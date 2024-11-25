package fr.diginamic.hello.controleurs;

import fr.diginamic.hello.entites.Ville;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/villes")
public class VilleControleur {

    @GetMapping
    public List<Ville> getVilles() {
        List <Ville> villes = new ArrayList<Ville>();
        villes.add(new Ville("Paris", 1_000_000));
        villes.add(new Ville("Bordeaux", 500_000));
        villes.add(new Ville("Tours", 200_000));
        return villes;
    }
}
