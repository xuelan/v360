package com.bank.datalake.model;

import org.apache.solr.client.solrj.beans.Field;
import java.util.ArrayList;

import java.util.List;

public class Config {

    @Field
    private String id;

    @Field
    private String name;

    @Field
    private String typeEntity;

    @Field(child = true)
    private List<Input> inputs = new ArrayList<Input>();

    @Field(child = true)
    private List<Output> outputs = new ArrayList<Output>();

    public Config() {
    }

    public Config(String id, String name, String typeEntity, List<Input> inputs, List<Output> outputs) {
        this.id = id;
        this.name = name;
        this.typeEntity = typeEntity;
        this.inputs = inputs;
        this.outputs = outputs;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeEntity() {
        return typeEntity;
    }

    public void setTypeEntity(String typeEntity) {
        this.typeEntity = typeEntity;
    }

    public List<Input> getInputs() {
        return inputs;
    }

    public void setInputs(List<Input> inputs) {
        this.inputs = inputs;
    }

    public List<Output> getOutputs() {
        return outputs;
    }

    public void setOutputs(List<Output> outputs) {
        this.outputs = outputs;
    }
}
