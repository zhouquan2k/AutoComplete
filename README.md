# AutoComplete

## Plan
<img width="1614" alt="WeChat2da7e64c1f251c19cc6411152f513399" src="https://user-images.githubusercontent.com/7393184/127791082-e45351be-7ae8-4777-b46a-0b55b9061514.png">

## compile and test 
* mkdir autocomplete;cd  autocomplete
* git clone -b master https://github.com/zhouquan2k/AutoComplete.git
* cd AutoComplete; mvn verify  # deploy mysql container, run integration test, see testcase: ex.autocomplete.test.AutoCompleteApiIT 

## run
* chmod +x start_mysql; ./start_mysql # start mysql container 
* mvn -D app.mysql-host=127.0.0.1:33066 exec:java # run rest api server

## api url
* api:  GET http://host:8088/suggestions?q= &latitude= &longitude= 
* swagger doc: http://host:8088/swagger-ui.html

## Design: Main class diagram
<img width="1424" alt="WeChatcab89d8d77058e841aea4182a68c4d8d" src="https://user-images.githubusercontent.com/7393184/127795435-95fd286f-21b1-434e-8277-d4ab52b45efe.png">


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

