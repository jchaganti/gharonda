package org.jchlabs.gharonda.client.view.widgets;

import java.util.Set;

import org.jchlabs.gharonda.client.util.PropertyOptions;
import org.jchlabs.gharonda.domain.model.Contentitems;
import org.jchlabs.gharonda.domain.model.PropertiesDTO;

import com.google.gwt.gen2.table.client.CellRenderer;
import com.google.gwt.gen2.table.client.ColumnDefinition;
import com.google.gwt.gen2.table.client.TableDefinition.AbstractCellView;

public class PropertyCellRenderer implements CellRenderer<PropertiesDTO, Integer> {
	String ctx = null;

	public PropertyCellRenderer() {
		ctx = null;
	}

	public PropertyCellRenderer(String string) {
		ctx = string;
	}

	@Override
	public void renderRowValue(PropertiesDTO rowValue, ColumnDefinition<PropertiesDTO, Integer> columnDef,
			AbstractCellView<PropertiesDTO> view) {
		String addrLine1 = PropertyOptions.getFormattedAddrLine(rowValue.getStreetName(), rowValue.getAddrNum(), null,
				" No:");
		String addrLine2 = PropertyOptions.getFormattedLine(" ", rowValue.getSuburb(), rowValue.getCity(),
				rowValue.getState(), rowValue.getPostCode());
		addrLine2 = (addrLine2.isEmpty()) ? PropertyOptions.addressNotGiven : addrLine2;
		addrLine1 = addrLine1 + " " + addrLine2;
		String bedRooms = rowValue.getBedRooms() == 0 ? "&nbsp;" : PropertyOptions.BEDROOM_TYPES.get(rowValue
				.getBedRooms() - 1);
		String selHeat = rowValue.getHeat() == 0 ? "&nbsp;" : PropertyOptions.HEAT_TYPES.get(rowValue.getHeat() - 1);

		String topDescStr = PropertyOptions.formatString(rowValue.getTitle(), 55);
		String url = PropertyOptions.getImageUrl(rowValue, "_tn2", "images/img1_homesforsale.jpg");
		String priceStr = PropertyOptions.getFormattedPrice(rowValue.getPrice(), rowValue.getCurrency());
		String desc = PropertyOptions.formatString(rowValue.getDescription().trim(), 185);
		String detailsUrl = PropertyOptions.getPropertyDetailPageUrl(rowValue.getId());
		String jsFunc = "addToCompareListing";
		if (ctx != null) {
			jsFunc += ctx;
		}
		view.setHTML("<div class=\"inner_homeforsale\">        "
				+ "<table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\">          <tbody>"
				+ "<tr>            <td valign=\"top\" align=\"left\">"
				+ "<div><table cellspacing=\"0\" cellpadding=\"0\" border=\"0\">              <tbody>"
				+ "<tr>                <td valign=\"top\" align=\"left\">"
				+ "<a href=\"#\" onClick=\'javascript:window.open(\""
				+ detailsUrl
				+ "\"); return false;\'> <img width=\"123\" height=\"85\" border=\"0\" alt=\"\" "
				+ "src=\""
				+ url
				+ "\"/></a>"
				+ "</td>"
				+ "              </tr>              <tr>                "
				+ "<td valign=\"middle\" align=\"center\"><table cellspacing=\"0\" cellpadding=\"0\" border=\"0\">"
				+ "                  <tbody><tr>                    <td width=\"25\" valign=\"top\" align=\"left\">"
				+ "<input type=\"checkbox\" id=\"cb_"
				+ rowValue.getId()
				+ "\" name=\"cb_"
				+ rowValue.getId()
				+ "\""
				+ " onClick=\"javascript:"
				+ jsFunc
				+ "(\'"
				+ rowValue.getId()
				+ "\');\"/></td>                    "
				+ "<td valign=\"middle\" align=\"left\"><span class=\"black_txt3\">ID </span> "
				+ "# "
				+ "<span class=\"black_txt4\">"
				+ rowValue.getId()
				+ "</span>"
				+ "</td>                  </tr>                "
				+ "</tbody></table>                                    </td>              </tr>            "
				+ "</tbody></table></div></td>            <td valign=\"top\" align=\"left\">            	"
				+ "<div class=\"inner1_home\"><table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\">"
				+ "              <tbody><tr>                <td valign=\"top\" align=\"left\"><div class=\"inner2_home\">"
				+ "                  <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\">                    "
				+ "<tbody><tr>                      "
				+ "<td width=\"80%\" valign=\"middle\" align=\"left\"><h1>"
				+ "<a href=\"#\" style=\"text-decoration:none;\" onClick=\'javascript:window.open(\""
				+ detailsUrl
				+ "\"); return false;\'>"
				+ topDescStr
				+ "</a></h1></td>"
				+ "<td  valign=\"middle\" align=\"left\">&nbsp;</td>"
				+ "<td  valign=\"middle\" align=\"left\">&nbsp;</td>"
				+ "<td  valign=\"middle\" align=\"left\">&nbsp;</td>"
				+ "<td width=\"20%\" valign=\"middle\" align=\"right\" style=\"padding-right: 5px;\"> <span class=\"dark_red_txt_price\">"
				+ priceStr
				+ "</span></td></tr>"
				+ "</tbody></table></div></td></tr>"
				+ "<tr><td valign=\"top\" align=\"left\"><div class=\"inner2_home\">"
				+ "<table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\">"
				+ "<tbody><tr>                      "
				+ "<td width=\"96%\" valign=\"middle\" align=\"left\"><span class=\"black_txt_addr\">"
				+ addrLine1
				+ "</span></td>"
				+ "<td width=\"1%\" valign=\"middle\" align=\"left\"> </td>"
				+ "<td width=\"1%\" valign=\"middle\" align=\"left\"></td>"
				+ "<td width=\"1%\" valign=\"middle\" align=\"left\"></td>"
				+ "<td width=\"1%\" valign=\"middle\" align=\"left\"></td></tr>"
				+ "</tbody></table>                  </div></td>              </tr>"
				+ "<tr>"
				+ "<td valign=\"middle\" height=\"25\" align=\"left\"><div class=\"inner2_home\">"
				+ "<table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\">"
				+ "<tbody><tr>"
				+ "<td width=\"6%\" valign=\"middle\" align=\"left\"><span class=\"green_txt\">m2 :</span></td>"
				+ "<td width=\"6%\" valign=\"middle\" align=\"left\">"
				+ rowValue.getSqrft()
				+ "</td>"
				+ "<td width=\"12%\" valign=\"middle\" align=\"left\"><span class=\"green_txt\">"
				+ PropertyOptions.ROOM_NO
				+ PropertyOptions.COLON
				+ "</span></td>"
				+ "<td width=\"6%\" valign=\"middle\" align=\"left\">"
				+ bedRooms
				+ "</td>                      "
				+ "<td width=\"12%\" valign=\"middle\" align=\"left\"><span class=\"green_txt\">"
				+ PropertyOptions.YEAR_BUILT
				+ PropertyOptions.COLON
				+ "</span></td>"
				+ "<td width=\"10%\" valign=\"middle\" align=\"left\">"
				+ rowValue.getBuildDate()
				+ "</td>                     "
				+ " <td width=\"9%\" valign=\"middle\" align=\"left\"><span class=\"green_txt\">"
				+ PropertyOptions.HEAT_STR
				+ PropertyOptions.COLON
				+ "</span></td>"
				+ "<td width=\"28%\" valign=\"middle\" align=\"left\"><span>"
				+ selHeat
				+ "</span></td>"
				+ "</tr></tbody></table></div></td></tr><tr><td valign=\"top\" align=\"left\">"
				+ "<div style=\"width:560px;overflow:hidden;display:block;height:30px;\">"
				// + "<div class=\"inner2_home\">"
				+ "<p>" + desc + "</p></div></td>              </tr>"
				// +"              <tr>                "
				// + "<td valign=\"top\" align=\"left\"><div class=\"inner3_home\">"
				// +
				// "<table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\">                  <tbody><tr>"
				// + "<td valign=\"middle\" align=\"left\">" +
				// "<img style=\"border:0px;margin:0px;cursor:pointer;\" alt=\"\" src=\"images/btn_favourties.gif\" onClick=\"javascript:addToFavorites_1(\'"
				// + rowValue.getId() + "\');\"/>" +
				//
				// "</td>"
				// +
				// "<td valign=\"middle\" align=\"left\"><input type=\"image\" value=\"\" src=\"images/btn_loan.gif\"/></td>"
				// +
				// "<td valign=\"middle\" align=\"left\"><input type=\"image\" value=\"\" src=\"images/btn_property.gif\"/></td>"
				// +
				// "<td valign=\"middle\" align=\"left\"><input type=\"image\" value=\"\" src=\"images/btn_print.gif\"/></td>"
				// + "</tr>                </tbody></table></div>                </td>              </tr>            "
				+ "</tbody></table></div></td>          </tr>        </tbody></table>      	</div>");

	}

}
