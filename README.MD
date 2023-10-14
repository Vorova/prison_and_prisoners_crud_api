Prison and Prisoner REST CRUD API
== 
Данный сервис выполняет CRUD операции над сущностями **Prison**(тюрьма) 
и **Prisoner** (заключенный). <br>
Отображает зависимость и отношения между тюрьмой и заключенными 
## Сущности в приложении

-----------------
> #### Prison - тюрьма
> `Long id` - primary key <br>
> `String title` - название

> #### Prisoner - заключенный 
> `Long id` - primary key <br>
> `String name` - имя заключенного <br>
> `Long prison_id` - reference key на prison(id)

## Таблицы в базе

-----------
>
> `CREATE TABLE prison
(
id    BIGSERIAL PRIMARY KEY,
title VARCHAR(128)
);`

>
>`CREATE TABLE prisoner
(
id        BIGSERIAL PRIMARY KEY,
name      VARCHAR(128),
prison_id BIGINT NOT NULL REFERENCES prison (id)
);`

## Стек технологий и библиотек

_______
* **Java 17** (используемая jdk)
* **Intellij Idea** (среда разрабоотки)
* **Gradle** (Инструмент сборки приложения)
* **Postgres 16.0** (База данных)
* **Tomcat 10.1.13** (Контейнер сервлетов)
* **Servlet Api 6.0** (Реализация сервлетов)
* **Lombok** (getters и setters для моделей)
* **Docker** (для запуска postgres)

## Тестирование в Postman

_____

По коллекции запросов можно провести тестирование сервиса. <br>
[Collection API for Postman](https://api.postman.com/collections/23657522-f0f22d11-977b-47c6-a773-f99ec67cf2e3?access_key=PMAT-01HCQQHWB19ZC4Z3CFRAEV0SPH)