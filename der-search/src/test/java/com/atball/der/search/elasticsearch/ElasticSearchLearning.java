package com.atball.der.search.elasticsearch;

import org.springframework.data.mongodb.core.mapping.Field;

// es 分布式、高性能、高可用、可伸缩、易维护
//  分布式的搜索、存储、和数据分析引擎

// 应用领域
/**
 * 百度（全文检索、高亮、搜索推荐）
 * 各大网站的用户行为日志（用户点击、浏览、点赞）
 * BI（Business Intelligence）数据分析：数据挖掘统计
 * ELK
 */

// ES 核心概念
/**
 * Cluster(集群)：每个集群至少包含两个节点
 * Node：集群中的每个节点，一个节点不代表一台服务器
 * Field：一个数据字段，与index和type一起，可以定位一个doc
 * Document:ES最小的数据单元，JSON
 * type：；逻辑上的数据分类
 * index: 一类相同或类似的doc，比如一个员工索引，商品索引
 *
 * Doc<>row  type<>table
 */

// es 容错机制（面试高频）
/**
 * 第一步： master选举
 *        {master选举过程：findMaster（ping所有的节点，看看有没有哪个节点是Master）}
 *        1)、脑裂：可能产生多个Master节点
 *        2）、discovery.zen.minimum_master_nodes = N / 2 + 1
 * 第二步： replica容错，新的（或者原有）Master节点会将丢失的Primary对应的某个副本提升为Primary
 * 第三步： master节点尝试重启故障机
 * 第四步： 数据恢复 （Master会将宕机期间丢失的数据同步到重启机器对应的分片上去）
 */
// es的集群检查
/**
 * green: 所有的p shard和r shard均为active，集群很健康
 * yellow：至少一个replica shard不可用，但是数据仍然是完整的
 * red:至少有一个p shard为不可用状态，数据不完整，集群不可用
 */
// 如何提高ES分布式系统的可用性以及性能最大化
/**
 * 1)、每台节点的shard数量越少，每个shard分配的CPU、内存和IO资源越多，单个Shard的性能越好，当一台机器一个shard
 *     时，单个shard性能最好
 * 2)、稳定的Master节点对于集群健康非常重要！理论上讲，应该尽可能的减轻Master节点的压力，
 *     分片数量越多，Master节点维护管理shard的任务越重，并且节点可能就要承担更多的数据转发任务，可增加"仅协调"节点
 *     来缓解Master节和Data节点的压力，但是在集群中添加过多的"仅协调"节点会增加整个集群的负担，因为选择的主节点必须
 *     等待每个节点的集群状态更新确认。
 * 3）、反过来说，如果相同资源分配相同的前提下，shard数量越少，单个shard的体积越大，查询性能越低，速度越慢，这个
 *      取舍应根据实际集群状况和结合应用场景等因素综合考虑。
 * 4）、数据节点和Master节点一定要分开，集群规模越大，这样做的意义也就越大
 * 5）、数据节点处理与数据相关的操作，例如CRUD，搜索和聚合。这些操作是I/O，内存和CPU密集型的，所以他们需要更高的配置的
 *      服务器以及更高的带宽，并且集群的性能冗余非常重要。
 * 6）、由于仅投票节点不参与Master竞选，所以和真正的Master节点相比，它需要的内存和CPU较少，但是，所有候选节点以及仅投票节点
 *      都可能是数据节点，所以他们都需要快速稳定低延迟的网络。
 * 7）、高可用性（HA）集群至少需要三个主节点，其中至少两个不是仅投票节点，即使其中一个节点发生故障，这样的集群也能够选举一个主节点。
 *      生产环境最好设置3台仅Master候选节点（node.master = true node.data = true）
 */
 // Mapping
/**
 * 查看Mapping: GET /product/_mapping
 * 为啥price是long类型而不是integer？因为es的mapping_type是由JSON分析器检测数据类型，而Json没有隐式类型转换（integer=>long
 *  or float => double）,所以dynamic mapping会选择一个比较宽的数据类型。
 */
 // 搜索方式
/**
 * 精确匹配
 * 全文检索：分词、近义词、同义词、混淆词、大小写、词性、过滤、时态转换等
 */
// 核心类型
/**
 * 数字类型：long integer short byte double float half scaled_float
 * 字符串：String
 *      a：keyword 适用于索引结构化字段，可以用于过滤、排序、聚合。keyword类型字段只能通过精确值搜索到。Id应该用keyword
 *      b：text 当字段要被全文检索的，比如Email内容、产品描述，这些字段应该使用text。设置text类型以后，字段内容会被分析，
 *              在生成倒排索引以前，字符串会被分词器分成一个一个词项。text类型字段不会用于排序，很少用于聚合。
 * 时间类型：date
 * 布尔类型：boolean
 * 区间类型：integer_range  long_range double_range float_range date_range
 * 复杂类型： 1）、Object 用于单个Json对象 2）、Nested用于Json对象数组
 * 地理位置：1）、Geo_point 经纬度积分 2）、GEO_shape:用于多边形等复杂形状
 *
 */
// 手工创建mapping
/**
 *
 */
// mapping parameters
/**
 * index: 是否对创建对当前字段创建索引，默认为true，如果不创建索引，该字段不会通过索引被搜索到
 *          但是仍然会在source元数据中展示
 * analyzer：指定分词器
 * boost：对当前字段相关度的评分权重，默认 1
 * coerce：是否允许强制类型转换
 * copy to：
 * doc_values: 为了提升排序和聚合效率，默认为true，如果确认不需要对字段进行排序或聚合，也不需要通过脚本
 * dynamic：
 * eager_global_ordinals: 用于聚合字段上，优化聚合性能
 * enable：是否创建倒排索引
 *
 * fields
 * format
 * null_value
 */
// 批量查询
/**
 *
 * GET /es_test/_mget
 * {
 *  "ids":["1","10"]
 *  }
 *
 * GET /index/_mget
 *  {
 *   "docs": [
 *     {
 *       "_id": 1,
 *       "_source":["name","price"]
 *     },
 *     {
 *       "_id": 10,
 *       "_source":{
 *           "include":[""],
 *           "exclude":[""]
 *       }
 *     }
 *   ]
 * }
 */

// 批量增删改
/**
 * PUT /index/_create/id    _create强制创建
 * PUT /index/id            Id存在更新
 *
 * create ： PUT /index/_create/id/ 强制创建（是否制定ID）
 * delete： 删除（lazy delete原理）
 * index： 可以是创建，也可以是全量替换
 * update： 执行partial update （全量替换，部分替换）
 */
// 分词
/**
 * GET /_analyze
 * {
 *     "text":"城管打电话喊商贩去摆摊摊"
 *     "analyzer":"standard"
 * }
 */

// 分词器
/**
 * 分析器：
 *      1、character filter（mapping）：分词之前预处理（过滤无用字符、标签等，转换一些&=>and 《Elasticsearch》=》Elasticsearch）
 *          1）、HTML Strip Character Filter： html strip
 *              a、参数：escaped tags 需要保留的html标签
 *          2）、Mapping Character Filter：type mapping
 *          3）、Pattern replace character filter：type pattern_replace
 *      2、tokenizer（分词器）：分词
 *      3、token filter：停用词、时态转换、大小写转换、同义词转换、语气词处理。比如：has=》have him=》he apples=》apple
 *         啊、哈干掉
 */
// 中文分词器
/**
 *  1、IK分词：
 *        1）、下载IK分词器
 *        2）、创建插件文件夹cd your-es-root/plugins/&&mkdir ik
 *        3）、将插件解压到文件夹your-es-root/plugins/ik
 *        4）、重新启动es
 *  2、两种analyzer
 *        1)、ik_max_word:细粒度
 *        2）、ik_smart:粗粒度
 *  3）、IK文件描述
 *        1）、IKAnalyzer.cfg.xml: IK分词配置文件
 *        2）、主词库：main.dic
 *        3）、英文停用词：stopword.dic
 *        4）、特殊词库
 *              a、quantifier.dic: 特殊词库：计量单位等
 *              b、suffix.dic: 特殊词库：后缀名
 *              c、surname.dic: 特殊词库: 百家姓
 *              d、preposition：特殊词库：语气词
 *        5）、自定义词库：比如当下流行词：857、emmm...、 渣女、舔屏、996
 *        6）、热更新：
 *              a.修改ik分词器源码
 *              b
 */

// ES查询之前缀搜索、通配符搜索、正则搜索、模糊查询串讲（fuzzy）
/**
 *  前缀搜索
 *      GET index/_search
 *      {
 *          "query":{
 *              "prefix":{
 *                  "text":"城管"
 *              }
 *          }
 *      }
 */

// ES JAVA API
/**
 *   <dependency>
 *       <groupId>org.springframework.boot</groupId>
 *       <artifactId>spring-boot-starter-data-elasticsearch</artifactId>
 *       <version>${spring-boot.version}</version>
 *   </dependency>
 *
 *   
 *
 */

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

//    GET /_cat/healthy?v
    /**
     * 创建索引：PUT /test_index?Pretty
     * GET /_cat/indices?v
     * 删除索引：DELETE /test_index?Pretty
     * 插入数据: PUT /index/_doc/id
     *          {
     *              Json数据
     *          }
     * 更新数据：
     *     1）、全量替换
     *
     *     2）、指定字段更新
     *          POST /index/_doc/id/update
     *          {
     *              指定字段Json
     *          }
     * 查询数据：GET /index/_search
     *
     *
     */

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
