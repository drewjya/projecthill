package com.drewjya.projecthill.exception

import com.drewjya.projecthill.model.ErrorResponse
import com.drewjya.projecthill.model.ResultResponse
import com.drewjya.projecthill.model.toResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ControllerExceptionHandler {
    @ExceptionHandler
    fun handleGeneralException(ex: Exception): ResponseEntity<ResultResponse<ErrorResponse>> {
        // Create the error response
        val errorResponse =
            ErrorResponse(
                code = "UNHANDLED_EXCEPTION",
                message = ex.message ?: ex.stackTraceToString(),
            )

        // Return the response entity
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
            errorResponse.toResponse(
                status = 500,
                message = "Internal Server Error",
            ),
        )
    }

    @ExceptionHandler
    fun handleNotFoundException(ex: DefaultNotFoundException): ResponseEntity<ResultResponse<ErrorResponse>> {
        val errorData =
            ErrorResponse(
                code =
                    "NOT_FOUND_EXCEPTION",
                message = ex.message ?: ex.stackTraceToString(),
            ).toResponse(status = 404, message = "Not Found")

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorData)
    }
}
