package org.jchlabs.gharonda.client.util;

import com.google.gwt.i18n.client.CurrencyData;

public class TLCurrencyData implements CurrencyData {

	@Override
	public String getCurrencyCode() {
		return "TRY";
	}

	@Override
	public String getCurrencySymbol() {
		return "TL";
	}

	@Override
	public int getDefaultFractionDigits() {
		return 0;
	}

	@Override
	public String getPortableCurrencySymbol() {
		return "";
	}

	@Override
	public boolean isDeprecated() {
		return false;
	}

	@Override
	public boolean isSpaceForced() {
		return true;
	}

	@Override
	public boolean isSpacingFixed() {
		return true;
	}

	@Override
	public boolean isSymbolPositionFixed() {
		return true;
	}

	@Override
	public boolean isSymbolPrefix() {
		return true;
	}

}
