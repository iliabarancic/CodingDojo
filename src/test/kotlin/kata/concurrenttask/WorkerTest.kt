package kata.concurrenttask

import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.collections.shouldHaveSize
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test

class WorkerTest {

    private val sut = Worker()

    // actions=[Task3(throws after 3s), Task4(compl. after 4s), Task2(compl. after 2s), Task1(throws after 1s)], timeout=8s
    @Test
    fun `1 task succeeds without timeout`() {
        val durationInSeconds = 5
        val task1 = SuccessfulTask("Task 1", durationInSeconds)

        val executed = runBlocking {
            sut.process(task1, timeoutInSec = durationInSeconds + 1)
        }

        executed.successfulTasks shouldContainExactlyInAnyOrder listOf(task1)
        executed.failedTasks shouldHaveSize 0
        executed.timedOutTasks shouldHaveSize 0
    }

    @Test
    fun `1 task fails without timeout`() {
        val durationInSeconds = 3
        val task1 = FailingTask("Task 1", durationInSeconds)

        val executed = runBlocking {
            sut.process(task1, timeoutInSec = durationInSeconds + 1)
        }

        executed.successfulTasks shouldHaveSize 0
        executed.failedTasks shouldContainExactlyInAnyOrder listOf(task1)
        executed.timedOutTasks shouldHaveSize 0
    }


    @Test
    fun `1 task times out`() = runTest {
        val durationInSeconds = 3
        val task1 = FailingTask("Task 1", durationInSeconds + 5)

        val executed = sut.process(task1, timeoutInSec = durationInSeconds)

        executed.successfulTasks shouldHaveSize 0
        executed.failedTasks shouldHaveSize 0
        executed.timedOutTasks shouldContainExactlyInAnyOrder listOf(task1)
    }

}