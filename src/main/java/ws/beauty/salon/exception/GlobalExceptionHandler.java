package ws.beauty.salon.exception;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleSecurityException(Exception exception) {

        ProblemDetail errorDetail = null;
        exception.printStackTrace();

        if (exception instanceof BadCredentialsException) {
            errorDetail = ProblemDetail.forStatusAndDetail(
                    HttpStatusCode.valueOf(401),
                    exception.getMessage()
            );
            errorDetail.setProperty("description", "El nombre de usuario o la contraseña son incorrectos");
            return errorDetail;
        }

        if (exception instanceof AccountStatusException) {
            errorDetail = ProblemDetail.forStatusAndDetail(
                    HttpStatusCode.valueOf(403),
                    exception.getMessage()
            );
            errorDetail.setProperty("description", "La cuenta está bloqueada o no está activa");
            return errorDetail;
        }

        if (exception instanceof AccessDeniedException) {
            errorDetail = ProblemDetail.forStatusAndDetail(
                    HttpStatusCode.valueOf(403),
                    exception.getMessage()
            );
            errorDetail.setProperty("description", "No estás autorizado a acceder a este recurso");
            return errorDetail;
        }

        if (exception instanceof SignatureException) {
            errorDetail = ProblemDetail.forStatusAndDetail(
                    HttpStatusCode.valueOf(401),
                    exception.getMessage()
            );
            errorDetail.setProperty("description", "La firma del token JWT no es válida");
            return errorDetail;
        }

        if (exception instanceof ExpiredJwtException) {
            errorDetail = ProblemDetail.forStatusAndDetail(
                    HttpStatusCode.valueOf(401),
                    exception.getMessage()
            );
            errorDetail.setProperty("description", "El token JWT ha expirado");
            return errorDetail;
        }
        if (errorDetail == null) {
            errorDetail = ProblemDetail.forStatusAndDetail(
                    HttpStatusCode.valueOf(500),
                    exception.getMessage()
            );
            errorDetail.setProperty("description", "Error interno desconocido del servidor");
        }

        return errorDetail;
    }
}
