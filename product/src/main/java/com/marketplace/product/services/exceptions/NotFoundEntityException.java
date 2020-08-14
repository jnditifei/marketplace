package com.marketplace.product.services.exceptions;

public class NotFoundEntityException extends Throwable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private final ErrorMsgDTO errorDto;

    /**
     * @param errorDto
     */
    public NotFoundEntityException(String error, String message, String localization) {
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
