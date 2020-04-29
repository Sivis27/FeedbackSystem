package com.fms.fileupload.api;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", 
date = "2020-04-25T05:05:49.607+13:25")

public class ApiException extends Exception{
    private int code;
    public ApiException (int code, String msg) {
        super(msg);
        this.code = code;
    }
}
