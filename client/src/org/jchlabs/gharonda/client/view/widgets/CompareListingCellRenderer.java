package org.jchlabs.gharonda.client.view.widgets;

import java.util.List;
import java.util.Set;

import org.jchlabs.gharonda.client.util.PropertyOptions;
import org.jchlabs.gharonda.domain.model.Contentitems;
import org.jchlabs.gharonda.domain.model.PropertiesDTO;

import com.google.gwt.gen2.table.client.CellRenderer;
import com.google.gwt.gen2.table.client.ColumnDefinition;
import com.google.gwt.gen2.table.client.TableDefinition.AbstractCellView;

public class CompareListingCellRenderer implements CellRenderer<List<PropertiesDTO>, Integer> {

	private static final String COLON = ": ";

	@Override
	public void renderRowValue(List<PropertiesDTO> rowValue, ColumnDefinition<List<PropertiesDTO>, Integer> columnDef,
			AbstractCellView<List<PropertiesDTO>> view) {

		String locale = PropertyOptions.getCurrentLocale();

		String val = "<div class=\"innercontent_compare\">    "
				+ "<table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\">"
				+ "        <tbody>            <tr>                "
				+ "<td valign=\"top\" align=\"left\">                    "
				+ "<table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\">" + "<tbody><tr>"
				+ getProperties(rowValue, locale) + "</tr></tbody></table></td></tr></tbody></table></div>";
		// String val = getProperties(rowValue);
		view.setHTML(val);

	}

	private String getProperties(List<PropertiesDTO> properties, String locale) {
		StringBuffer sb = new StringBuffer(1024);
		for (PropertiesDTO p : properties) {
			sb.append(getPropertyDetails(p, locale));
		}
		return sb.toString();
	}

	private String getPropertyDetails(PropertiesDTO p, String locale) {
		String priceStr = PropertyOptions.getFormattedPrice(p.getPrice(), p.getCurrency());
		String pType = p.getPType() == 0 ? PropertyOptions.UNKNOWN_PROP : PropertyOptions.P_TYPES.get(p.getPType() - 1);
		String pricePerSfStr = PropertyOptions.UNKNOWN;

		if (p.getSqrft() > 0) {
			Integer p1 = (p.getPrice() / p.getSqrft());
			pricePerSfStr = p1.toString();
		}
		String bedRooms = p.getBedRooms() == 0 ? "0" : PropertyOptions.BEDROOM_TYPES.get(p.getBedRooms() - 1);
		String bathRooms = p.getBathRooms().toString();
		String selView = p.getView() == 0 ? PropertyOptions.NOT_GIVEN : PropertyOptions.VIEW_TYPES.get(p.getView() - 1);

		String selHeat = p.getHeat() == 0 ? PropertyOptions.NOT_GIVEN : PropertyOptions.HEAT_TYPES.get(p.getHeat() - 1);

		String selHomeLoan = p.getHomeLoan() == 0 ? PropertyOptions.NOT_GIVEN : PropertyOptions.YES_NO_OPTIONS.get(p
				.getHomeLoan() - 1);

		String selFloor = p.getFloor() == 0 ? PropertyOptions.NOT_GIVEN : PropertyOptions.FLOORS_TYPES
				.get(p.getFloor() - 1);

		StringBuffer amenities = new StringBuffer(256);
		List<Integer> amenityNos = PropertyOptions.getAmenityIdsForProperty(p);
		for (Integer amenityNo : amenityNos) {
			String amenity = "<li>" + PropertyOptions.AMENITIES.get(amenityNo) + "</li>";
			amenities.append(amenity);
		}

		String imgUrl = PropertyOptions.getImageUrl(p, "_tn2", "images/img9_index.jpg");
		;
		String detailsUrl = PropertyOptions.getPropertyDetailPageUrl(p.getId(), locale);

		String result = "<td valign=\"top\" align=\"left\"><div class=\"description\"> "
				+ "<table width=\"181\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\">"
				+ "<tbody><tr><td height=\"24\" width=\"7\" valign=\"top\" align=\"left\">"
				+ "<img height=\"24\" width=\"7\" border=\"0\" alt=\"\" src=\"images/graybox_left.gif\"/>"
				+ "</td><td valign=\"top\" align=\"left\" class=\"bg_graybox\"><h1 class=\"red_x\" onClick=\'javascript:deleteCLProperty(\""
				+ p.getId()
				+ "\"); \'>"
				+ "ID#<span>"
				+ p.getId()
				+ "</span></h1></td>"
				+ "<td height=\"24\" width=\"7\" valign=\"top\" align=\"right\">"
				+ "<img height=\"24\" width=\"7\" border=\"0\" alt=\"\" src=\"images/graybox_right.gif\"/>"
				+ "</td></tr></tbody></table><div class=\"description_inner\">"
				+ "<table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\">"
				+ "<tbody><tr><td valign=\"top\" align=\"center\"><div>"
				+ "<img height=\"101\" width=\"135\" border=\"0\" alt=\"\" "
				+ "src=\""
				+ imgUrl
				+ "\"/>"
				+ "<span class=\"redbig_txt\"><strong>"
				+ priceStr
				+ "</strong></span></div><span class=\"seperator_limited\"></span>"
				+ "</td></tr><tr><td valign=\"top\" align=\"left\"><div class=\"description_txt\">"
				+ PropertyOptions.STATE_STR1
				+ COLON
				+ p.getState()
				+ "<br/>"
				+ PropertyOptions.CITY_STR1
				+ COLON
				+ p.getCity()
				+ "<br/>"
				+ PropertyOptions.SUBURB_STR1
				+ COLON
				+ p.getSuburb()
				+ "<br/>"
				+ PropertyOptions.POST_CODE_STR
				+ COLON
				+ p.getPostCode()
				+ "<br/><br/>"
				+ PropertyOptions.PROP_TYPE
				+ COLON
				+ pType
				+ "<br/>"
				+ "m2: "
				+ p.getSqrft()
				+ "<br/>"
				+ PropertyOptions.pricePerSft
				+ COLON
				+ pricePerSfStr
				+ "<br/>"
				+ PropertyOptions.rooms
				+ COLON
				+ bedRooms
				+ "<br/>"
				+ PropertyOptions.baths
				+ COLON
				+ bathRooms
				+ "<br/>"
				+ PropertyOptions.HEAT_STR
				+ COLON
				+ selHeat
				+ "<br/>"
				+ PropertyOptions.VIEW
				+ COLON
				+ selView
				+ "<br/>"
				+ PropertyOptions.YEAR_BUILT
				+ COLON
				+ p.getBuildDate()
				+ "<br/>"
				+ PropertyOptions.FLOOR
				+ COLON
				+ selFloor
				+ "<br/>"
				+ PropertyOptions.HOME_LOAN
				+ COLON
				+ selHomeLoan
				+ "<br/>"
				+ "<ul><li class=\"nobg_description\">"
				+ "<strong>"
				+ PropertyOptions.otherDetails
				+ "</strong>"
				+ "</li>"
				+ amenities.toString()
				+ "</ul><strong>"
				+ PropertyOptions.description
				+ "</strong>"
				+ "<br/><p>"
				+ p.getDescription()
				+ "</p><a href=\"#\" style=\"margin-top:10px;\" onClick=\'javascript:window.open(\""
				+ detailsUrl
				+ "\"); return false;\'> <img src=\"images/btn_detail.gif\"/></a></div>"
				+ "</td></tr></tbody></table></div></div></td>";

		return result;
	}
}
