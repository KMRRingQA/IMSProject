package com.qa.ims.persistence.dao;

public class CustomerDaoMysqlTest {

	public void createTest() {

	}
}

//@Test
//public void createTest() {
//	String name = "Minecraft";
//	String price = "14.99";
//	String stock = "100";
//
//	Mockito.doReturn(name, price, stock).when(itemController).getInput();
//	BigDecimal priceConverted = BigDecimal.valueOf(Double.parseDouble(price));
//	Long stockConverted = Long.parseLong(stock);
//	Item item = new Item(name, priceConverted, stockConverted);
//	Item savedItem = new Item(1L, "Minecraft", BigDecimal.valueOf(14.99), 100L);
//	Mockito.when(itemServices.create(item)).thenReturn(savedItem);
//	assertEquals(savedItem, itemController.create());
//}