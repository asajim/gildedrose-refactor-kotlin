package com.gildedrose.item

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class AgedBrieItemTest {
    @Test
    fun `quality increase after quality is updated`() {
        val item = Item(ImprovedItem.AGED_BRIE_NAME, 10, 10)
        val agedBrieItem = item.toGildedRoseItem()

        assertTrue(agedBrieItem is ImprovedItem.AgedBrieItem)

        // Day 1
        agedBrieItem.updateQuality()
        assertEquals(agedBrieItem.quality, item.quality + 1)
        assertEquals(agedBrieItem.sellIn, item.sellIn - 1)

        // Day 2
        agedBrieItem.updateQuality()
        assertEquals(agedBrieItem.quality, item.quality + 2)
        assertEquals(agedBrieItem.sellIn, item.sellIn - 2)
    }

    @Test
    fun `quality increase twice faster after quality is updated when sellIn less than zero`() {
        val item = Item(ImprovedItem.AGED_BRIE_NAME, 0, 10)
        val agedBrieItem = item.toGildedRoseItem()

        assertTrue(agedBrieItem is ImprovedItem.AgedBrieItem)

        // Day 1
        agedBrieItem.updateQuality()
        assertEquals(agedBrieItem.quality, item.quality + 2)
        assertEquals(agedBrieItem.sellIn, item.sellIn - 1)

        // Day 2
        agedBrieItem.updateQuality()
        assertEquals(agedBrieItem.quality, item.quality + 4)
        assertEquals(agedBrieItem.sellIn, item.sellIn - 2)
    }

    @Test
    fun `quality can never be more than 50 after quality is updated`() {
        val item = Item(ImprovedItem.AGED_BRIE_NAME, 10, 49)
        val agedBrieItem = item.toGildedRoseItem()

        assertTrue(agedBrieItem is ImprovedItem.AgedBrieItem)

        // Day 1
        agedBrieItem.updateQuality()
        assertEquals(agedBrieItem.quality, ImprovedItem.MAX_ITEM_QUALITY)
        assertEquals(agedBrieItem.sellIn, item.sellIn - 1)

        // Day 2
        agedBrieItem.updateQuality()
        assertEquals(agedBrieItem.quality, ImprovedItem.MAX_ITEM_QUALITY)
        assertEquals(agedBrieItem.sellIn, item.sellIn - 2)
    }
}