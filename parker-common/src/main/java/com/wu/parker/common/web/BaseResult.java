package com.wu.parker.common.web;

import java.io.Serializable;

/**
 * @author: wusq
 * @date: 2018/12/5
 */
public class BaseResult implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer code = 200;

    private String message;

    private Object data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
