package br.com.creditas.simulation.exception

import br.com.creditas.simulation.dto.ErrorView
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionHandler {
    @ExceptionHandler(InternalServerErrorException::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleInternalServerError(
        exception: Exception,
        req: HttpServletRequest
    ): ResponseEntity<ErrorView> {
        val errorView = ErrorView(
            status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            code = HttpStatus.INTERNAL_SERVER_ERROR.name,
            message = exception.message,
            path = req.servletPath,
        )
        return ResponseEntity.status(errorView.status).body(errorView)
    }

    @ExceptionHandler(BadRequestException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleValidationError(
        exception: Exception,
        req: HttpServletRequest
    ): ResponseEntity<ErrorView> {
        val errorView = ErrorView(
            status = HttpStatus.BAD_REQUEST.value(),
            code = HttpStatus.BAD_REQUEST.name,
            message = exception.message,
            path = req.servletPath,
        )
        return ResponseEntity.status(errorView.status).body(errorView)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleValidationError(
        exception: MethodArgumentNotValidException,
        req: HttpServletRequest
    ): ResponseEntity<ErrorView> {
        val errorMessage = HashMap<String, String?>()
        exception.bindingResult.fieldErrors.forEach { e ->
            errorMessage[e.field] = e.defaultMessage
        }

        val errorView = ErrorView(
            status = HttpStatus.BAD_REQUEST.value(),
            code = HttpStatus.BAD_REQUEST.name,
            message = errorMessage.toString(),
            path = req.servletPath,
        )

        return ResponseEntity.status(errorView.status).body(errorView)
    }
}
