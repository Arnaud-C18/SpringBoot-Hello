package fr.diginamic.hello.controleurs;


import java.util.List;
import java.util.Optional;

import fr.diginamic.hello.entites.Ville;
import fr.diginamic.hello.repository.VilleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/villes")
public class VilleControleur {

    @Autowired
    private VilleRepository villeRepository;

    @GetMapping
    public Page<Ville> readVilles(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return villeRepository.findAll(pageable);
    }

    @GetMapping("/id/{id}")
    public Optional<Ville> readVilleId(@PathVariable Integer id) {
        return villeRepository.findById(id);
    }

    @GetMapping("/nom/{nom}")
    public Ville readVilleNom(@PathVariable String nom) {
        return villeRepository.findByNom(nom);
    }

    @PostMapping
    public Ville createVille(@RequestBody Ville ville) {
        return villeRepository.save(ville);
    }

    @PutMapping("/{id}")
    public Ville updateVille(@PathVariable Integer id, @RequestBody Ville nouvelleVille) {
        if (villeRepository.existsById(id)) {
            nouvelleVille.setId(id);
            return villeRepository.save(nouvelleVille);
        } else {
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public void deleteVille(@PathVariable Integer id) {
        villeRepository.deleteById(id);
    }

    @GetMapping("/rechercheParNom")
    public List<Ville> readVilleChaine(@RequestParam String chaine) {
        return villeRepository.findByNomStartingWith(chaine);
    }

    @GetMapping("/rechercheParHabitantsMin")
    public List<Ville> readVilleNbHabitantsMin(@RequestParam Integer nbHabitants ) {
        return villeRepository.findByNbHabitantsGreaterThan(nbHabitants);
    }

    @GetMapping("/rechercheParHabitantsEntre")
    public List<Ville> readVilleNbHabitantsEntre(@RequestParam Integer nbHabitantsMin, @RequestParam Integer nbHabitantsMax) {
        return villeRepository.findByNbHabitantsBetween(nbHabitantsMin, nbHabitantsMax);
    }

    @GetMapping("/rechercheParDepartementsHabitantsMin")
    public List<Ville> readVilleDeDepartementAvecNbHabitantsMin(@RequestParam Integer departementId, @RequestParam Integer nbHabitants) {
        return villeRepository.findByDepartementIdAndNbHabitantsIsGreaterThan(departementId, nbHabitants);
    }

    @GetMapping("/rechercheParDepartementNbHabitantsEntre")
    public List<Ville> readVilleDeDepartementAvecNbHabitantsEntreMinEtMax(@RequestParam Integer departementId, @RequestParam Integer nbHabitantsMin, @RequestParam Integer nbHabitantsMax) {
        return villeRepository.findByDepartementIdAndNbHabitantsBetween(departementId, nbHabitantsMin, nbHabitantsMax );
    }

    @GetMapping("/recherchePlusPeupleeParDepartement")
    public List<Ville> readNVillePlusPeupleDeDepartement(@RequestParam Integer departementId, @RequestParam Pageable pageable) {
        return villeRepository.findByDepartementIdOrderByNbHabitantsDesc(departementId, pageable);
    }
}