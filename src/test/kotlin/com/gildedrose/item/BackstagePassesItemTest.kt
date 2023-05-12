package com.gildedrose.item

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class BackstagePassesItemTest {
    @Test
    fun `quality increase after quality is updated and sellIn is more than 10`() {
        val item = Item(ImprovedItem.BACKSTAGE_PASSES_NAME, 100, 10)
        val baseItem = item.toGildedRoseItem()

        assertTrue(baseItem is ImprovedItem.BackstagePassesItem)

        // Day 1
        baseItem.updateQuality()
        assertEquals(baseItem.quality, item.quality + 1)
        assertEquals(baseItem.sellIn, item.sellIn - 1)

        // Day 2
        baseItem.updateQuality()
        assertEquals(baseItem.quality, item.quality + 2)
        assertEquals(baseItem.sellIn, item.sellIn - 2)
    }

    @Test
    fun `quality increase twice faster after quality is updated and sellIn is between 6 and 10`() {
        val item = Item(ImprovedItem.BACKSTAGE_PASSES_NAME, 10, 10)
        val baseItem = item.toGildedRoseItem()

        assertTrue(baseItem is ImprovedItem.BackstagePassesItem)

        // Day 1
        baseItem.updateQuality()
        assertEquals(baseItem.quality, item.quality + 2)
        assertEquals(baseItem.sellIn, item.sellIn - 1)

        // Day 2
        baseItem.updateQuality()
        assertEquals(baseItem.quality, item.quality + 4)
        assertEquals(baseItem.sellIn, item.sellIn - 2)
    }

    @Test
    fun `quality increase thrice faster after quality is updated and sellIn is between 0 and 5`() {
        val item = Item(ImprovedItem.BACKSTAGE_PASSES_NAME, 5, 10)
        val baseItem = item.toGildedRoseItem()

        assertTrue(baseItem is ImprovedItem.BackstagePassesItem)

        // Day 1
        baseItem.updateQuality()
        assertEquals(baseItem.quality, item.quality + 3)
        assertEquals(baseItem.sellIn, item.sellIn - 1)

        // Day 2
        baseItem.updateQuality()
        assertEquals(baseItem.quality, item.quality + 6)
        assertEquals(baseItem.sellIn, item.sellIn - 2)
    }

    @Test
    fun `quality drops to 0 when sellIn is minus`() {
        val item = Item(ImprovedItem.BACKSTAGE_PASSES_NAME, 0, 10)
        val baseItem = item.toGildedRoseItem()

        assertTrue(baseItem is ImprovedItem.BackstagePassesItem)

        // Day 1
        baseItem.updateQuality()
        assertEquals(baseItem.quality, ImprovedItem.MIN_ITEM_QUALITY)
        assertEquals(baseItem.sellIn, item.sellIn - 1)
    }

    @Test
    fun `quality can never be less than zero after quality is updated and sellIn less than 0`() {
        val item = Item(ImprovedItem.BACKSTAGE_PASSES_NAME, 0, 0)
        val basicItem = item.toGildedRoseItem()

        assertTrue(basicItem is ImprovedItem.BackstagePassesItem)

        // Day 1
        basicItem.updateQuality()
        assertEquals(basicItem.quality, ImprovedItem.MIN_ITEM_QUALITY)
        assertEquals(basicItem.sellIn, item.sellIn - 1)
    }

    @Test
    fun `quality can never be more than 50 after quality is updated`() {
        val item = Item(ImprovedItem.BACKSTAGE_PASSES_NAME, 1, 49)
        val basicItem = item.toGildedRoseItem()

        assertTrue(basicItem is ImprovedItem.BackstagePassesItem)

        // Day 1
        basicItem.updateQuality()
        assertEquals(basicItem.quality, ImprovedItem.MAX_ITEM_QUALITY)
        assertEquals(basicItem.sellIn, item.sellIn - 1)
    }
}