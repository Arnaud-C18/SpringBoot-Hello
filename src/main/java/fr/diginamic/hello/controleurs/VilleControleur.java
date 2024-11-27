package fr.diginamic.hello.controleurs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.diginamic.hello.entites.Ville;
import fr.diginamic.hello.services.VilleService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/villes")
public class VilleControleur {
    @Autowired
    private VilleService villeService;


    @GetMapping
    public List<Ville> getVilles() {
        return villeService.getAllVilles();
    }

    @GetMapping("/{id}")
    public Ville getVille(@PathVariable Long id) {
        return villeService.findVilleById(id);
    }

    @PostMapping
    public ResponseEntity<String> createVille(@Valid @RequestBody Ville ville, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<String>("Problème de validation des contraintes !", HttpStatus.BAD_REQUEST);
        }
        if (villeService.addVille(ville)) {
            return new ResponseEntity<String>("Succès ! La ville a été créée", HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("La ville existe déjà !", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<String> updateVille(@Valid @RequestBody Ville ville, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<String>("Problème de validation des contraintes !", HttpStatus.BAD_REQUEST);
        }
        if (villeService.updateVille(ville)) {
            return new ResponseEntity<String>("Succès ! La ville a été mise à jour", HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("La mise à jour a échouée !", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVille(@PathVariable Long id) {
        if (villeService.deleteVille(id)) {
            return new ResponseEntity<String>("Succès !", HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("La suppression a échouée !", HttpStatus.BAD_REQUEST);
        }
    }
}