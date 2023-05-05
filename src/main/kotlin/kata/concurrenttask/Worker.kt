package kata.concurrenttask

/**
 *  @see <a href="https://www.codewars.com/kata/5b34ca5791c746079c000067/java"> Concurrent Task Executor </a>
 */
class Worker {
    fun process(vararg tasks: Task, timeoutInSec: Int): ExecutedTasks {
        return ExecutedTasks()
    }
}

data class ExecutedTasks(
    val successfulTasks: MutableList<Task> = mutableListOf(),
    val timedOutTasks: MutableList<Task> = mutableListOf(),
    val failedTasks: MutableList<Task> = mutableListOf(),
)

class Task(val name: String)

