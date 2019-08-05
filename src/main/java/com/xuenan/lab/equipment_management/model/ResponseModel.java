package com.xuenan.lab.equipment_management.model;

/**
 * @author Howie Lu
 * @version Updated at 2019/08/01
 * @description
 */
public class ResponseModel {
    private Integer error ;
    private String message ;
    private Object data ;

    public ResponseModel(){
        this.error = 0 ;
        this.message = "success" ;
        this.data = null ;
    }

    public ResponseModel( Integer error , String message ){
        this.message = message ;
        this.error = error ;
    }

    public Integer getError() {
        return error;
    }

    public void setError(Integer error) {
        this.error = error;
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
