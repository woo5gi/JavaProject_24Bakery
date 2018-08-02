package com.kitri.bakery.domain;

public class BakeryBean {
	public static String member_id = "";
	public static String serchMember = "gungyea";
	private String product_name;
	private String product_epl;
	private String order_date;
	private int product_id;
	private int price;
	private int allprice;
	private int su;
	private int order_id;

	public String getOrder_date() {
		return order_date;
	}

	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getProduct_epl() {
		return product_epl;
	}

	public void setProduct_epl(String product_epl) {
		this.product_epl = product_epl;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getAllprice() {
		return allprice;
	}

	public void setAllprice(int allprice) {
		this.allprice = allprice;
	}

	public int getSu() {
		return su;
	}

	public void setSu(int su) {
		this.su = su;
	}

}
