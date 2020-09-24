package com.marketplace.order.services.exceptions;

public class InvalidEntityToPersistException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private final ErrorMsgDTO errorDto;

    /**
     * @param errorDto
     */
    public InvalidEntityToPersistException(String error, String message, String localization) {
        super();
        this.errorDto = new ErrorMsgDTO(error, message, localization);
    }

    /**
     * @return the errorDto
     */
    public ErrorMsgDTO getErrorDto() {
        return errorDto;
    }

}
