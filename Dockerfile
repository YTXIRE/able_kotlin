FROM maven:3.8.4-openjdk-17

WORKDIR /app

RUN chmod 777 /app

COPY . ./

RUN mvn install

CMD mvn clean test -Dmode="selenoid"
