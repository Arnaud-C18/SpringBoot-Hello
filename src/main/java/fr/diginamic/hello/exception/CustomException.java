package fr.diginamic.hello.exception;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Représente une exception personnalisée pour gérer les erreurs métier spécifiques.")
public class CustomException extends Exception {

    @Schema(description = "Message d'erreur détaillant la cause de l'exception", example = "La ville n'existe pas.")
    private final String message;

    public CustomException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
