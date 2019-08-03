# harrier 
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/me.kagura/harrier/badge.svg)](https://maven-badges.herokuapp.com/maven-central/me.kagura/harrier) 
![](https://img.shields.io/badge/language-Kotlin-red.svg)
![](https://img.shields.io/badge/platform-jvm-red.svg)
![](https://img.shields.io/badge/license-MIT-blue.svg)

*Simpler use of multithreading in Kotlin*

## Example
```kotlin
@Test
fun mapMultithreading() {
    (1..10).mapMultithreading {åçç
        println("--->${Thread.currentThread().name}")
        Thread.sleep(1000)
    }
}

@Test
fun mapMultithreadingWithCount() {
    val resultList = (1..10).mapMultithreading(10) {
        Thread.sleep(1000)
        Thread.currentThread().name
    }
    resultList.forEach { println(it) }
}

@Test
fun mapMultithreadingWithReturn() {
    val resultList = (1..10).mapMultithreading {
        Thread.sleep(1000)
        return@mapMultithreading Thread.currentThread().name
    }
    resultList.forEach { println(it) }
}
```
## Getting started
```xml
<dependency>
  <groupId>me.kagura</groupId>
  <artifactId>harrier</artifactId>
  <version>1.0</version>
</dependency>
```