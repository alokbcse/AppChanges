package com.dmbteam.catalogapp.cmn;

import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

import android.content.Context;

/**
 * The Class Product.
 */
public class Product implements Comparable<Product> {

	/** The id. */
	@Attribute(required = true)
	private int id;

	/** The category. */
	@Attribute(required = true)
	private int category;

	/** The price. */
	@Attribute(required = true)
	private double price;

	/** The discount. */
	@Attribute(required = false)
	private double discount;

	/** The date. */
	@Element(required = true)
	private String date;

	/** The title. */
	@Element(required = true)
	private String title;

	/** The photos. */
	@ElementList(required = false)
	private List<String> photos;

	/** The description. */
	@Element(required = false)
	private String description;

	/** The condition. */
	@Element(required = true)
	private String condition;

	/** The color. */
	@Element(required = true)
	private String color;

	/** The discounted price. */
	private double discountedPrice;

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Product anotherProduct) {
		return Integer.valueOf(id).compareTo(anotherProduct.id);
	}

	/**
	 * Gets the price.
	 *
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Gets the discount.
	 *
	 * @return the discount
	 */
	public double getDiscount() {
		return discount;
	}

	/**
	 * Gets the title.
	 *
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Gets the photo.
	 *
	 * @param context the context
	 * @return the photo
	 */
	public List<String> getPhotos(Context context) {
		return photos;
	}

	/**
	 * Gets the drawable id.
	 *
	 * @param context the context
	 * @return the drawable id
	 */
	public static int getDrawableId(Context context, String photo) {
		return context.getResources().getIdentifier(photo, "drawable",
				context.getPackageName());
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Checks if is network resource.
	 *
	 * @param resource the resource
	 * @return true, if is network resource
	 */
	public static boolean isNetworkResource(String resource) {

		return resource.startsWith("http");
	}


	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Gets the category.
	 *
	 * @return the category
	 */
	public int getCategory() {
		return category;
	}

	/**
	 * Gets the condition.
	 *
	 * @return the condition
	 */
	public String getCondition() {
		return condition;
	}

	/**
	 * Gets the color.
	 *
	 * @return the color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * Gets the discounted price.
	 *
	 * @return the discounted price
	 */
	public double getDiscountedPrice() {
		discountedPrice = getPrice() * (1 - getDiscount() / 100);

		return discountedPrice;
	}

}
