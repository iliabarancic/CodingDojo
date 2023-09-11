package kata.potter

import java.math.BigDecimal

class Potter {

    enum class Books {
        BOOK_1, BOOK_2, BOOK_3, BOOK_4, BOOK_5
    }
    
    fun calculateDiscount(books: List<Books>) : Int {
        val groupedBooks: Map<Books, Int> = books.groupingBy { it }.eachCount()


        return when (groupedBooks.size) {
            2 -> 5
            3 -> 10
            4 -> 20
            5 -> 25
            else -> 0
        }
    }

    fun createBundles(groupedBooks: Map<Books, List<Books>>) {

    }

    fun calculateFinalPrice(books: List<Books>) : BigDecimal {
        val distinctSize = books.distinct().size

        val priceWithoutDiscount = BigDecimal("8").multiply(BigDecimal(books.size))

        if(distinctSize == 2) {
            return priceWithoutDiscount.multiply(BigDecimal("0.95"))
        }
        return priceWithoutDiscount
    }

}