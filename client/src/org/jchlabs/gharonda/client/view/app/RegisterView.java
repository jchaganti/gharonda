package org.jchlabs.gharonda.client.view.app;

import java.util.HashMap;
import java.util.Map;

import org.jchlabs.gharonda.client.presenter.app.RegisterPresenterWidget.MyView;
import org.jchlabs.gharonda.client.util.PropertyOptions;
import org.jchlabs.gharonda.domain.model.Serviceproviderdetails;
import org.jchlabs.gharonda.domain.model.Users;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.EventBus;

public class RegisterView extends BaseLoginRegisterView implements MyView {

	interface RegisterViewUiBinder extends UiBinder<PopupPanel, RegisterView> {
	}

	@UiField
	Anchor register;
	@UiField
	TextBox firstName;
	@UiField
	TextBox email;
	@UiField
	TextBox lastName;
	@UiField
	TextBox confirmEmail;

	@UiField
	TextBox companyName;
	@UiField
	TextBox address1;
	@UiField
	TextBox address2;
	@UiField
	TextBox suburb;
	@UiField
	TextBox city;
	@UiField
	TextBox state;
	@UiField
	TextBox zipCode;
	@UiField
	TextBox phone;
	@UiField
	TextBox phone_area;
	@UiField
	TextBox cell;
	@UiField
	TextBox cell_area;
	@UiField
	TextBox webSite;

	@UiField
	PasswordTextBox passwd;
	@UiField
	PasswordTextBox confirmPassWd;

	@UiField
	ListBox businessType;

	@UiField
	CheckBox signUpForServiceProvider;
	@UiField
	CheckBox agreement;
	@UiField
	HTMLPanel serviceProviderInfoPanel;
	private static RegisterViewUiBinder uiBinder = GWT.create(RegisterViewUiBinder.class);

	private final PopupPanel widget;
	private static final Map<TextBox, String> tbToMsgMap = new HashMap<TextBox, String>();

	@Inject
	protected RegisterView(EventBus eventBus) {
		super(eventBus);
		widget = uiBinder.createAndBindUi(this);
		for (String type : PropertyOptions.BUSINESS_TYPES) {
			businessType.addItem(type);
		}
		businessType.setSize("130px", "20px");
		initTextBoxToMsgMap();

	}

	@Override
	public Widget asWidget() {
		return widget;
	}

	@Override
	public void initUI() {
		serviceProviderInfoPanel.setVisible(false);
		signUpForServiceProvider.setValue(false);
		email.setValue("");
		confirmEmail.setValue("");
		passwd.setValue("");
		confirmPassWd.setValue("");
		firstName.setValue("");
		lastName.setValue("");
		companyName.setValue("");
		phone.setValue("");
		cell.setValue("");
		phone_area.setValue("");
		cell_area.setValue("");
		address1.setValue("");
		address2.setValue("");
		state.setValue("");
		city.setValue("");
		suburb.setValue("");
		zipCode.setValue("");
		webSite.setValue("");
		agreement.setValue(false);
	}

	@UiHandler("policy")
	void onPolicyClicked(ClickEvent event) {
		Window.open(PropertyOptions.getPrivacyPageUrl(), "", "");
	}

	@UiHandler("registerMeIn")
	void registerClicked(ClickEvent event) {
		uiHandlers.handleRegister(getUserForRegistration(), getPostLogInCB());
	}

	@UiHandler("signUpForServiceProvider")
	void signUpForServiceProviderClicked(ClickEvent event) {
		if (signUpForServiceProvider.getValue()) {
			serviceProviderInfoPanel.setVisible(true);
		} else {
			serviceProviderInfoPanel.setVisible(false);
		}
	}

	private Users getUserForRegistration() {
		if (!agreement.getValue()) {
			Window.alert(PropertyOptions.agreeToTerms);
			return null;
		}
		if (isNull(email) || isNull(email) || isNull(passwd) || isNull(confirmPassWd) || isNull(firstName)
				|| isNull(lastName) || isNull(phone) || isNull(cell)) {
			return null;
		}
		String emailVal = ((String) email.getValue()).trim();
		String confirmEmailVal = ((String) confirmEmail.getValue()).trim();

		if (!isValidEmail(emailVal) || !isValidEmail(confirmEmailVal)) {
			Window.alert(PropertyOptions.enterEmailAddress);
			return null;
		}

		if (!PropertyOptions.isValidPhone(phone_area, phone)) {
			Window.alert(PropertyOptions.enterPhone);
			return null;
		}
		if (!PropertyOptions.isValidPhone(cell_area, cell)) {
			Window.alert(PropertyOptions.enterCell);
			return null;
		}
		Users user = new Users();

		if (!emailVal.equals(confirmEmailVal)) {
			Window.alert(PropertyOptions.emailsNotMatch);
			return null;
		}

		String passwdVal = (String) passwd.getValue();
		String confirmPasswdVal = (String) confirmPassWd.getValue();

		if (passwdVal != null && !passwdVal.equals(confirmPasswdVal)) {
			Window.alert(PropertyOptions.passwdsNotMatch);
			return null;
		}
		user.setEmail(emailVal);
		user.setFirstName(firstName.getValue());
		user.setLastName(lastName.getValue());
		user.setPasswd(passwdVal);
		user.setPhone(phone_area.getValue() + "-" + phone.getValue());
		user.setCell(cell_area.getValue() + "-" + cell.getValue());
		int emailUpdates = signUpForServiceProvider.getValue() ? 1 : 0;
		user.setEmailUpdates(emailUpdates);
		Serviceproviderdetails providerDetails = null;
		if (emailUpdates == 1) {
			if (isNull(companyName)) {
				return null;
			}
			int businessType = this.businessType.getSelectedIndex();
			if (businessType == -1 || businessType == 0) {
				Window.alert(PropertyOptions.selectBusinessType);
				return null;
			}
			providerDetails = new Serviceproviderdetails();
			providerDetails.setAddrLine1(address1.getValue());
			providerDetails.setAddrLine2(address2.getValue());
			providerDetails.setSuburb(suburb.getValue());
			providerDetails.setCity(city.getValue());
			providerDetails.setState(state.getValue());
			providerDetails.setCompanyName(companyName.getValue());
			// providerDetails.setPhone(phone.getValue());
			providerDetails.setWebsite(webSite.getValue());
			providerDetails.setZip(zipCode.getValue());
			providerDetails.setBusinessType(businessType);
			// providerDetails.setFax(fax.getValue());
		}
		user.setServiceproviderdetails(providerDetails);
		return user;
	}

	private void initTextBoxToMsgMap() {
		tbToMsgMap.put(email, PropertyOptions.enterEmailAddress);
		tbToMsgMap.put(confirmEmail, PropertyOptions.enterEmailAddress);
		tbToMsgMap.put(passwd, PropertyOptions.enterPasswd);
		tbToMsgMap.put(confirmPassWd, PropertyOptions.enterPasswd);
		tbToMsgMap.put(firstName, PropertyOptions.enterFirstName);
		tbToMsgMap.put(lastName, PropertyOptions.enterLastName);
		tbToMsgMap.put(companyName, PropertyOptions.enterCompanyName);
		tbToMsgMap.put(phone, PropertyOptions.enterPhone);
		tbToMsgMap.put(phone_area, PropertyOptions.enterPhone);
		tbToMsgMap.put(cell, PropertyOptions.enterCell);
		tbToMsgMap.put(cell_area, PropertyOptions.enterPhone);
	}

	private boolean isNull(TextBox f) {
		boolean result = true;
		Object value = f.getValue();
		if (value != null) {
			if (value instanceof String && ((String) value).length() > 0) {
				result = false;
			}
		}
		if (result) {
			String msg = tbToMsgMap.get(f);
			if (msg != null) {
				Window.alert(msg);
			}
		}
		return result;
	}

	private native boolean isValidEmail(String email) /*-{
														var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
														return reg.test(email);
														}-*/;

}
