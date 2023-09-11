package kata.potter

import io.kotest.matchers.shouldBe
import kata.potter.Potter.Books.BOOK_1
import kata.potter.Potter.Books.BOOK_2
import kata.potter.Potter.Books.BOOK_3
import kata.potter.Potter.Books.BOOK_4
import kata.potter.Potter.Books.BOOK_5
import org.junit.jupiter.api.Test

class PotterTest {

    private val sut: Potter = Potter()

    @Test
    fun oneBookCosts8Euro() {
        val books = listOf(BOOK_1)
        sut.calculateDiscount(books) shouldBe 0
    }

    @Test
    fun twoSameBooksCost16Euros() {
        val books = listOf(BOOK_1, BOOK_1)
        sut.calculateDiscount(books) shouldBe 0
    }

    @Test
    fun twoDifferentBooksGrant5PercentDiscount() {
        val books = listOf(BOOK_1, BOOK_2)
        sut.calculateDiscount(books) shouldBe 5
    }

    @Test
    fun threeDifferentBooksGrant10PercentDiscount() {
        val books = listOf(BOOK_1, BOOK_2, BOOK_3)
        sut.calculateDiscount(books) shouldBe 10
    }

    @Test
    fun fourDifferentBooksGrant20PercentDiscount() {
        val books = listOf(BOOK_1, BOOK_2, BOOK_3, BOOK_4)
        sut.calculateDiscount(books) shouldBe 20
    }

    @Test
    fun fiveDifferentBooksGrant25PercentDiscount() {
        val books = listOf(BOOK_1, BOOK_2, BOOK_3, BOOK_4, BOOK_5)
        sut.calculateDiscount(books) shouldBe 25
    }

    @Test
    fun twoSameBooksAndOneDifferent() {
        val books = listOf(BOOK_1, BOOK_1, BOOK_3)
        sut.calculateDiscount(books) shouldBe 5
    }
}