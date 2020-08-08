
#elasticsearch dev tools  
初识 elasticsearch

```GET _search
   {
     "query": {
       "match_all": {}
     }
   }
   
   GET _cat/master?v
   
   
   
   #查看索引相关信息
   GET kibana_sample_data_ecommerce/_count
   
   GET movies/_count
   
   #查看前十条文档， 了解文档格式
   POST kibana_sample_data_ecommerce/_search
   {
   }
   
   GET _cat/indices/kibana*?v
   
   # 查询所有 健康状态为green 的索引
   GET _cat/indices?v&health=green
   
   # 按照文档个数排序
   GET /_cat/indices?v&s=docs.count:desc
   
   GET /_cat/indices/kibana_*?v&pri&h=health,index,pri,docs.count,mt,deleted
   
   GET /_cat/indices?v&h-i,tm&s-tm:desc
   
   
   ### 节点 - 集群
   
   GET _cluster/health
   
   GET _cat/nodes?v
   
   GET _cat/shards?v
   
   
   ####  CRUD
   
   # create document  自动生成 _id
   POST users/_doc/
   {
     "user":"Mick",
     "post_date":"2020-07-26 19:43:00",
     "message":"trying out kibana"
   }
   
   ## create document 指定id 如果已经存在（根据version判断） - 报错
   PUT /users/_doc/1?op_type=create
   {
       "user":"Ryan111",
       "post_date":"2020-07-26 19:43:00",
       "message":"trying out Elasticsearch11" 
   }
   
   ## index 没有新增，有的话删了version 新增 重新插入
   PUT /users/_doc/1?op_type=index
   {
       "user":"Ryan",
       "post_date":"2020-07-26 19:43:00",
       "message":"trying out Elasticsearch" 
   }
   
   
   PUT /users/_doc/1
   {
     "user":"lili"
   }
   
   ## 在原来索引上新增字段
   POST /users/_update/1
   {
     "doc":{
       "post_data":"2020-08-01",
       "message":"一会去取快递",
       "name":"mm"
     }
   }
   
   DELETE /users/_doc/1
   
   GET /users/_doc/1
   
   GET /users/_doc/2
   
   GET /movies/_doc/2
   
   ### 使用 _Analyzer API
   
   # standard 默认分词器、按此切分、小写、
   GET /_analyze
   {
     "analyzer": "standard",
     "text": "2 running Quick brown-foxes leap over lazy dogs in the summer evening."
   }
   
   # simple
   GET _analyze
   {
     "analyzer": "simple",
     "text": "2 running Quick brown-foxes leap over lazy dogs in the summer evening."
   }
   
   
   GET _analyze
   {
     "analyzer": "icu_analyzer",
     "text": "他说的的确在理"
   }
   
   GET _analyze
   {
     "analyzer": "ik_max_word",
     "text": "他说的的确在理"
   }
   GET _cat/plugins
   
   
   GET kibana_sample_data_ecommerce/_search?q=customer_first_name:Eddie
   
   GET /movies/_search?q=2012&df=title
   {
     "profile":true
   }
   
   ## 泛查询 正对 _all ，所有字段
   GET /movies/_search?q=2012
   {
     "profile":true
   }
   
   ##  查询指定字段
   GET /movies/_search?q=title:2012
   {
     "profile":true
   }
   
   #使用引号 PhraseQuery  短语查询
   GET /movies/_search?q=title:"Beautiful mind"
   {
     "profile":true
   }
   
   
   GET /movies/_search?q=title:Beautiful Mind
   {
     "profile":true
   }
   
   GET /movies/_search?q=title:(Beautiful NOT Mind)
   {
     "profile" :true
   }
   
   #模糊匹配和相似搜索
   GET /movies/_search?q=title:beautif~2
   {
     "profile": "true"
   }
   
   GET /movies/_search?q=title:"Lord Rings"~6
   {
     "profile": "true",
     "_source": ["title"],
     "from": 0,
     "size": 2,
     "sort": [
       {
         "year": {
           "order": "desc"
         }
       }
     ]
   }
   
   
   POST /movies/_search
   {
     "from": 0,
     "size": 2, 
     "_source": "title", 
     "query": {
       "match": {
         "title":{
           "query":"last Christmas",
           "operator":"and"
         }
       }
     }
   }
   
   
   POST /movies/_search
   {
     "query": {
       "match_phrase": {
         "title": {
           "query": "one love",
           "slop": 3
         }
       }
     }
   }
   
   
   PUT mapping-test/_doc/1
   {
     "firstname":"xiao ming",
     "lastname":"xiao qiang",
     "createDate":"2020-08-02"
   }
   
   GET /mapping-test/_mapping
   DELETE mapping-test
   
   PUT mapping-test/_doc/1
   {
     "uid":"122",
     "isAdmin":false,
     "isVip":"true",
     "age":23,
     "height":185,
     "weight":98.4
   }
   
   GET mapping-test/_mapping 
   
   GET movies/_mapping
   
   DELETE users
   
   PUT users
   {
       "mappings" : {
         "properties" : {
           "message" : {
             "type" : "text",
             "fields" : {
               "keyword" : {
                 "type" : "keyword",
                 "ignore_above" : 256
               }
             }
           },
           "id" : {
             "type" : "text",
             "fields" : {
               "keyword" : {
                 "type" : "keyword",
                 "ignore_above" : 256
               }
             }
           },
           "title" : {
             "type" : "text",
             "fields" : {
               "keyword" : {
                 "type" : "keyword",
                 "ignore_above" : 256
               }
             }
           },
           "year" : {
             "type" : "long",
             "index":false
           }
         }
       }
   }
   
   
   GET users/_mapping
   
   PUT users/_doc/2
   {
     "id":"123",
     "title":"elasticsearch",
     "message":["hhxx ttxs","123sdf"],
     "year":2020
   }
   
   POST users/_search
   {
     "query": {
       "match": {
         "year":2020
       }
     }
   }
   
   GET users/_doc/2
   
   
   GET _cat/templates?v
   
   GET _template/
   
   
   
   GET   kibana_sample_data_flights/_mapping
   #按目的地进行分桶统计
   GET kibana_sample_data_flights/_search
   {
     "size": 1, 
     "aggs": {
       "flight_dest": {
         "terms": {
           "field": "DestCountry"
         }
       }
     }
   }
   
   
   # 查看航班目的地统计信息，增加平均、最高最低价格
   GET kibana_sample_data_flights/_search
   {
     "size": 0, 
     "aggs": {
       "flight_dest": {
         "terms": {
           "field": "DestCountry"
         },
         "aggs": {
           "avg_price": {
             "avg": {
               "field": "AvgTicketPrice"
             }
           },
           "max_price":{
             "max":{
               "field": "AvgTicketPrice"
             }
           },
           "min_price":{
             "min": {
               "field": "AvgTicketPrice"
             }
           }
         }
       }
     }
   }
   
   GET  kibana_sample_data_flights/_mapping
   
   # 价格统计信息 + 天气信息
   GET kibana_sample_data_flights/_search
   {
     "size": 0,
     "aggs":{
       "flight_dest":{
         "terms":{
           "field": "DestCountry"
         },
         "aggs":{
           "stats_price":{
             "stats": {
               "field": "AvgTicketPrice"
             }
           },
           "wather":{
             "terms": {
               "field": "DestWeather"
             }
           }
         }
       }
       
     }
   }
   
   
   
   
   
```