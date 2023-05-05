package kata.gildedrose

class GildedRose(var items: Array<Item>) {

    fun updateQuality() {
        for (i in items.indices) {
            updateItem(items[i])
        }
    }

    fun updateItem(item: Item) {
        when (item.name) {
            "Aged Brie" -> item.increaseQuality(1)
            "Backstage passes to a TAFKAL80ETC concert" -> {}
            else -> item.decreaseQuality(1)
        }
        if (item.name == "Backstage passes to a TAFKAL80ETC concert") {
            if (item.quality < 50) {
                item.increaseQuality(1)

                if (item.name == "Backstage passes to a TAFKAL80ETC concert") {
                    if (item.sellIn < 11) {
                        item.increaseQuality(1)
                    }

                    if (item.sellIn < 6) {
                        item.increaseQuality(1)
                    }
                }
            }
        }

        item.updateSellIn()

        if (item.sellIn < 0) {
            if (item.name != "Aged Brie") {
                if (item.name != "Backstage passes to a TAFKAL80ETC concert") {
                    item.decreaseQuality(1)
                } else {
                    item.updateQuality(-item.quality)
                }
            } else {
                item.increaseQuality(1)
            }
        }
    }

    private fun Item.updateSellIn() {
        if (name != "Sulfuras, Hand of Ragnaros") {
            sellIn -= 1
        }
    }

    private fun Item.updateQuality(amount: Int) {
        if (name == "Sulfuras, Hand of Ragnaros") return
        this.quality = kotlin.math.min(50, kotlin.math.max(this.quality + amount, 0))
   }

    private fun Item.increaseQuality(amount: Int) = this.updateQuality(amount)

    private fun Item.decreaseQuality(amount: Int) = this.updateQuality(-amount)
}