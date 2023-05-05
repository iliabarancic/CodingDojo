package kata.concurrenttask

import io.kotest.matchers.collections.shouldContainExactly
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class WorkerTest {

    private val sut = Worker()

    @Test
    fun testSuccessful() {
        val good = TimedTask("task1", 1)
        runBlocking {
            val result = sut.process(good, timeoutInSec = 5)
            result.successfulTasks shouldContainExactly listOf(good)
        }
    }

    @Test
    fun testTimedOut() {
        val timedOut = TimedTask("task1", 2)
        runBlocking {
            val result = sut.process(timedOut, timeoutInSec = 1)
            result.timedOutTasks shouldContainExactly listOf(timedOut)
        }
    }
}