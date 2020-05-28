package com.deepexi.ai.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;
import java.io.Serializable;

/**
 * @author Administrator
 */
public class ApplicationException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = -7110316208734638051L;

    protected Logger logger = LoggerFactory.getLogger(this.getClass());


    public ApplicationException() {
        super();
    }

    public ApplicationException(Response.Status status, String msg) {
        super(status.getStatusCode() + "@" + msg);
    }

    public ApplicationException(String msg) {
        super(msg);
    }

    public ApplicationException(Response.Status status, String msg, Exception e) {
        super(status.getStatusCode() + "@" + msg, e);
    }
}
