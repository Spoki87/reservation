package com.reservation.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
public class Response<T> {

    private final int status;
    private final String message;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime timestamp;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final T data;

    private Response(String message, T data, HttpStatus httpStatus){
        this.timestamp = LocalDateTime.now();
        this.message = message;
        this.data = data;
        this.status = httpStatus.value();
    }

    public static <T> Response<T> success(String message, T data, HttpStatus httpStatus){
        return new Response<>(message,data, HttpStatus.OK);
    }

    public static <T> Response<T> error(String message, HttpStatus status) {
        return new Response<>(message, null, status);
    }
}
