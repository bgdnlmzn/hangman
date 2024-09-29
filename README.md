# **CLI игра "Виселица"**
- **Java** 22
- **Maven** 3.9.6

Сборка проекта:
```shell
./mvnw clean verify
```
Запуск игры:
```shell
java -jar target/java-1.0.0.jar
```
Запуск только компиляции основных классов:

```shell
./mvnw compile
```

Запуск тестов:

```shell
./mvnw test
```

Запуск линтеров:

```shell
./mvnw checkstyle:check modernizer:modernizer spotbugs:check pmd:check pmd:cpd-check
```
