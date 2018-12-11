package com.example.springboot.common;

import lombok.Data;

/**
 * @author linghongkang
 * @description:
 * @create: 2018-12-07 11:21
 **/
@Data
public class IndexSchema {
    private String filedName;
    private String filedType;
    private String indexAnalyzer;
    private String queryAnalyzer;

    public IndexSchema(String filedName, String filedType, String indexAnalyzer, String queryAnalyzer) {
        this.filedName = filedName;
        this.filedType = filedType;
        this.indexAnalyzer = indexAnalyzer;
        this.queryAnalyzer = queryAnalyzer;
    }
}
