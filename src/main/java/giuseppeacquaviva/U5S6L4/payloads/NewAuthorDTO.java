package giuseppeacquaviva.U5S6L4.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record NewAuthorDTO(
        @NotEmpty(message = "Il nome è obbligatorio")
        @Size(min= 3, max = 20, message = "Il nome deve essere minimo di 3 caratteri, massimo 20")
        String nome,
        @NotEmpty(message = "Il cognome è obbligatorio")
        String cognome,
        @NotEmpty(message = "L'email è obbligatoria")
        String email,
        @NotNull(message = "La data di nascita è obbligatoria")
        String dataDiNascita
) {
}
