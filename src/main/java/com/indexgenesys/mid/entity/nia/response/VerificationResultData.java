/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.indexgenesys.mid.entity.nia.response;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author ernest
 */

public class VerificationResultData implements Serializable{
   public Data data;
    public boolean success;
    public String code;
    public Object msg; 

    public VerificationResultData() {
    }

    
    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }
    
    
    
}
