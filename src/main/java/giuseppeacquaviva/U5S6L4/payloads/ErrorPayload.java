package giuseppeacquaviva.U5S6L4.payloads;

import java.time.LocalDate;

public record ErrorPayload(String message, LocalDate time) {
}
