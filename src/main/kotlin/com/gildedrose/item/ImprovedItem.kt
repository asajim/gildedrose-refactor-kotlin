package com.gildedrose.item

import kotlin.math.max
import kotlin.math.min

/**
 * Wrapper for [Item] because item is not allowed to be modified
 */
sealed class ImprovedItem(
    item: Item
) {
    /**
     * Name of item
     */
    val name: String = item.name

    /**
     * Number of days until the item has to be sold
     */
    var sellIn: Int = item.sellIn
        protected set

    /**
     * Indicate how valuable the item is (i.e. similar with price).
     * It should never be negative and never more than 50
     */
    var quality: Int = item.quality
        protected set

    init {
        assert(quality >= MIN_ITEM_QUALITY) { "Quality cannot be negative" }
        if (!name.startsWith(SULFURAS_NAME)) {
            assert(quality <= MAX_ITEM_QUALITY) { "Quality cannot be more than $MAX_ITEM_QUALITY except $SULFURAS_ITEM_QUALITY" }
        }
    }

    /**
     * Update quality of the item. It should be called when a day has passed
     */
    abstract fun updateQuality()

    override fun toString(): String {
        return "${this.name}, ${this.sellIn}, ${this.quality}"
    }

    /**
     * Convert rose-item to item
     */
    fun toItem(): Item {
        return Item(name, sellIn, quality)
    }

    class Basic(item: Item) : ImprovedItem(item) {
        override fun updateQuality() {
            sellIn -= 1
            // Once the sell by date has passed, Quality degrades twice as fast
            val newQuality = if (sellIn >= 0) quality - 1 else quality - 2
            // The quality of an item is never negative
            quality = max(0, newQuality)
        }
    }

    class AgedBrieItem(item: Item) : ImprovedItem(item) {
        override fun updateQuality() {
            sellIn -= 1
            // "Aged Brie" actually increases in Quality the older it gets
            val newQuality = quality + if (sellIn < 0) 2 else 1
            // The Quality of an item is never more than 50
            quality = min(newQuality, MAX_ITEM_QUALITY)
        }
    }

    class Sulfuras(item: Item) : ImprovedItem(item) {
        init {
            assert(item.quality == SULFURAS_ITEM_QUALITY) { "Sulfuras has to have quality of $SULFURAS_ITEM_QUALITY" }
        }

        override fun updateQuality() {
            // "Sulfuras", being a legendary item, never has to be sold or decreases in Quality
        }
    }

    class BackstagePassesItem(item: Item) : ImprovedItem(item) {
        override fun updateQuality() {
            sellIn -= 1
            val newQuality = when {
                // Quality drops to 0 after the concert
                sellIn < 0 -> 0
                // Quality increases by 3 when there are 5 days or less
                sellIn < 5 -> quality + 3
                // Quality increases by 2 when there are 10 days or less
                sellIn < 10 -> quality + 2
                // Otherwise, increase by 1 on each day
                else -> quality + 1
            }
            quality = min(MAX_ITEM_QUALITY, newQuality)
        }
    }

    companion object {
        const val AGED_BRIE_NAME = "Aged Brie"
        const val SULFURAS_NAME = "Sulfuras"
        const val BACKSTAGE_PASSES_NAME = "Backstage passes"
        const val MIN_ITEM_QUALITY = 0
        const val MAX_ITEM_QUALITY = 50
        const val SULFURAS_ITEM_QUALITY = 80
    }
}

fun Item.toGildedRoseItem(): ImprovedItem {
    return when {
        name.startsWith(ImprovedItem.AGED_BRIE_NAME) -> ImprovedItem.AgedBrieItem(this)
        name.startsWith(ImprovedItem.SULFURAS_NAME) -> ImprovedItem.Sulfuras(this)
        name.startsWith(ImprovedItem.BACKSTAGE_PASSES_NAME) -> ImprovedItem.BackstagePassesItem(this)
        else -> ImprovedItem.Basic(this)
    }
}