package spectra.attic.migration.elastic;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.ClearScrollRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchScrollRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;

// ctalk
//attic_ctalk_ticket, attic_ctalk_search_talk, attic_ctalk_active_talk
//attic_ctalk_partner

// messaging
//attic_messaging_group_channel, attic_messaging_message, attic_messaging_user

// search !?
//attic_search_aggregation ???
public class ElasticDump {

    public static void main(String[] args) {
        ElasticDump elasticDump = new ElasticDump();
        try {
            elasticDump.dump();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void dump() throws IOException {
        RestHighLevelClient client = new RestHighLevelClient(
            RestClient.builder(
                new HttpHost("172.16.120.202", 9200, "http")));

        // 1. 특정 인덱스에서 데이터 가져오기
        SearchRequest searchRequest = new SearchRequest("attic_ctalk_ticket");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchRequest.source(searchSourceBuilder);
        searchRequest.scroll(TimeValue.timeValueMinutes(1L)); // 스크롤 유지 시간 설정

        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        String scrollId = searchResponse.getScrollId();
        SearchHit[] searchHits = searchResponse.getHits().getHits();

        // 2. 가져온 데이터를 다른 인덱스로 밀어넣기
        RestHighLevelClient localClient = new RestHighLevelClient(
            RestClient.builder(
                new HttpHost("localhost", 9200, "http")));

        while (searchHits != null && searchHits.length > 0) {
            for (SearchHit hit : searchHits) {
                System.out.println(hit.getSourceAsString());

                //IndexRequest indexRequest = new IndexRequest("target_index_name");
                //indexRequest.source(hit.getSourceAsString(), XContentType.JSON);
                //localClient.index(indexRequest, RequestOptions.DEFAULT);
            }

            SearchScrollRequest scrollRequest = new SearchScrollRequest(scrollId);
            scrollRequest.scroll(TimeValue.timeValueMinutes(1L));
            searchResponse = client.scroll(scrollRequest, RequestOptions.DEFAULT);

            scrollId = searchResponse.getScrollId();
            searchHits = searchResponse.getHits().getHits();
        }

        ClearScrollRequest clearScrollRequest = new ClearScrollRequest();
        clearScrollRequest.addScrollId(scrollId);
        client.clearScroll(clearScrollRequest, RequestOptions.DEFAULT);

        client.close();
    }
}
