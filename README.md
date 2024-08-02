# inflearn-tobyspring-hellospring

## 스프링과 JDK 업그레이드
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
