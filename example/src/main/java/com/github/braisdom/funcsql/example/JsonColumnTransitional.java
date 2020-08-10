package com.github.braisdom.funcsql.example;

import com.github.braisdom.funcsql.transition.ColumnTransitional;
import com.github.braisdom.funcsql.DomainModelDescriptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.sql.DatabaseMetaData;
import java.sql.ResultSetMetaData;

public class JsonColumnTransitional implements ColumnTransitional {

    private Gson gson = new GsonBuilder().create();

    @Override
    public Object sinking(DatabaseMetaData databaseMetaData, Object object,
                          DomainModelDescriptor domainModelDescriptor, String fieldName, Object fieldValue) {
        if(fieldValue != null)
            return gson.toJson(fieldValue);
        return null;
    }

    @Override
    public Object rising(DatabaseMetaData databaseMetaData, ResultSetMetaData resultSetMetaData,
                         Object object, DomainModelDescriptor domainModelDescriptor, String fieldName, Object fieldValue) {
        if(fieldValue != null)
            return gson.fromJson(String.valueOf(fieldValue), domainModelDescriptor.getFieldType(fieldName));
        return null;
    }
}