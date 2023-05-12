package com.gildedrose.item

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class BaseItemTest {
    @Test
    fun `quality decrease after quality is updated`() {
        val item = Item("Test item", 10, 10)
        val baseItem = item.toGildedRoseItem()

        assert(baseItem is ImprovedItem.Basic)

        // Day 1
        baseItem.updateQuality()
        assert(baseItem.quality == item.quality - 1)
        assert(baseItem.sellIn == item.sellIn - 1)

        // Day 2
        baseItem.updateQuality()
        assert(baseItem.quality == item.quality - 2)
        assert(baseItem.sellIn == item.sellIn - 2)
    }

    @Test
    fun `quality decrease twice faster when sellIn less than zero`() {
        val item = Item("Test item", 0, 10)
        val basicItem = item.toGildedRoseItem()

        assert(basicItem is ImprovedItem.Basic)

        // Day 1
        basicItem.updateQuality()
        assert(basicItem.quality == item.quality - 2)
        assert(basicItem.sellIn == item.sellIn - 1)

        // Day 2
        basicItem.updateQuality()
        assert(basicItem.quality == item.quality - 4)
        assert(basicItem.sellIn == item.sellIn - 2)
    }

    @Test
    fun `quality can never be less than zero after quality is updated`() {
        val item = Item("Test item", 0, 0)
        val basicItem = item.toGildedRoseItem()

        assert(basicItem is ImprovedItem.Basic)

        // Day 1
        basicItem.updateQuality()
        assert(basicItem.quality == 0)
        assert(basicItem.sellIn == item.sellIn - 1)
    }

    @Test
    fun `initialized quality can never be more than 50`() {
        val item = Item(ImprovedItem.SULFURAS_NAME, 10, 220)
        assertThrows<AssertionError> {
            item.toGildedRoseItem()
        }
    }

    @Test
    fun `initialized quality can never be less than 0`() {
        val item = Item(ImprovedItem.SULFURAS_NAME, 10, -1)
        assertThrows<AssertionError> {
            item.toGildedRoseItem()
        }
    }
}