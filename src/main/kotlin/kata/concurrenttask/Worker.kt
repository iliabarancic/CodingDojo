package kata.concurrenttask

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeoutOrNull
import kotlin.time.Duration.Companion.seconds

class Worker {
    suspend fun process(vararg tasks: Task, timeoutInSec: Int): ExecutedTasks {
        val executedTasks = ExecutedTasks()
        withTimeoutOrNull(timeoutInSec.seconds.inWholeMilliseconds) {
            tasks.forEach { task ->
                launch {
                    try {
                        task.execute()
                        executedTasks.addSuccessfull(task)
                    } catch (e: CancellationException) {
                        executedTasks.addTimedOut(task)
                    } catch (e: Exception) {
                        executedTasks.addFailed(task)
                    }
                }
            }
        }

        return executedTasks

    }
}

data class ExecutedTasks(
    val successfulTasks: MutableList<Task> = mutableListOf(),
    val timedOutTasks: MutableList<Task> = mutableListOf(),
    val failedTasks: MutableList<Task> = mutableListOf(),
) {
    fun addSuccessfull(task: Task) = successfulTasks.add(task)
    fun addFailed(task: Task) = failedTasks.add(task)
    fun addTimedOut(task: Task) = timedOutTasks.add(task)
}


sealed class Task(val name: String) {
    abstract suspend fun execute()

    override fun toString(): String {
        return "Task(name='$name')"
    }
}

class TimedTask(name: String, private val executionTimeInSec: Int) : Task(name) {
    override suspend fun execute() {
        delay(executionTimeInSec.seconds.inWholeMilliseconds)
    }
}

class FailedTask(name: String) : Task(name) {
    override suspend fun execute() {
        throw RuntimeException("the disappointment is immeasurable")
    }
}


