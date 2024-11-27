package fr.diginamic.hello.controleurs;

import java.util.List;
import fr.diginamic.hello.entites.Ville;
import fr.diginamic.hello.entites.Departement;
import fr.diginamic.hello.services.DepartementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/departements")
public class DepartementControleur {
    @Autowired
    private DepartementService departementService;

    @GetMapping
    public List<Departement> readDepartements() {
        return departementService.extraireDepartements();
    }

    @GetMapping("/code/{code}")
    public Departement readDepartementId(@PathVariable Integer code) {
        return departementService.extraireDepartementParCode(code);
    }

    @GetMapping("/nom/{nom}")
    public Departement readDepartementNom(@PathVariable String nom) {
        return departementService.extraireDepartementParNom(nom);
    }

    @GetMapping("/code/{code}/VillesPlusPeuple")
    public List<Ville> readNVillesPlusPeuple(@PathVariable Integer code, @RequestParam Integer n) {
        return departementService.nPlusGrandeVilles(code, n);
    }

    @GetMapping("/code/{code}/VillesPopEntreMinEtMax")
    public List<Ville> readVillesPopEtreMinEtMax(@PathVariable Integer code, @RequestParam Long min, @RequestParam Long max) {
        return departementService.villesPopEntreMinEtMax(code, min, max);
    }

    @PostMapping
    public void createDepartement(@RequestBody Departement departement) {
        departementService.insererDepartement(departement);
    }

    @PutMapping("/{code}")
    public void updateDepartement(@PathVariable Integer code, @RequestBody Departement nouvelleDepartement) {
        departementService.modifierDepartement(code, nouvelleDepartement);
    }

    @DeleteMapping("/{code}")
    public void deleteDepartement(@PathVariable Integer code) {
        departementService.supprimerDepartement(code);
    }
}
