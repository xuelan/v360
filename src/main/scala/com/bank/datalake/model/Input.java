package com.bank.datalake.model;

import org.apache.solr.client.solrj.beans.Field;

public class Input {

    @Field
    private String id;

    @Field
    private String inputType;

    @Field
    private String inputName;

    @Field
    private String address;

    public Input() {

    }

    public Input(String inputType, String inputName, String address) {
        this.inputType = inputType;
        this.inputName = inputName;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInputType() {
        return inputType;
    }

    public void setInputType(String inputType) {
        this.inputType = inputType;
    }

    public String getInputName() {
        return inputName;
    }

    public void setInputName(String inputName) {
        this.inputName = inputName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
