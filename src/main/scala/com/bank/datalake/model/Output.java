package com.bank.datalake.model;

import org.apache.solr.client.solrj.beans.Field;

public class Output {

    @Field
    private String id;

    @Field
    private String outputType;

    @Field
    private String outputName;

    @Field
    private String address;

    public Output() {

    }

    public Output(String outputType, String outputName, String address) {
        this.outputType = outputType;
        this.outputName = outputName;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOutputType() {
        return outputType;
    }

    public void setOutputType(String outputType) {
        this.outputType = outputType;
    }

    public String getOutputName() {
        return outputName;
    }

    public void setOutputName(String outputName) {
        this.outputName = outputName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
