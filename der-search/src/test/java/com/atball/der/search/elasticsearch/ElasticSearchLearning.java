package com.atball.der.search.elasticsearch;

import org.springframework.data.mongodb.core.mapping.Field;

/**
 *  学习
 * 查看索引信息：/_cat/indices
 *                                                                              数据存储索引
 *           日志采集                                     数据传输汇聚            ElasticSearch集群                 异常定位与监控
 *
 *          ***********                                                         ***********
 *          *  服务器  *     ***********                                         *  ES节点  *                       全文检索
 *          ***********               *                 ************            ***********
 *                                    *                 *   Kafka  *
 *                                    *                 ************
 *          ************              *                                         ************
 *          *  移动设备  *    *********************》                             *  ES节点   *                     结构化查询
 *          ************              *                 ************            ************
 *                                    *                 * Logstash *
 *                                    *                 ************
 *          ************              *                                         ************
 *          * IOT传感器 *    ***********                                         *  ES节点   *                     数据可视化
 *          ************                                                        ************
 *
 */

public class ElasticSearchLearning {

    @Field
    private String Id;

    /**
     *
     *
     * 1、更新
     *
     *   GET index/doc/id?if_seq_no=0&if_primary_term=1
     *
     *   put 和 post（不带_update）都会直接更新数据
     *
     *
     *   POST操作会对比源文档数据，如果相同不会有什么操作，文档version不增加
     *   PUT操作总会将数据重新保存并增加version版本；
     *      带_update对比元数据 如果一样就不进行任何操作。
     *      看场景：
     *          对于大并发更新，不带update；
     *          对于大并发查询偶尔更新，带update；对比更新，重新计算分配规则。
     *
     *
     * 2、批量更新
     *
     *    index/doc/_bulk
     *    {"index":{"_id":"1"}}
     *    {"name":"John Doe"}
     *    {"index":{"_id":"2"}}
     *    {"name":"John Doe"}
     *
     *
     * 3、查询
     *
     * index/search
     * {
     *     "query":{
     *         "match":{
     *             "balance":20
     *         }
     *     }
     * }
     *
     * // match 进行分词匹配
     * index/search
     * {
     *     "query":{
     *         "match":{
     *             "address": "Kings"
     *         }
     *     }
     * }
     *
     *
     * // 短语匹配 match（进行了分词）
     * index/search
     * {
     *     "query":{
     *         "match_phrase":{
     *             "address": "Kings"
     *         }
     *     }
     * }
     *
     * // 多字段匹配 multi_match （也会进行分词）
     *
     * state 或者 address 中存在 mill
     * index/search
     * {
     *     "query":{
     *         "multi_match":{
     *              "query":"mill",
     *              "fields":["state","address"]
     *         }
     *     }
     * }
     *
     * // bool 复合查询
     * index/search
     * {
     *      "query":{
     *          "bool":{
     *                "must":[
     *      *              {"match":{"age":"40"}}
     *      *         ],
     *      *         "must_not":[
     *      *              {"match":{"state":"ID"}}
     *      *         ]
     *      *     }
     *
     *      }
     *
     *
     * }
     *
     *
     *
     *
     * // 迁移数据
     *
     * POST  _reindex
     * {
     *     "source":{
     *         "index":"product"
     *     },
     *     "dest":{
     *         "index":"product_new"
     *     }
     *
     * }
     *
     *
     *
     *  https://zhuanlan.zhihu.com/p/559632776（万字长文：一文彻底搞懂Elasticsearch中Geo数据类型查询、聚合、排序）
     *
     *  https://blog.csdn.net/tianyaleixiaowu/article/details/76149547/
     *
     *
     *
     *
     *
     *
     *
     *
     *  每一个搜索请求都需要命中索引中的每一个分片，如果每一个分片都处于不同的节点还好，但如果多个分片都需要在同一个节点上竞争使用相同的资源就有些糟糕了
     *
     *  用于计算相关度的词项统计信息是基于分片的。如果有许多分片，每一个都只有很少的数据会导致很低的相关度。
     *
     *
     *
     *
     *
     *
     */
}
