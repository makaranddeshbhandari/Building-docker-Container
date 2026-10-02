FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY Simple-date-Predictor.java .

RUN javac Simple-date-Predictor.java

CMD ["java","SimpleDatePredictor"]
 
