package giuseppeacquaviva.U5S6L4.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class ErrorsPayload {
    private String message;
    private LocalDateTime time;
}