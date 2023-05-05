package kata.gildedrose

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

internal class GildedRoseTest {

    @Test
    fun `quality degrades by two once sellin is below 0`() {
        val items = arrayOf(Item(name="georg", sellIn = -1, quality = 2))
        val sut = GildedRose(items)
        sut.updateQuality()
        items[0].quality shouldBe 0
    }

    @Test
    fun `quality degrades by one as long as sellIn is greater 0`() {
        val items = arrayOf(Item(name="georg", sellIn = 1, quality = 2))
        val sut = GildedRose(items)
        sut.updateQuality()
        items[0].quality shouldBe 1
    }

    @Test
    fun `quality degrades by two once sellIn is equal 0`() {
        val items = arrayOf(Item(name="georg", sellIn = 0, quality = 2))
        val sut = GildedRose(items)
        sut.updateQuality()
        items[0].quality shouldBe 0
    }

    @Test
    fun `quality stays 0 once it has reached 0`() {
        val items = arrayOf(Item(name="georg", sellIn = 1, quality = 0))
        val sut = GildedRose(items)
        sut.updateQuality()
        items[0].quality shouldBe 0
    }

    @Test
    fun `sellIn decreases by 1 if updateQuality is called once`() {
        val items = arrayOf(Item(name="georg", sellIn = 1, quality = 0))
        val sut = GildedRose(items)
        sut.updateQuality()
        items[0].sellIn shouldBe 0
    }

    @Test
    fun `sellIn can become negative`() {
        val items = arrayOf(Item(name="georg", sellIn = 0, quality = 0))
        val sut = GildedRose(items)
        sut.updateQuality()
        items[0].sellIn shouldBe -1
    }

    @Test
    fun `Aged Brie increases in quality the older it gets`() {
        val items = arrayOf(Item(name="Aged Brie", sellIn = 1, quality = 0))
        val sut = GildedRose(items)
        sut.updateQuality()
        items[0].quality shouldBe 1
    }

    @Test
    fun `Aged Brie's quality never exceeds 50`() {
        val items = arrayOf(Item(name="Aged Brie", sellIn = 1, quality = 50))
        val sut = GildedRose(items)
        sut.updateQuality()
        items[0].quality shouldBe 50
    }

    @Test
    fun `Aged Brie's quality never exceeds 50 starting with quality 49`() {
        val items = arrayOf(Item(name="Aged Brie", sellIn = 1, quality = 49))
        val sut = GildedRose(items)
        sut.updateQuality()
        items[0].quality shouldBe 50
    }

    @Test
    fun `Sulfuras quality never changes`() {
        val items = arrayOf(Item(name="Sulfuras, Hand of Ragnaros", sellIn = 1, quality = 80))
        val sut = GildedRose(items)
        sut.updateQuality()
        items[0].quality shouldBe 80
    }

    @Test
    fun `Backstage passes value increase by 1 when there are more than 10 days left`() {
        val items = arrayOf(Item(name="Backstage passes to a TAFKAL80ETC concert", sellIn = 11, quality = 10))
        val sut = GildedRose(items)
        sut.updateQuality()
        items[0].quality shouldBe 11
    }

    @Test
    fun `Backstage passes value increase by 2 when there are less or equal 10 days left`() {
        val items = arrayOf(Item(name="Backstage passes to a TAFKAL80ETC concert", sellIn = 10, quality = 10))
        val sut = GildedRose(items)
        sut.updateQuality()
        items[0].quality shouldBe 12
    }


    @Test
    fun `Backstage passes value increase by 3 when there are less or equal 5 days left`() {
        val items = arrayOf(Item(name="Backstage passes to a TAFKAL80ETC concert", sellIn = 5, quality = 10))
        val sut = GildedRose(items)
        sut.updateQuality()
        items[0].quality shouldBe 13
    }

    @Test
    fun `Backstage passes value is zero after concert`() {
        val items = arrayOf(Item(name="Backstage passes to a TAFKAL80ETC concert", sellIn = 0, quality = 10))
        val sut = GildedRose(items)
        sut.updateQuality()
        items[0].quality shouldBe 0
    }

    @Test
    @Disabled
    fun `conjured items decrease in quality by 2 per update`() {
        val items = arrayOf(Item(name="Conjured georg", sellIn = 1, quality = 10))
        val sut = GildedRose(items)
        sut.updateQuality()
        items[0].quality shouldBe 8
    }
}