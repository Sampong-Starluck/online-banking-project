plugins {
    java
    id("org.springframework.boot") version "4.0.0-M2"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "org.sampong"
version = "1.0.0"
description = "OnlineBanking"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    // Spring boot
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-hateoas")
    implementation("org.springframework.boot:spring-boot-starter-webclient")

    // Lombok
    implementation("org.projectlombok:lombok")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-batch")
    implementation("org.springframework.boot:spring-boot-starter-aop:4.0.0-M2")
    testImplementation("org.springframework.batch:spring-batch-test")
    annotationProcessor("org.projectlombok:lombok")

    // MapStruct
    implementation("org.mapstruct:mapstruct:1.6.3")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.6.3")

    // Lombok + MapStruct binding (so they work together)
    annotationProcessor("org.projectlombok:lombok-mapstruct-binding:0.2.0")

    // Spring data + JPA + postgreSQL
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    runtimeOnly("org.postgresql:postgresql")

    // Spring security + JWT
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("io.jsonwebtoken:jjwt-api:0.13.0")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.13.0")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.13.0")

    // Springdoc + OpenAPI + Swagger-UI
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:3.0.0-M1")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-api:3.0.0-M1")
    implementation("org.springdoc:springdoc-openapi-starter-common:3.0.0-M1")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<JavaCompile> {
    options.compilerArgs.add("-Amapstruct.defaultComponentModel=spring")
}

tasks.withType<Test> {
//    useJUnitPlatform()
    enabled = false
}
