package tdd.kata;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class GildedRoseTest {

	@Test
	@Disabled
	void foo() {
		Item[] items = new Item[] { new Item("foo", 0, 0) };
		GildedRose app = new GildedRose(items);
		app.updateQuality();
		assertEquals("foo", app.items[0].name);
	}

	@Test
	void should_discrease_quality_by_one_on_common_item() {
		Item[] items = new Item[]{
				new Item("Common Item", 10, 20)
		};
		var app = new GildedRose(items);
		app.updateQuality();
		assertThat(app.items[0]).hasFieldOrPropertyWithValue("quality", 19);
	}

	@Test
	void should_discrease_quality_and_sellIn_by_one_on_common_item() {
		Item[] items = new Item[]{
				new Item("Common Item", 10, 20)
		};
		var app = new GildedRose(items);
		app.updateQuality();
		assertThat(app.items[0])
				.hasFieldOrPropertyWithValue("quality", 19)
				.hasFieldOrPropertyWithValue("sellIn",9);
	}

	@Test
	void should_only_discrease_sellin_when_no_quality_on_common_item() {
		Item[] items = new Item[]{
				new Item("Common Item", 10, 0)
		};
		var app = new GildedRose(items);
		app.updateQuality();
		assertThat(app.items[0])
				.hasFieldOrPropertyWithValue("quality", 0)
				.hasFieldOrPropertyWithValue("sellIn",9);
	}


	@Test
	void should_preserve_sulfuras_item() {
		Item[] items = new Item[]{
				new Item("Sulfuras, Hand of Ragnaros", 10, 80)
		};
		var app = new GildedRose(items);
		app.updateQuality();
		assertThat(app.items[0])
				.hasFieldOrPropertyWithValue("quality", 80)
				.hasFieldOrPropertyWithValue("sellIn",10);
	}


	@Test
	void should_increase_quality_on_aged_brie_by_one_and_discrease_sellIn_by_one() {
		Item[] items = new Item[]{
				new Item("Aged Brie", 10, 20)
		};
		var app = new GildedRose(items);
		app.updateQuality();
		assertThat(app.items[0])
				.hasFieldOrPropertyWithValue("quality", 21)
				.hasFieldOrPropertyWithValue("sellIn",9);
	}


	@Test
	void should_discrease_sellIn_by_one_and_limit_quality_to_50_on_aged_brie() {
		Item[] items = new Item[]{
				new Item("Aged Brie", 10, 50)
		};
		var app = new GildedRose(items);
		app.updateQuality();
		assertThat(app.items[0])
				.hasFieldOrPropertyWithValue("quality", 50)
				.hasFieldOrPropertyWithValue("sellIn",9);
	}


	@Test
	void should_increase_quality_by_two_when_sellIn_is_less_than_eleven_and_discrease_sellIn_by_one_on_backstage() {
		Item[] items = new Item[]{
				new Item("Backstage passes to a TAFKAL80ETC concert", 10, 48)
		};
		var app = new GildedRose(items);
		app.updateQuality();
		assertThat(app.items[0])
				.hasFieldOrPropertyWithValue("quality", 50)
				.hasFieldOrPropertyWithValue("sellIn",9);
	}


	@ParameterizedTest
	@ValueSource(ints = {11,12,13} )
	void should_increase_quality_by_one_when_sellIn_is_higher_than_ten_and_discrease_sellIn_by_one_on_backstage(int givenSellin) {
		Item[] items = new Item[]{
				new Item("Backstage passes to a TAFKAL80ETC concert", givenSellin, 48)
		};
		var app = new GildedRose(items);
		app.updateQuality();
		assertThat(app.items[0])
				.hasFieldOrPropertyWithValue("quality", 49)
				.hasFieldOrPropertyWithValue("sellIn",givenSellin-1);
	}


	@Test
	void should_increase_quality_by_three_when_sellIn_is_lower_than_six_than_ten_and_discrease_sellIn_by_one_on_backstage() {
		Item[] items = new Item[]{
				new Item("Backstage passes to a TAFKAL80ETC concert", 5, 46)
		};
		var app = new GildedRose(items);
		app.updateQuality();
		assertThat(app.items[0])
				.hasFieldOrPropertyWithValue("quality", 49)
				.hasFieldOrPropertyWithValue("sellIn",4);
	}


	@Test
	void should_increase_until_50_on_backstage_when_quality_is_49() {
		Item[] items = new Item[]{
				new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49)
		};
		var app = new GildedRose(items);
		app.updateQuality();
		assertThat(app.items[0])
				.hasFieldOrPropertyWithValue("quality", 50)
				.hasFieldOrPropertyWithValue("sellIn",9);
	}

	@Test
	void should_increase_until_50_on_backstage_when_quality_is_48() {
		Item[] items = new Item[]{
				new Item("Backstage passes to a TAFKAL80ETC concert", 5, 48)
		};
		var app = new GildedRose(items);
		app.updateQuality();
		assertThat(app.items[0])
				.hasFieldOrPropertyWithValue("quality", 50)
				.hasFieldOrPropertyWithValue("sellIn",4);
	}
}