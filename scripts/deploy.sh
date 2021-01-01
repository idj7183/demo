#!/bin/bash

REPOSITORY=/home/ec2-user/app/step2
PROJECT_NAME=demo

echo "> Build 파일 복사"

cp $REPOSITORY/zip/*.jar $REPOSITORY/

echo "> 8080 포트가 사용 중이라면 다운 종료"

fuser -k -n tcp 8080

echo "> 새 애플리케이션 배포"

JAR_NAME=$(ls -tr $REPOSITORY/*.jar | tail -n 1)

echo "> JAR NAME $JAR_NAME 에 실행권한 추가"

chmod +x $JAR_NAME

echo "> $JAR_NAME 실행"

nohup java -jar -Dspring.config.location=/home/ec2-user/app/step2/zip/src/main/resources/resources,/home/ec2-user/app/application-oauth.properties \
  $JAR_NAME > $REPOSITORY/nohup.out 2>&1 &

