FROM di/bet:latest

WORKDIR /home/bet-1.0-SNAPSHOT

ENV SERVER_PORT=8085 \
    DEVELOPER_MODE=true \
    WEBSERVICE_ADDRESS=http://172.20.10.19:8083/Service/PowerDataServicePort?wsdl \
    WHITE_LIST=0:0:0:0:0:0:0:1,127.0.0.1,172.20.10.23,172.20.10.19,172.17.0.1,172.17.0.3 \
    DATASOURCE_URL="jdbc:postgresql://172.20.10.19:54321/postgres?currentSchema=backcomm&gssEncMode=disable" \
    DATASOURCE_USERNAME=postgres \
    DATASOURCE_PASSWORD=postgres

#暴露端口
EXPOSE $SERVER_PORT

#映射地址
CMD java -Duser.timezone=CET \
         -Dfile.encoding=UTF-8 \
         -Dspring.config.additional-location=application.yml \
         org.springframework.boot.loader.JarLauncher  \
         --spring.datasource.url=$DATASOURCE_URL \
         --spring.datasource.username=$DATASOURCE_USERNAME \
         --spring.datasource.password=$DATASOURCE_PASSWORD \
         --server.port=$SERVER_PORT