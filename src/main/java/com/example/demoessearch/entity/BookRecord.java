package com.example.demoessearch.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

import java.util.List;

@Data
@Accessors(chain = true)
@Document(indexName = "book_record",createIndex = false)
public class BookRecord {

    @Id
    private String id;

    @MultiField(mainField = @Field(type = FieldType.Text),otherFields = @InnerField(suffix = "keyword",type = FieldType.Keyword))
    private String title;

    @Field(type = FieldType.Keyword)
    private String people;

    @Field(type = FieldType.Double)
    private Double price;

    @Field(type = FieldType.Integer)
    private List<Integer> tags;

    @Field(type = FieldType.Integer)
    private Integer rank;

    @Field(type = FieldType.Date,format = DateFormat.epoch_millis)
    private Long timestamp;

    @Field(type = FieldType.Object)
    private Author author;

    @Field(type = FieldType.Text)
    private String description;

}
