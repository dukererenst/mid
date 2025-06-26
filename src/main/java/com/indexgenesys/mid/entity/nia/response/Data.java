/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.indexgenesys.mid.entity.nia.response;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author ernest
 */

public class Data implements Serializable{
   public String transactionGuid;
    public String shortGuid;
    public String tellerId;
    public Date requestTimestamp;
    public Date responseTimestamp;
    public String verified;
    public boolean isException;
    public String source;
    public Person person; 

    public Data() {
    }

    public String getTransactionGuid() {
        return transactionGuid;
    }

    public void setTransactionGuid(String transactionGuid) {
        this.transactionGuid = transactionGuid;
    }

    public String getShortGuid() {
        return shortGuid;
    }

    public void setShortGuid(String shortGuid) {
        this.shortGuid = shortGuid;
    }

    public Date getRequestTimestamp() {
        return requestTimestamp;
    }

    public void setRequestTimestamp(Date requestTimestamp) {
        this.requestTimestamp = requestTimestamp;
    }

    public Date getResponseTimestamp() {
        return responseTimestamp;
    }

    public void setResponseTimestamp(Date responseTimestamp) {
        this.responseTimestamp = responseTimestamp;
    }

    public String getVerified() {
        return verified;
    }

    public void setVerified(String verified) {
        this.verified = verified;
    }

    public boolean isIsException() {
        return isException;
    }

    public void setIsException(boolean isException) {
        this.isException = isException;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
    public String getTellerId() {
        return source;
    }

    public void setTellerId(String tellerId) {
        this.tellerId = tellerId;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
    
    
    
}
