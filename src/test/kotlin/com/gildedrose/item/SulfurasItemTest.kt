package com.gildedrose.item

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class SulfurasItemTest {
    @Test
    fun `quality and sellin stay when day changes`() {
        val item = Item(ImprovedItem.SULFURAS_NAME, 10, ImprovedItem.SULFURAS_ITEM_QUALITY)
        val baseItem = item.toGildedRoseItem()

        assert(baseItem is ImprovedItem.Sulfuras)

        // Day 1
        baseItem.updateQuality()
        assert(baseItem.quality == item.quality)
        assert(baseItem.sellIn == item.sellIn)

        // Day 2
        baseItem.updateQuality()
        assert(baseItem.quality == item.quality)
        assert(baseItem.sellIn == item.sellIn)
    }

    @Test
    fun `throw error when value is not 80`() {
        val item = Item(ImprovedItem.SULFURAS_NAME, 10, 10)
        assertThrows<AssertionError> {
            item.toGildedRoseItem()
        }
    }
}