package com.gildedrose

import com.gildedrose.item.Item
import com.gildedrose.item.toGildedRoseItem

/**
 * Improved version of [GildedRose]
 */
class ImprovedGildedRose(items: List<Item>) {

    val items = items.map { item -> item.toGildedRoseItem() }

    fun updateQuality() {
        items.forEach {
            it.updateQuality()
        }
    }
}

