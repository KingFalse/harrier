package me.kagura.harrier

import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import java.util.concurrent.Executors

/**
 * Returns a list containing the results of applying the given [funWorker] function with Multithreading
 * to each element in the original collection.
 */
inline fun <T> Iterable<T>.mapMultithreading(nThreads: Int = Runtime.getRuntime().availableProcessors(), crossinline funWorker: (T) -> Any?): Iterable<Any?> {
    val results = arrayOfNulls<Any>(count())
    runBlocking {
        Executors.newWorkStealingPool(nThreads).asCoroutineDispatcher().use {
            forEachIndexed { index, arg ->
                async(it) {
                    results[index] = funWorker(arg)
                }
            }
        }
    }
    return results.asIterable()
}
