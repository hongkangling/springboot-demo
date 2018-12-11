package com.example.springboot.lucene.index;

import com.example.springboot.common.IndexSchema;
import com.example.springboot.lucene.client.HightLevelClient;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.admin.indices.alias.Alias;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.get.GetIndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author linghongkang
 * @description:
 * @create: 2018-12-06 16:14
 **/
@Slf4j
public class IndexBuilder {
    private static List<IndexSchema> list=new ArrayList<>(10);

    private static String indexName;


    /**
     * 创建索引
     *
     * @throws IOException
     */
    public  static void createIndexs() throws IOException {
        RestHighLevelClient client=HightLevelClient.getClient();
        indexName="demoes";
//        for (int i = 0; i <7 ; i++) {
            list.add(new IndexSchema("id","long","",""));
            list.add(new IndexSchema("name","text","ik_smart","ik_smart"));
//        }

        GetIndexRequest request = new GetIndexRequest();
        request.indices(indexName);

        boolean exists = client.indices().exists(request, RequestOptions.DEFAULT);
        if(exists){
            log.error("{} is  exist!",indexName);
            return;
        }
        CreateIndexRequest indexRequest = new CreateIndexRequest(indexName);
        indexRequest.settings(Settings.builder()
                .put("index.number_of_shards", 2)
                .put("index.number_of_replicas", 1));

        XContentBuilder builder = XContentFactory.jsonBuilder();
        builder.startObject();
        {
            builder.startObject("_doc");
            {
                builder.startObject("properties");
                {
                    for (int i = 0, len = list.size(); i < len; i++) {
                        IndexSchema indexSchema = list.get(i);
                        // 域字段设置 相当于数据库表的列名
                        builder.startObject(indexSchema.getFiledName());
                        {
                            //    定义属性域类型，建索引分词器，查询索引使用分词器。
                            builder.field("type", indexSchema.getFiledType());
                            if(indexSchema.getFiledType().equals("text")){
                                builder.field("analyzer", indexSchema.getIndexAnalyzer());
                                builder.field("search_analyzer", indexSchema.getQueryAnalyzer());
                            }

                        }
                        builder.endObject();
                    }

                }
                builder.endObject();
            }
            builder.endObject();
        }
        builder.endObject();
        indexRequest.mapping("_doc", builder);

        indexRequest.alias(new Alias(indexName+"_alias"));
        //所有节点创建成功的超时时间
        indexRequest.timeout(TimeValue.timeValueMinutes(2));
        // 主节点链接超时时间
        indexRequest.masterNodeTimeout(TimeValue.timeValueMinutes(1));

        CreateIndexResponse createIndexResponse = client.indices().create(indexRequest, RequestOptions.DEFAULT);

        boolean acknowledged = createIndexResponse.isAcknowledged();
    }

    public static void main(String[] args) {
        try {
            IndexBuilder.createIndexs();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
