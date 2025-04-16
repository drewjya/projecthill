package com.drewjya.projecthill.model

import org.springframework.http.HttpStatus

/**
 * A generic wrapper class for API responses.
 *
 * @param T The type of the data payload included in the response.
 * @property status The status code of the response (e.g., HTTP status code).
 * @property message A descriptive message accompanying the response.
 * @property data The actual data payload of the response (nullable).
 */
data class ResultResponse<T>(
    val status: Number,
    val message: String,
    val data: Any? = null,
)

/**
 * Extension function to easily wrap any object into a ResultResponse.
 *
 * This allows you to call `.toResponse(...)` on any variable.
 *
 * @param T The type of the object being wrapped.
 * @param status The status code for the response. Defaults to 200 (OK).
 * @param message The message for the response. Defaults to "Success".
 * @return A ResultResponse instance containing the original object as data.
 */
fun <T> T?.toResponse(
    status: Int = HttpStatus.OK.value(),
    message: String,
): ResultResponse<T> =
    ResultResponse(
        status = status,
        message = message,
        data = this,
    )
