## setion04
java 버전을 21로 변경

신규 프로젝트 생성후,

- gradle/wrapper/gradle-wrapper.properties 내용 복사
- build.gradle 수정

java {
sourceCompatibility = '21'
}

- settting.gradle 수정
- settings -> gradle 에서 gradle jvm 을 17 -> 21 로 변경