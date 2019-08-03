package me.kagura.harrier

import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.Executors

/**
 * Returns a list containing the results of applying the given [funWorker] function
 * to each element in the original collection.
 */
inline fun <T> Iterable<T>.mapMultithreading(nThreads: Int = 5, crossinline funWorker: (T) -> Any?): Iterable<Any?> {
    val results = arrayOfNulls<Any>(count())
    val coroutineDispatcher = Executors.newFixedThreadPool(nThreads).asCoroutineDispatcher()
    runBlocking {
        coroutineDispatcher.use {
            launch {
                forEachIndexed { index, arg ->
                    async(coroutineDispatcher) {
                        funWorker(arg).let {
                            results[index] = it
                        }
                    }
                }
            }.join()
        }
    }
    return results.asIterable()
}
