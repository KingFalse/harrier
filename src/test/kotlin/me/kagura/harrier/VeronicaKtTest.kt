package me.kagura.harrier

import org.junit.jupiter.api.Test


class VeronicaKtTest {

    @Test
    fun mapMultithreading() {
        (1..10).mapMultithreading {
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

}
