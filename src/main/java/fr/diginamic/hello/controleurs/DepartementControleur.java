package fr.diginamic.hello.controleurs;

import java.util.List;
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
    public List<Departement> readDepartements(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return departementService.extraireDepartements(page, size);
    }

    @GetMapping("/code/{code}")
    public Departement readDepartementId(@PathVariable Integer code) {
        return departementService.extraireDepartementParCode(code);
    }

    @GetMapping("/nom/{nom}")
    public Departement readDepartementNom(@PathVariable String nom) {
        return departementService.extraireDepartementParNom(nom);
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
