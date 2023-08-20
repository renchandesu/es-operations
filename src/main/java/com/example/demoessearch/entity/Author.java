package com.example.demoessearch.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.InnerField;
import org.springframework.data.elasticsearch.annotations.MultiField;

@Data
@Accessors(chain = true)
public class Author {

    @Field(type = FieldType.Integer)
    private Integer id;

    @MultiField(mainField = @Field(type = FieldType.Text),otherFields = @InnerField(suffix = "keyword",type = FieldType.Keyword))
    private String name;

}
