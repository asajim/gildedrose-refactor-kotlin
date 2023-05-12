package com.gildedrose

import com.gildedrose.item.Item
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class GildedRoseTest {

    @Test
    fun `Result of ImprovedGildedRose is equal to oldGildedRose when updating basic items`() {
        val items = listOf(
            Item("+5 Dexterity Vest", 10, 20), //
            Item("Aged Brie", 2, 0), //
            Item("Elixir of the Mongoose", 5, 7), //
            Item("Sulfuras, Hand of Ragnaros", 0, 80), //
            Item("Sulfuras, Hand of Ragnaros", -1, 80),
            Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
            Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
            Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
            // this conjured item does not work properly yet
            Item("Conjured Mana Cake", 3, 6)
        )

        val gildedRose = GildedRose(items)
        val improvedGildedRose = ImprovedGildedRose(items)

        repeat((0..100).count()) {
            // Since item should not be overridden, we have to compare them using their representation
            gildedRose.items.forEachIndexed { index, item ->
                assertEquals(
                    item.toString(),
                    improvedGildedRose.items[index].toItem().toString()
                )
            }

            gildedRose.updateQuality()
            improvedGildedRose.updateQuality()
        }
    }


}


