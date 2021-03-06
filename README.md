# AutoComplete

## Plan
<img width="1614" alt="WeChat2da7e64c1f251c19cc6411152f513399" src="https://user-images.githubusercontent.com/7393184/127791082-e45351be-7ae8-4777-b46a-0b55b9061514.png">


## prerequisite
* git
* maven 3
* jdk 8+
* docker
* linux （docker on mac may be incompatible when pull images)

## compile and test 
* mkdir autocomplete;cd  autocomplete
* git clone -b master https://github.com/zhouquan2k/AutoComplete.git
* cd AutoComplete; mvn verify  # deploy mysql container, run integration test, see testcase: ex.autocomplete.test.AutoCompleteApiIT 

## run
* chmod +x start_mysql; ./start_mysql # start mysql container 
* mvn -D spring.profiles.active=prod exec:java # run rest api server, import about 190K city data to database when first time run 
* TODO for production usage,should package to a docker image for easy deployment

## api url
* api:  GET http://host:8088/suggestions?q= &latitude= &longitude= 
* swagger doc: http://host:8088/swagger-ui.html
* remote url: http://tencent.atuspace.site:8088/swagger-ui.html

## Design: Main class diagram
<img width="889" alt="WeChat3932ca9b98bebed12986b0d2b937d3b5" src="https://user-images.githubusercontent.com/7393184/127803484-9675f573-f695-4522-96fe-b19d7c2d2160.png">

## Design: score 
* distance = 6378.138 * 2 * ASIN(
      SQRT(
        POW(
          SIN(
            (
              lat1 * PI() / 180 - lat2 * PI() / 180
            ) / 2
          ), 2
        ) + COS(lat1 * PI() / 180) * COS(lat2 * PI() / 180) * POW(
          SIN(
            (
              lng1 * PI() / 180 - lng2 * PI() / 180
            ) / 2
          ), 2
        )
      )
    ) 
* score = distance/max distance * 0.8 + matched_length/city_name_length * 0.2
* (0.8,0.2 is the weight for distance and text match)

## Design: Java Package comment
* ex.autocomplete: Rest API
* ex.autocomplete.impl.sql:  using Sql to implement auto complete function
* compoment.mybatisex: extend Mybatis-Plus to support batch insert


## Automated test
* using integration test maven plugin: maven-failsafe-plugin
* using docker-maven-plugin to run mysql container for integration test

## Used tech stack
* Java 8
* SpringBoot 2.3 (WebServer and Rest API)
* MybatisPlus (ORM)
* Druid (DB Connection Pool Management)
* Mysql 
* Swagger (http api doc)
* mybatis-enhance-actable (auto create table)
* maven plugins: maven-failsafe-plugin,docker-maven-plugin (Integration Test)

... see details in pom.xml

