package com.gildedrose.item

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class ConjureItemTest {
    @Test
    fun `quality decrease twice faster after quality is updated`() {
        val item = Item(ImprovedItem.CONJURED_NAME, 10, 10)
        val conjureItem = item.toGildedRoseItem()

        assertTrue(conjureItem is ImprovedItem.Conjure)

        // Day 1
        conjureItem.updateQuality()
        assertEquals(conjureItem.quality, item.quality - 2)
        assertEquals(conjureItem.sellIn, item.sellIn - 1)

        // Day 2
        conjureItem.updateQuality()
        assertEquals(conjureItem.quality, item.quality - 4)
        assertEquals(conjureItem.sellIn, item.sellIn - 2)
    }

    @Test
    fun `quality decrease 4 times faster after quality is updated and sellIn less than zero`() {
        val item = Item(ImprovedItem.CONJURED_NAME, 0, 10)
        val conjureItem = item.toGildedRoseItem()

        assertTrue(conjureItem is ImprovedItem.Conjure)

        // Day 1
        conjureItem.updateQuality()
        assertEquals(conjureItem.quality, item.quality - 4)
        assertEquals(conjureItem.sellIn, item.sellIn - 1)

        // Day 2
        conjureItem.updateQuality()
        assertEquals(conjureItem.quality, item.quality - 8)
        assertEquals(conjureItem.sellIn, item.sellIn - 2)
    }

    @Test
    fun `quality can never be less than zero after quality is updated`() {
        val item = Item(ImprovedItem.CONJURED_NAME, 0, 0)
        val conjureItem = item.toGildedRoseItem()

        assertTrue(conjureItem is ImprovedItem.Conjure)

        // Day 1
        conjureItem.updateQuality()
        assertEquals(conjureItem.quality, ImprovedItem.MIN_ITEM_QUALITY)
        assertEquals(conjureItem.sellIn, item.sellIn - 1)
    }
}