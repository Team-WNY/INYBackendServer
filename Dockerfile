# 기반 이미지 설정
FROM openjdk:17.0.2-jdk-slim

# 작업 디렉토리 설정
WORKDIR /app

# 호스트의 build/libs 디렉토리에서 JAR 파일을 컨테이너의 /app 디렉토리로 복사
COPY build/libs/*.jar /app/app.jar

# 컨테이너 포트 노출
EXPOSE 8080

# 애플리케이션 실행 명령
CMD ["java", "-jar", "app.jar"]