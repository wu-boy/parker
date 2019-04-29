package com.wu.parker.solr.test;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

/**
 * @author wusq
 * @date 2019/4/28
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SolrTest {

    @Autowired
    private SolrClient client;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testAdd() throws Exception {
        //List<SolrInputDocument> docs = new ArrayList<>();
        SolrInputDocument doc = new SolrInputDocument();
        doc.addField("id", UUID.randomUUID().toString());
        doc.addField("title", "这是标题");
        doc.addField("content", "这是一个项目");
        //docs.add(doc);
        UpdateResponse updateResponse = client.add(doc);
        client.commit();
    }

    @Test
    public void testQuery() throws Exception {
        SolrQuery query = new SolrQuery();

        // 根据标题或内容来搜索
        query.setQuery("title:项目 or content:项目");

        // 返回的字段
        query.addField("id");
        query.addField("title");
        query.addField("content");

        QueryResponse response = client.query(query);
        SolrDocumentList documents = response.getResults();
        System.out.println("结果集大小=" + documents.size());
        for(SolrDocument document : documents) {
            final String title = (String) document.getFirstValue("title");
            System.out.println(title);
            //final String name = (String) document.getFirstValue("name");
            //System.out.println("id: " + id + "; name: " + name);
        }
    }

}
