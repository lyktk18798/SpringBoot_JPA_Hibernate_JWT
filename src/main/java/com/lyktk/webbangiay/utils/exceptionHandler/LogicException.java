package com.lyktk.webbangiay.utils.exceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogicException extends RuntimeException {

    HttpStatus status;
    String message;
}
