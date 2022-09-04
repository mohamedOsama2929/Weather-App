package  com.ahoy.entities.base

/**
 * Default error model that comes from server if something goes wrong with a repository call
 */
data class ErrorModel(
    var message: String?,
    var errorCode: Int?,
    @Transient var errorStatus: ErrorStatus
) {
    constructor(errorStatus: ErrorStatus) : this(null, null, errorStatus)
    constructor(errorStatus: ErrorStatus, errorCode: Int) : this(null, errorCode, errorStatus)
}