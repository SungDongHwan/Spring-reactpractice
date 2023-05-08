# To-do List 
- Spring-react를 이용한 Todo리스트 를 security 구현한 클론코딩입니다. 
---

## Front-end
- React 버전을 낮춰서 썼기때문에 참고 하시길 바랍니다.
```
React-dependency
    "@emotion/react": "^11.9.0",
    "@emotion/styled": "^11.8.1",
    "@mui/icons-material": "^5.6.0",
    "@mui/material": "^5.6.0",
    "@testing-library/jest-dom": "5.16.4",
    "@testing-library/react": "^12.1.4",
    "@testing-library/user-event": "^13.5.0",
    "react": "^18.0.0",
    "react-dom": "^18.0.0",
    "react-scripts": "5.0.0",
    "web-vitals": "^2.1.4"
```
---
## Back-End
- Spring 기반, gradle 사용
```
    'JDK-17'
    id 'org.springframework.boot' version '3.0.6'
    id 'io.spring.dependency-management' version '1.1.0'
    implementation 'org.springframework.boot:spring-boot-starter-data-jdbc'
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
    implementation 'org.springframework.boot:spring-boot-starter-web' // 3.0/
    implementation 'org.springframework.boot:spring-boot-starter-actuator' // 3.0.6 이용
    compileOnly 'org.projectlombok:lombok'
    developmentOnly 'org.springframework.boot:spring-boot-devtools'
    runtimeOnly 'com.h2database:h2'
    annotationProcessor 'org.projectlombok:lombok'
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
    implementation group: 'com.google.guava', name: 'guava', version: '31.1-jre'
    implementation 'io.jsonwebtoken:jjwt-api:0.11.2'
    runtimeOnly 'io.jsonwebtoken:jjwt-impl:0.11.2'
    runtimeOnly 'io.jsonwebtoken:jjwt-jackson:0.11.2' // for Jackson JSON processing
