package pe.Examen.BackEnd_Klauz.config;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pe.Examen.BackEnd_Klauz.exception.BadRequestException;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RestControllerAdvice
@AllArgsConstructor
public class RestExceptionHandler {
  private MessageSource messageSource;

  @ExceptionHandler(MethodArgumentNotValidException.class)
  ProblemDetail handleValidationError(MethodArgumentNotValidException exception) {
    ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
    problemDetail.setTitle("Entidad con errores");
    problemDetail.setDetail("La entidad no puede procesarse porque tiene errores.");
    List<FieldError> fieldErrors = exception.getFieldErrors();
    List<String> errors = new ArrayList<>();
    for (FieldError fe : fieldErrors) {
      String message = messageSource.getMessage(fe, Locale.getDefault());
      errors.add(message);
    }
    problemDetail.setProperty("errores", errors);
    return problemDetail;
  }

  @ExceptionHandler(BadRequestException.class)
  ProblemDetail handleBadRequestException(BadRequestException exception) {
    ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
    problemDetail.setTitle("Entidad con errores");
    problemDetail.setDetail(exception.getMessage());
    return problemDetail;
  }

  @ExceptionHandler(EntityNotFoundException.class)
  ProblemDetail handleEntityNotFoundException(EntityNotFoundException exception) {
    ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
    problemDetail.setTitle("No encontrado");
    problemDetail.setDetail(exception.getMessage());
    return problemDetail;
  }
  }
