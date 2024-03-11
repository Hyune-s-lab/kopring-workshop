package com.example.kopringworkshop.exception.support.exception

class NotHandledBusinessException: BaseException {
    constructor(): super()
    constructor(message: String): super(message)
    constructor(message: String, cause: Throwable?): super(message, cause)
    constructor(cause: Throwable?): super(cause)
}
