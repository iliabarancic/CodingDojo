package kata.concurrenttask

import kotlinx.coroutines.Job
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull
import java.util.concurrent.TimeoutException
import kotlin.time.Duration.Companion.seconds

/**
 *  @see <a href="https://www.codewars.com/kata/5b34ca5791c746079c000067/java"> Concurrent Task Executor </a>
 *  @see <a href="https://kotlinlang.org/docs/coroutines-basics.html"> Coroutines Basics </a>
 *
 */
class Worker {
    suspend fun process(vararg tasks: Task, timeoutInSec: Int): ExecutedTasks {
        val executedTasks = ExecutedTasks()

        val jobs = mutableListOf<Job>()
        withTimeoutOrNull(timeoutInSec.seconds.inWholeMilliseconds) {
            tasks.forEach { task ->
                jobs.add(launch {
                    try {

                        task.execute()
                        executedTasks.successfulTasks.add(task)
                    } catch (e: TimeoutCancellationException) {
                        executedTasks.timedOutTasks.add(task)
                    } catch (e: Exception) {
                        executedTasks.failedTasks.add(task)
                    }
                })
            }
        }

        return executedTasks
    }
}

data class ExecutedTasks(
    val successfulTasks: MutableList<Task> = mutableListOf(),
    val timedOutTasks: MutableList<Task> = mutableListOf(),
    val failedTasks: MutableList<Task> = mutableListOf(),
)

sealed class Task(val name: String, protected val duration: Int) {
    abstract suspend fun execute()
}

class SuccessfulTask(name: String, duration: Int) : Task(name, duration) {
    override suspend fun execute() {
        delay(duration.seconds.inWholeMilliseconds)
    }
}

class FailingTask(name: String, duration: Int) : Task(name, duration) {
    override suspend fun execute() {
        delay(duration.seconds.inWholeMilliseconds)
        throw GeorgieException()
    }
}

class GeorgieException : Exception()