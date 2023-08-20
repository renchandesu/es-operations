package com.example.demoessearch.service;

import com.example.demoessearch.entity.Author;
import com.example.demoessearch.entity.BookRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.IndexedObjectInformation;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class ElasticService {

    public static final String INDEX_NAME = "book_record";

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    public void insertData(){

        BookRecord bookRecord = new BookRecord().setRank(0).setPrice(32.0).setTimestamp(System.currentTimeMillis()).setDescription("123")
                .setTags(Arrays.asList(1,2)).setAuthor(new Author().setId(1).setName("1234")).setPeople("1211").setTitle("test");
        IndexQuery build = new IndexQueryBuilder().withIndex("").withObject(bookRecord).build();
        System.out.println(elasticsearchTemplate.index(build, IndexCoordinates.of("book_record")));



    }

    public void insertBatch(){
        Random random = new Random();

        List<IndexQuery> queries = new ArrayList<>();
        List<Integer> tags = Arrays.asList(1,2,3,4,5);

        for (int i = 0; i < 10000; i++) {

            List<Integer> parts = new ArrayList<>();
            int one = random.nextInt(3);
            parts.add(tags.get(one));
            int two = random.nextInt(3)+2;
            if (one != two){
                parts.add(tags.get(two));
            }

            Author author = new Author().setId((i % 3) + 1).setName("author_" + ((i % 3) + 1));
            BookRecord bookRecord = new BookRecord().setRank((i % 4) + 1)
                    .setTitle("book_" + (i % 4) + "_" + i)
                    .setAuthor(author)
                    .setPrice((double) (30 + random.nextInt(30)))
                    .setTimestamp(System.currentTimeMillis())
                    .setTags(parts)
                    .setPeople("reader"+((i+1)%100+1));
            IndexQueryBuilder indexQueryBuilder = new IndexQueryBuilder().withObject(bookRecord).withIndex(INDEX_NAME);
            queries.add(indexQueryBuilder.build());
            if (i%500 == 0){
                List<IndexedObjectInformation> indexedObjectInformations = elasticsearchTemplate.bulkIndex(queries, IndexCoordinates.of(INDEX_NAME));
                queries.clear();
            }
        }
        if (queries.size()!=0){
            elasticsearchTemplate.bulkIndex(queries,IndexCoordinates.of(INDEX_NAME));
            queries.clear();
        }

    }

}
