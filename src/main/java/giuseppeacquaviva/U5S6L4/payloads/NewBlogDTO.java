package giuseppeacquaviva.U5S6L4.payloads;

import jakarta.validation.constraints.NotNull;

public record NewBlogDTO(
        @NotNull(message = "L'id dell'autore è obbligatorio")
        Integer authorId,
        @NotNull(message = "La categoria è obbligatoria")
        String categoria,
        String content,
        @NotNull(message = "Il tempo di lettura è obbligatorio")
        double tempoDiLettura,
        @NotNull(message = "Il titolo è obbligatorio")
        String titolo
) {
}
