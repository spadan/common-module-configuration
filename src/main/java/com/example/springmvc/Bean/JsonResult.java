package com.example.springmvc.Bean;

/**
 * json返回实体
 *
 * @author xiongLiang
 * @date 2018/7/29 11:25
 */
public class JsonResult<T> {
    private Integer code;
    private String errorMessage;
    private T Data;

    private JsonResult(Builder<T> builder) {
        setCode(builder.code);
        setErrorMessage(builder.errorMessage);
        setData(builder.Data);
    }

    public static <B> Builder<B> newBuilder() {
        return new Builder<>();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public T getData() {
        return Data;
    }

    public void setData(T data) {
        Data = data;
    }

    public static final class Builder<T> {
        private Integer code;
        private String errorMessage;
        private T Data;

        private Builder() {
        }

        public Builder<T> code(Integer val) {
            code = val;
            return this;
        }

        public Builder<T> errorMessage(String val) {
            errorMessage = val;
            return this;
        }

        public Builder<T> Data(T val) {
            Data = val;
            return this;
        }

        public JsonResult<T> build() {
            return new JsonResult<>(this);
        }
    }
}
