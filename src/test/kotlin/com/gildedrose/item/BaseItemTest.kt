package com.gildedrose.item

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class BaseItemTest {
    @Test
    fun `quality decrease after quality is updated`() {
        val item = Item("Test item", 10, 10)
        val baseItem = item.toGildedRoseItem()

        assertTrue(baseItem is ImprovedItem.Basic)

        // Day 1
        baseItem.updateQuality()
        assertEquals(baseItem.quality, item.quality - 1)
        assertEquals(baseItem.sellIn, item.sellIn - 1)

        // Day 2
        baseItem.updateQuality()
        assertEquals(baseItem.quality, item.quality - 2)
        assertEquals(baseItem.sellIn, item.sellIn - 2)
    }

    @Test
    fun `quality decrease twice faster when sellIn less than zero`() {
        val item = Item("Test item", 0, 10)
        val basicItem = item.toGildedRoseItem()

        assertTrue(basicItem is ImprovedItem.Basic)

        // Day 1
        basicItem.updateQuality()
        assertEquals(basicItem.quality, item.quality - 2)
        assertEquals(basicItem.sellIn, item.sellIn - 1)

        // Day 2
        basicItem.updateQuality()
        assertEquals(basicItem.quality, item.quality - 4)
        assertEquals(basicItem.sellIn, item.sellIn - 2)
    }

    @Test
    fun `quality can never be less than zero after quality is updated`() {
        val item = Item("Test item", 0, 0)
        val basicItem = item.toGildedRoseItem()

        assertTrue(basicItem is ImprovedItem.Basic)

        // Day 1
        basicItem.updateQuality()
        assertEquals(basicItem.quality, ImprovedItem.MIN_ITEM_QUALITY)
        assertEquals(basicItem.sellIn, item.sellIn - 1)
    }

    @Test
    fun `initialized quality can never be more than 50`() {
        val item = Item("Test item", 10, 220)
        assertThrows<AssertionError> {
            item.toGildedRoseItem()
        }
    }

    @Test
    fun `initialized quality can never be less than 0`() {
        val item = Item("Test item", 10, -1)
        assertThrows<AssertionError> {
            item.toGildedRoseItem()
        }
    }
}