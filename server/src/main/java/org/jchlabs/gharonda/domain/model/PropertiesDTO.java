package org.jchlabs.gharonda.domain.model;

import java.io.Serializable;
import java.util.Set;

public class PropertiesDTO implements Serializable {

	private int hashCode = Integer.MIN_VALUE;
	private java.lang.Integer id;

	public java.lang.Integer getId() {
		return id;
	}

	public void setId(java.lang.Integer id) {
		this.id = id;
	}

	public PropertiesDTO(String addrNum, Integer amenity1, Integer amenity10, Integer amenity11, Integer amenity12,
			Integer amenity13, Integer amenity14, Integer amenity2, Integer amenity3, Integer amenity4,
			Integer amenity5, Integer amenity6, Integer amenity7, Integer amenity8, Integer amenity9,
			Integer bathRooms, Integer bedRooms, String buildDate, String city, Set<Contentitems> contentitems,
			Integer currency, String description, int hashCode, Integer id, Integer isBargain, Integer isEBook,
			Integer isFeatured, Integer isYardSign, Integer isactive, Double lat, Integer livingRooms, Double lng,
			Integer floor, String name, Integer otherSideId, Integer type, Integer mode, Integer planType,
			String postCode, Integer price, Integer homeLoan, Integer reservedInt, String reservedStr, Integer sqrft,
			String state, String streetName, String suburb, Long created, Long timeStamp, String title, Users user,
			Integer view, Integer wipState, String amenities) {
		super();
		this.addrNum = addrNum;
		this.amenity1 = amenity1;
		this.amenity10 = amenity10;
		this.amenity11 = amenity11;
		this.amenity12 = amenity12;
		this.amenity13 = amenity13;
		this.amenity14 = amenity14;
		this.amenity2 = amenity2;
		this.amenity3 = amenity3;
		this.amenity4 = amenity4;
		this.amenity5 = amenity5;
		this.amenity6 = amenity6;
		this.amenity7 = amenity7;
		this.amenity8 = amenity8;
		this.amenity9 = amenity9;
		this.bathRooms = bathRooms;
		this.bedRooms = bedRooms;
		this.buildDate = buildDate;
		this.city = city;
		this.contentitems = contentitems;
		this.currency = currency;
		this.description = description;
		this.hashCode = hashCode;
		this.id = id;
		this.isBargain = isBargain;
		this.isEBook = isEBook;
		this.isFeatured = isFeatured;
		this.isYardSign = isYardSign;
		this.isactive = isactive;
		this.lat = lat;
		this.heat = livingRooms;
		this.lng = lng;
		this.floor = floor;
		this.name = name;
		this.otherSideId = otherSideId;
		this.pType = type;
		this.pMode = mode;
		this.planType = planType;
		this.postCode = postCode;
		this.price = price;
		this.homeLoan = homeLoan;
		this.reservedInt = reservedInt;
		this.reservedStr = reservedStr;
		this.sqrft = sqrft;
		this.state = state;
		this.streetName = streetName;
		this.suburb = suburb;
		this.timeStamp = timeStamp;
		this.created = created;
		this.title = title;
		this.user = user;
		this.view = view;
		this.wipState = wipState;
		this.amenities = amenities;
	}

	private java.lang.Double lat;
	private java.lang.Double lng;
	private java.lang.Integer pType;
	private java.lang.Integer pMode;
	private java.lang.String addrNum;
	private java.lang.String streetName;
	private java.lang.String suburb;
	private java.lang.String city;
	private java.lang.String postCode;
	private java.lang.String state;
	private java.lang.String title;
	private java.lang.Integer bedRooms;
	private java.lang.Integer heat;
	private java.lang.Integer bathRooms;
	private java.lang.Integer view;
	private java.lang.String buildDate;
	private java.lang.Integer floor;
	private java.lang.Integer homeLoan;
	private java.lang.String description;
	private java.lang.Integer price;
	private java.lang.Integer sqrft;
	private java.lang.Integer currency;
	private java.lang.Integer isactive;
	private java.lang.Integer planType;
	private java.lang.Integer isFeatured;
	private java.lang.Integer isBargain;
	private java.lang.Integer isYardSign;
	private java.lang.Integer isEBook;
	private java.lang.Integer amenity1;
	private java.lang.Integer amenity2;
	private java.lang.Integer amenity3;
	private java.lang.Integer amenity4;
	private java.lang.Integer amenity5;
	private java.lang.Integer amenity6;
	private java.lang.Integer amenity7;
	private java.lang.Integer amenity8;
	private java.lang.Integer amenity9;
	private java.lang.Integer amenity10;
	private java.lang.Integer amenity11;
	private java.lang.Integer amenity12;
	private java.lang.Integer amenity13;
	private java.lang.Integer amenity14;
	private java.lang.String amenities;
	private java.lang.Integer reservedInt;
	private java.lang.String reservedStr;
	private java.lang.Long timeStamp;
	private java.lang.Long created;
	private java.lang.String name;
	private java.lang.Integer wipState;
	private java.lang.Integer otherSideId;
	private org.jchlabs.gharonda.domain.model.Users user;

	// collections
	private java.util.Set<org.jchlabs.gharonda.domain.model.Contentitems> contentitems;

	public PropertiesDTO() {
		super();
	}

	public java.lang.Double getLat() {
		return lat;
	}

	public void setLat(java.lang.Double lat) {
		this.lat = lat;
	}

	public java.lang.Double getLng() {
		return lng;
	}

	public void setLng(java.lang.Double lng) {
		this.lng = lng;
	}

	public java.lang.Integer getPType() {
		return pType;
	}

	public void setPType(java.lang.Integer type) {
		pType = type;
	}

	public java.lang.String getAddrNum() {
		return addrNum;
	}

	public void setAddrNum(java.lang.String addrNum) {
		this.addrNum = addrNum;
	}

	public java.lang.String getStreetName() {
		return streetName;
	}

	public void setStreetName(java.lang.String streetName) {
		this.streetName = streetName;
	}

	public java.lang.String getSuburb() {
		return suburb;
	}

	public void setSuburb(java.lang.String suburb) {
		this.suburb = suburb;
	}

	public java.lang.String getCity() {
		return city;
	}

	public void setCity(java.lang.String city) {
		this.city = city;
	}

	public java.lang.String getPostCode() {
		return postCode;
	}

	public void setPostCode(java.lang.String postCode) {
		this.postCode = postCode;
	}

	public java.lang.String getState() {
		return state;
	}

	public void setState(java.lang.String state) {
		this.state = state;
	}

	public java.lang.String getTitle() {
		return title;
	}

	public void setTitle(java.lang.String title) {
		this.title = title;
	}

	public java.lang.Integer getBedRooms() {
		return bedRooms;
	}

	public void setBedRooms(java.lang.Integer bedRooms) {
		this.bedRooms = bedRooms;
	}

	public java.lang.Integer getHeat() {
		return heat;
	}

	public void setHeat(java.lang.Integer heat) {
		this.heat = heat;
	}

	public java.lang.Integer getBathRooms() {
		return bathRooms;
	}

	public void setBathRooms(java.lang.Integer bathRooms) {
		this.bathRooms = bathRooms;
	}

	public java.lang.Integer getView() {
		return view;
	}

	public void setView(java.lang.Integer view) {
		this.view = view;
	}

	public java.lang.String getBuildDate() {
		return buildDate;
	}

	public void setBuildDate(java.lang.String buildDate) {
		this.buildDate = buildDate;
	}

	public java.lang.Integer getFloor() {
		return floor;
	}

	public void setFloor(java.lang.Integer floor) {
		this.floor = floor;
	}

	public java.lang.Integer getHomeLoan() {
		return homeLoan;
	}

	public void setHomeLoan(java.lang.Integer homeLoan) {
		this.homeLoan = homeLoan;
	}

	public java.lang.String getDescription() {
		return description;
	}

	public void setDescription(java.lang.String description) {
		this.description = description;
	}

	public java.lang.Integer getPrice() {
		return price;
	}

	public void setPrice(java.lang.Integer price) {
		this.price = price;
	}

	public java.lang.Integer getSqrft() {
		return sqrft;
	}

	public void setSqrft(java.lang.Integer sqrft) {
		this.sqrft = sqrft;
	}

	public java.lang.Integer getCurrency() {
		return currency;
	}

	public void setCurrency(java.lang.Integer currency) {
		this.currency = currency;
	}

	public java.lang.Integer getIsactive() {
		return isactive;
	}

	public void setIsactive(java.lang.Integer isactive) {
		this.isactive = isactive;
	}

	public java.lang.Integer getPlanType() {
		return planType;
	}

	public void setPlanType(java.lang.Integer planType) {
		this.planType = planType;
	}

	public java.lang.Integer getIsFeatured() {
		return isFeatured;
	}

	public void setIsFeatured(java.lang.Integer isFeatured) {
		this.isFeatured = isFeatured;
	}

	public java.lang.Integer getIsBargain() {
		return isBargain;
	}

	public void setIsBargain(java.lang.Integer isBargain) {
		this.isBargain = isBargain;
	}

	public java.lang.Integer getIsYardSign() {
		return isYardSign;
	}

	public void setIsYardSign(java.lang.Integer isYardSign) {
		this.isYardSign = isYardSign;
	}

	public java.lang.Integer getIsEBook() {
		return isEBook;
	}

	public void setIsEBook(java.lang.Integer isEBook) {
		this.isEBook = isEBook;
	}

	public java.lang.Integer getAmenity1() {
		return amenity1;
	}

	public void setAmenity1(java.lang.Integer amenity1) {
		this.amenity1 = amenity1;
	}

	public java.lang.Integer getAmenity2() {
		return amenity2;
	}

	public void setAmenity2(java.lang.Integer amenity2) {
		this.amenity2 = amenity2;
	}

	public java.lang.Integer getAmenity3() {
		return amenity3;
	}

	public void setAmenity3(java.lang.Integer amenity3) {
		this.amenity3 = amenity3;
	}

	public java.lang.Integer getAmenity4() {
		return amenity4;
	}

	public void setAmenity4(java.lang.Integer amenity4) {
		this.amenity4 = amenity4;
	}

	public java.lang.Integer getAmenity5() {
		return amenity5;
	}

	public void setAmenity5(java.lang.Integer amenity5) {
		this.amenity5 = amenity5;
	}

	public java.lang.Integer getAmenity6() {
		return amenity6;
	}

	public void setAmenity6(java.lang.Integer amenity6) {
		this.amenity6 = amenity6;
	}

	public java.lang.Integer getAmenity7() {
		return amenity7;
	}

	public void setAmenity7(java.lang.Integer amenity7) {
		this.amenity7 = amenity7;
	}

	public java.lang.Integer getAmenity8() {
		return amenity8;
	}

	public void setAmenity8(java.lang.Integer amenity8) {
		this.amenity8 = amenity8;
	}

	public java.lang.Integer getAmenity9() {
		return amenity9;
	}

	public void setAmenity9(java.lang.Integer amenity9) {
		this.amenity9 = amenity9;
	}

	public java.lang.Integer getAmenity10() {
		return amenity10;
	}

	public void setAmenity10(java.lang.Integer amenity10) {
		this.amenity10 = amenity10;
	}

	public java.lang.Integer getAmenity11() {
		return amenity11;
	}

	public void setAmenity11(java.lang.Integer amenity11) {
		this.amenity11 = amenity11;
	}

	public java.lang.Integer getAmenity12() {
		return amenity12;
	}

	public void setAmenity12(java.lang.Integer amenity12) {
		this.amenity12 = amenity12;
	}

	public java.lang.Integer getAmenity13() {
		return amenity13;
	}

	public void setAmenity13(java.lang.Integer amenity13) {
		this.amenity13 = amenity13;
	}

	public java.lang.Integer getAmenity14() {
		return amenity14;
	}

	public void setAmenity14(java.lang.Integer amenity14) {
		this.amenity14 = amenity14;
	}

	public java.lang.Integer getReservedInt() {
		return reservedInt;
	}

	public void setReservedInt(java.lang.Integer reservedInt) {
		this.reservedInt = reservedInt;
	}

	public java.lang.String getReservedStr() {
		return reservedStr;
	}

	public void setReservedStr(java.lang.String reservedStr) {
		this.reservedStr = reservedStr;
	}

	public java.lang.Long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(java.lang.Long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public java.lang.String getName() {
		return name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

	public java.lang.Integer getWipState() {
		return wipState;
	}

	public void setWipState(java.lang.Integer wipState) {
		this.wipState = wipState;
	}

	public java.lang.Integer getOtherSideId() {
		return otherSideId;
	}

	public void setOtherSideId(java.lang.Integer otherSideId) {
		this.otherSideId = otherSideId;
	}

	public org.jchlabs.gharonda.domain.model.Users getUser() {
		return user;
	}

	public void setUser(org.jchlabs.gharonda.domain.model.Users user) {
		this.user = user;
	}

	public java.util.Set<org.jchlabs.gharonda.domain.model.Contentitems> getContentitems() {
		return contentitems;
	}

	public void setContentitems(java.util.Set<org.jchlabs.gharonda.domain.model.Contentitems> contentitems) {
		this.contentitems = contentitems;
	}

	public java.lang.String getAmenities() {
		return amenities;
	}

	public void setAmenities(java.lang.String amenities) {
		this.amenities = amenities;
	}

	public java.lang.Long getCreated() {
		return created;
	}

	public void setCreated(java.lang.Long created) {
		this.created = created;
	}

	public java.lang.Integer getPMode() {
		return pMode;
	}

	public void setPMode(java.lang.Integer mode) {
		pMode = mode;
	}

	public boolean equals(Object obj) {
		if (null == obj)
			return false;
		if (!(obj instanceof org.jchlabs.gharonda.domain.model.PropertiesDTO))
			return false;
		else {
			org.jchlabs.gharonda.domain.model.PropertiesDTO properties = (org.jchlabs.gharonda.domain.model.PropertiesDTO) obj;
			if (null == this.getId() || null == properties.getId())
				return false;
			else
				return (this.getId().equals(properties.getId()));
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
		StringBuffer sb = new StringBuffer();
		sb.append("addrNum = " + this.addrNum + " ");
		sb.append("bathRooms = " + this.bathRooms + " ");
		sb.append("bedRooms = " + this.bedRooms + " ");
		sb.append("buildDate = " + this.buildDate + " ");
		sb.append("city = " + this.city + " ");
		sb.append("currency = " + this.currency + " ");
		sb.append("description = " + this.description + " ");
		sb.append("id = " + this.id + " ");
		sb.append("isBargain = " + this.isBargain + " ");
		sb.append("isEBook = " + this.isEBook + " ");
		sb.append("isFeatured = " + this.isFeatured + " ");
		sb.append("isYardSign = " + this.isYardSign + " ");
		sb.append("isactive = " + this.isactive + " ");
		sb.append("lat = " + this.lat + " ");
		sb.append("lng = " + this.lng + " ");
		sb.append("floor = " + this.floor + " ");
		sb.append("name = " + this.name + " ");
		sb.append("otherSideId = " + this.otherSideId + " ");
		sb.append("type = " + this.pType + " ");
		sb.append("mode = " + this.pMode + " ");
		sb.append("planType = " + this.planType + " ");
		sb.append("postCode = " + this.postCode + " ");
		sb.append("price = " + this.price + " ");
		sb.append("homeLoan = " + this.homeLoan + " ");
		sb.append("reservedInt = " + this.reservedInt + " ");
		sb.append("reservedStr = " + this.reservedStr + " ");
		sb.append("sqrft = " + this.sqrft + " ");
		sb.append("state = " + this.state + " ");
		sb.append("streetName = " + this.streetName + " ");
		sb.append("suburb = " + this.suburb + " ");
		sb.append("created = " + this.created + " ");
		sb.append("timeStamp = " + this.timeStamp + " ");
		sb.append("title = " + this.title + " ");
		sb.append("user id = " + this.user.getId() + " ");
		sb.append("view = " + this.view + " ");
		sb.append("wipState = " + this.wipState + " ");
		sb.append("amenity1 = " + this.amenity1 + " ");
		sb.append("amenity10 = " + this.amenity10 + " ");
		sb.append("amenity11 = " + this.amenity11 + " ");
		sb.append("amenity12 = " + this.amenity12 + " ");
		sb.append("amenity13 = " + this.amenity13 + " ");
		sb.append("amenity14 = " + this.amenity14 + " ");
		sb.append("amenity2 = " + this.amenity2 + " ");
		sb.append("amenity3 = " + this.amenity3 + " ");
		sb.append("amenity4 = " + this.amenity4 + " ");
		sb.append("amenity5 = " + this.amenity5 + " ");
		sb.append("amenity6 = " + this.amenity6 + " ");
		sb.append("amenity7 = " + this.amenity7 + " ");
		sb.append("amenity8 = " + this.amenity8 + " ");
		sb.append("amenity9 = " + this.amenity9 + " ");
		return sb.toString();

	}

}
