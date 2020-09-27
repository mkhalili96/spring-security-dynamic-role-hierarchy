package net.mkhalili96.dynamic_role_hierarchy.common.exception.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ErrorDto implements Serializable {
    private Date timestamp = new Date();

    private int status;

    private ErrorMap error;

    private Object message;


    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ErrorMap getError() {
        return error;
    }

    public void setError(ErrorMap error) {
        this.error = error;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }


    public ErrorDto() {
    }

    public ErrorDto(Date timestamp, int status, ErrorMap error, Object message) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
    }
}
