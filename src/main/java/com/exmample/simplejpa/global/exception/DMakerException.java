package com.exmample.simplejpa.global.exception;

import com.exmample.simplejpa.global.code.DMakerErrorCode;
import lombok.Getter;


@Getter
public class DMakerException extends RuntimeException {
    private DMakerErrorCode dMakerErrorCode;
    private String detailMessage;

    public DMakerException(DMakerErrorCode dMakerErrorCode) {
        super(dMakerErrorCode.getMessage());
        this.dMakerErrorCode = dMakerErrorCode;
        this.detailMessage = dMakerErrorCode.getMessage();
    }

    public DMakerException(DMakerErrorCode dMakerErrorCode,
                           String detailMessage) {
        super(detailMessage);
        this.dMakerErrorCode = dMakerErrorCode;
        this.detailMessage = detailMessage;
    }
}