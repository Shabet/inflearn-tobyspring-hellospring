# inflearn-tobyspring-hellospring

## HTTPie
```
http --verify=no -v https://open.er-api.com/v6/latest/USD
```

## setion04
## 스프링과 JDK 업그레이드

java 버전을 21로 변경하기

신규 프로젝트 생성후,

- gradle/wrapper/gradle-wrapper.properties 내용 복사
- build.gradle 수정
- 자바 버전 변경
```
#in build.gradle
java {
    sourceCompatibility = '21'
}
```
- settting.gradle 수정
- settings -> gradle 에서 gradle jvm 을 17 -> 21 로 변경


## setion05
## JPA 설정

- in build.gradle,

```
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
    runtimeOnly 'com.h2database:h2'
}
```
