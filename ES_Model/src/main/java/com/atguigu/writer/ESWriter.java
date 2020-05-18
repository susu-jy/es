package com.atguigu.writer;

import com.atguigu.bean.Stu;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.Index;

import java.io.IOException;

public class ESWriter {
    public static void main(String[] args) throws IOException {
        //1.创建ES客户端连接池
        JestClientFactory factory = new JestClientFactory();

        //2.创建ES客户端连接地址
        HttpClientConfig httpClientConfig = new HttpClientConfig.Builder("http://hadoop102:9200").build();

        //3.设置ES连接地址
        factory.setHttpClientConfig(httpClientConfig);

        //4.获取ES客户端连接
        JestClient jestClient = factory.getObject();
//        Index index = new Index.Builder("{\n" +
//                "  \"id\":\"003\",\n" +
//                "  \"name\":赫敏\n" +
//                "}")
//                .index("stu_temp_01")
//                .type("_doc")
//                .id("003")
//                .build();
        Stu stu = new Stu("1007", "地瓜");
        Index index = new Index.Builder(stu)
                .index("stu_temp_02")
                .type("_doc")
                .id("1007")
                .build();
        jestClient.execute(index);

        jestClient.shutdownClient();

    }
}
