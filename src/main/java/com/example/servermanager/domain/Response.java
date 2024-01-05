package com.example.servermanager.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

/**
 * This class is used to create a response object that can be returned to the client.
 * It contains information about the response such as the status code, status, reason, message, data and developer message.
 */
@Data
@SuperBuilder
@JsonInclude(NON_NULL) //  Excluding the message that is not sent (if devlMessage, message = null and vice versa)
public class Response {
    protected LocalDateTime timeStamp;
    protected int statusCode;
    protected HttpStatus status;
    protected String reason;
    protected String message;
    protected String devMessage;
    protected Map<?, ?> data;

}
