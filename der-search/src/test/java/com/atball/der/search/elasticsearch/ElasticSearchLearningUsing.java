package com.atball.der.search.elasticsearch;


/**
 * 学习使用java操作ES
 * https://www.elastic.co/guide/en/elasticsearch/client/java-rest/7.14/java-rest-high-getting-started-request-options.html
 */
public class ElasticSearchLearningUsing {

/**
 *
 * Maven Configuration
 *
 *     <dependency>
 *         <groupId>org.elasticsearch.client</groupId>
 *         <artifactId>elasticsearch-rest-high-level-client</artifactId>
 *         <version>7.14.2</version>
 *     </dependency>
 *
 *
 *
 * Initialization 初始化
 *
 *     RestHighLevelClient client = new RestHighLevelClient(
 *             RestClient.builder(
 *                     new HttpHost("localhost", 9200, "http"),
 *                     new HttpHost("localhost", 9201, "http")));
 *
 *
 * 存储
 *
 * IndexRequest request = new IndexRequest("posts");
 * request.id("1");
 *      // String jsonString = "{" +
 *      //        "\"user\":\"kimchy\"," +
 *      //         "\"postDate\":\"2013-01-30\"," +
 *      //        "\"message\":\"trying out Elasticsearch\"" +
 *      //        "}";
 * User user = new User();
 * request.source(JSON.toJSONString(user), XContentType.JSON);
 * IndexResponse indexResponse = client.index(request, RequestOptions.DEFAULT);
 *
 *
 *  商品es索引设计
 *  PUT product
 *
 * {
 * 	"mappings": {
 * 		"properties": {
 * 			"skuId": {
 * 				"type": "long"
 *                        },
 * 			"spuId": {
 * 				"type": "keyword"
 *            },
 * 			"skuTitle": {
 * 				"type": "text",
 * 				"analyzer": "ik_smart"
 *            },
 * 			"skuPrice": {
 * 				"type": "keyword"
 *            },
 * 			"skuImg": {
 * 				"type": "keyword",
 * 				"index": false,
 * 				"doc_values": false
 *            },
 * 			"saleCount": {
 * 				"type": "long"
 *            },
 * 			"hasStock": {
 * 				"type": "boolean"
 *            },
 * 			"hotScore": {
 * 				"type": "long"
 *            },
 * 			"brandId": {
 * 				"type": "long"
 *            },
 * 			"catalogId": {
 * 				"type": "long"
 *            },
 * 			"brandName": {
 * 				"type": "keyword",
 * 				"index": false,
 * 				"doc_values": false                # 这个后面需要删除掉 不然不可以参与聚合
 *            },
 * 			"brandImg": {
 * 				"type": "keyword",
 * 				"index": false,
 * 				"doc_values": false
 *            },
 * 			"catalogName": {
 * 				"type": "long"
 *            },
 * 			"attrs": {
 * 				"type": "nested",
 * 				"properties": {
 * 					"attrId": {
 * 						"type": "long"
 *                    },
 * 					"attrName": {
 * 						"type": "keyword",
 * 						"index": false
 *                    },
 * 					"attrValue": {
 * 						"type": "keyword"
 *                    }
 *                }
 *            }
 *         }
 *  	}
 *   }
 *
 *
 *  // 保存到es
 *  // 给es中建立索引。product，建立好映射关系。
 *
 *  // 2、给es中保存这些数据
 *  // BulkRequest bulkRequest, RequestOptions options
 *
 *  BulkRequest bulkRequest = new BulkRequest();
 *  for(SkuEsModel model : skuEsModels){
 *      // 1、构造保存请求
 *      IndexRequest indexRequest = new IndexRequest(EsConstant.PRODUCT_INDEX);
 *      indexRequest.id(model.getSkuId().toString());
 *      indexRequest.source(JSON.toJSONString(model),XContentType.JSON);
 *
 *      bulkRequest.add(indexRequest);
 *
 *  }
 *
 *   bulkResponse bulk = restHighLevelClent.bulk(bulkRequest,Config.COMMON_OPTIONS);
 *
 *   // 如果批量错误
 *   boolean b = bulk.hasFailures();
 *   List<String> collect = Arrays.stream(bulk.getItems()).map(item -> {
 *       return item.getId();
 *   }).collect(Collectors.toList());
 *   log.error("商品上架完成：{}"，collect);
 *
 *
 *  Feign调用流程
 *  1、构造请求数据，将对象转为json；
 *       RequestTemplate template = buildTemplateFromArgs.create(argv);
 *  2、发送请求进行执行（执行成功解码响应数据）
 *       executeAndDecode(template);
 *  3、执行请求会有重试机制
 *       while(true){
 *           try{
 *               executeAndDecode(template);
 *           }catch(){
 *               try{retryer.continueOrPropagate(e);}catch(){throw ex}
 *               continue;
 *           }
 *       }
 *
 *
 *  GET product/_search
 *

 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */





}
