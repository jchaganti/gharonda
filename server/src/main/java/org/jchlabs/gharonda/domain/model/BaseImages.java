package org.jchlabs.gharonda.domain.model;

import java.io.Serializable;

/**
 * This is an object that contains data related to the images table. Do not modify this class because it will be
 * overwritten if the configuration file related to this class is modified.
 * 
 * @hibernate.class table="images"
 */

public abstract class BaseImages implements Serializable {

	public static String REF = "Images";
	public static String PROP_IMAGE4 = "Image4";
	public static String PROP_IMAGE3 = "Image3";
	public static String PROP_IMAGE2 = "Image2";
	public static String PROP_IMAGE6 = "Image6";
	public static String PROP_IMAGE10 = "Image10";
	public static String PROP_IMAGE8 = "Image8";
	public static String PROP_IMAGE5 = "Image5";
	public static String PROP_IMAGE7 = "Image7";
	public static String PROP_IMAGE1 = "Image1";
	public static String PROP_ID = "Id";
	public static String PROP_IMAGE9 = "Image9";

	// constructors
	public BaseImages() {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseImages(java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	protected void initialize() {
	}

	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String image1;
	private java.lang.String image10;
	private java.lang.String image2;
	private java.lang.String image3;
	private java.lang.String image4;
	private java.lang.String image5;
	private java.lang.String image6;
	private java.lang.String image7;
	private java.lang.String image8;
	private java.lang.String image9;

	/**
	 * Return the unique identifier of this class
	 * @hibernate.id generator-class="increment" column="id"
	 */
	public java.lang.Integer getId() {
		return id;
	}

	/**
	 * Set the unique identifier of this class
	 * @param id the new ID
	 */
	public void setId(java.lang.Integer id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}

	/**
	 * Return the value associated with the column: image1
	 */
	public java.lang.String getImage1() {
		return image1;
	}

	/**
	 * Set the value related to the column: image1
	 * @param image1 the image1 value
	 */
	public void setImage1(java.lang.String image1) {
		this.image1 = image1;
	}

	/**
	 * Return the value associated with the column: image10
	 */
	public java.lang.String getImage10() {
		return image10;
	}

	/**
	 * Set the value related to the column: image10
	 * @param image10 the image10 value
	 */
	public void setImage10(java.lang.String image10) {
		this.image10 = image10;
	}

	/**
	 * Return the value associated with the column: image2
	 */
	public java.lang.String getImage2() {
		return image2;
	}

	/**
	 * Set the value related to the column: image2
	 * @param image2 the image2 value
	 */
	public void setImage2(java.lang.String image2) {
		this.image2 = image2;
	}

	/**
	 * Return the value associated with the column: image3
	 */
	public java.lang.String getImage3() {
		return image3;
	}

	/**
	 * Set the value related to the column: image3
	 * @param image3 the image3 value
	 */
	public void setImage3(java.lang.String image3) {
		this.image3 = image3;
	}

	/**
	 * Return the value associated with the column: image4
	 */
	public java.lang.String getImage4() {
		return image4;
	}

	/**
	 * Set the value related to the column: image4
	 * @param image4 the image4 value
	 */
	public void setImage4(java.lang.String image4) {
		this.image4 = image4;
	}

	/**
	 * Return the value associated with the column: image5
	 */
	public java.lang.String getImage5() {
		return image5;
	}

	/**
	 * Set the value related to the column: image5
	 * @param image5 the image5 value
	 */
	public void setImage5(java.lang.String image5) {
		this.image5 = image5;
	}

	/**
	 * Return the value associated with the column: image6
	 */
	public java.lang.String getImage6() {
		return image6;
	}

	/**
	 * Set the value related to the column: image6
	 * @param image6 the image6 value
	 */
	public void setImage6(java.lang.String image6) {
		this.image6 = image6;
	}

	/**
	 * Return the value associated with the column: image7
	 */
	public java.lang.String getImage7() {
		return image7;
	}

	/**
	 * Set the value related to the column: image7
	 * @param image7 the image7 value
	 */
	public void setImage7(java.lang.String image7) {
		this.image7 = image7;
	}

	/**
	 * Return the value associated with the column: image8
	 */
	public java.lang.String getImage8() {
		return image8;
	}

	/**
	 * Set the value related to the column: image8
	 * @param image8 the image8 value
	 */
	public void setImage8(java.lang.String image8) {
		this.image8 = image8;
	}

	/**
	 * Return the value associated with the column: image9
	 */
	public java.lang.String getImage9() {
		return image9;
	}

	/**
	 * Set the value related to the column: image9
	 * @param image9 the image9 value
	 */
	public void setImage9(java.lang.String image9) {
		this.image9 = image9;
	}

	public boolean equals(Object obj) {
		if (null == obj)
			return false;
		if (!(obj instanceof org.jchlabs.gharonda.domain.model.Images))
			return false;
		else {
			org.jchlabs.gharonda.domain.model.Images images = (org.jchlabs.gharonda.domain.model.Images) obj;
			if (null == this.getId() || null == images.getId())
				return false;
			else
				return (this.getId().equals(images.getId()));
		}
	}

	public int hashCode() {
		if (Integer.MIN_VALUE == this.hashCode) {
			if (null == this.getId())
				return super.hashCode();
			else {
				String hashStr = this.getClass().getName() + ":" + this.getId().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return this.hashCode;
	}

	public String toString() {
		return super.toString();
	}

}