package com.maersk.movieservice.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.FieldError;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionDetail {

    private String errorCode;
    private String message;
    private String attributeName;
    private String value;

    public ExceptionDetail(String errorCode, FieldError fieldError) {
        this.errorCode = errorCode;
        this.message = fieldError.getDefaultMessage();
        this.attributeName = fieldError.getField();
        String rejectValue = fieldError.getRejectedValue().toString();
        this.value = StringUtils.isBlank(rejectValue) ? null : rejectValue;
    }

    @Override
    public String toString() {
        return "ExceptionDetail{" +
                "errorCode='" + errorCode + '\'' +
                ", message='" + message + '\'' +
                ", attributeName='" + attributeName + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
