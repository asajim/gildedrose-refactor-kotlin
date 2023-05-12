# GildedRose Assignment

This project is a refactored version
of [the original one in GitHub](https://github.com/emilybache/GildedRose-Refactoring-Kata).
The requirement of this project can be found [here](./README.md)

## Refactor Details

* Original GildedRose and Item are not touched. This was done to ensure that any changes done on refactored code will
  behave similar as the original code (unless the conjured items).
* ImprovedItem was created as a better implementation of Item. In ImprovedItem, each item type has their own class which
  make implementing special rules for each item type easier to do.
* Each class in ImprovedItem are provided with its own test class.
* GildedRoseTest contains a function to check that the new improved implementation behaves the same as the original
  one (except for Conjure item).
* GitHub workflow is added to ensure each change pass the check.

## Assumption

* In requirement, it's not mentioned that "Aged Brie" will increase its value twice after the sellIn value less than
  zero. But turns the original code actually do that, thus I follow the original one approach.
