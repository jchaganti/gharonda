package org.jchlabs.gharonda.client.view.account;

import java.util.HashMap;
import java.util.Map;

import org.jchlabs.gharonda.client.presenter.account.MyProfilePresenter.MyView;
import org.jchlabs.gharonda.client.util.PropertyOptions;
import org.jchlabs.gharonda.client.view.AbstractAppView;
import org.jchlabs.gharonda.domain.model.Serviceproviderdetails;
import org.jchlabs.gharonda.domain.model.Users;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class MyProfileView extends AbstractAppView<MyProfileUiHandlers> implements MyView {
	interface MyProfileViewUiBinder extends UiBinder<Widget, MyProfileView> {

	}

	private static final MyProfileViewUiBinder uiBinder = GWT.create(MyProfileViewUiBinder.class);
	private static final Map<TextBox, String> tbToMsgMap = new HashMap<TextBox, String>();
	@UiField
	HTMLPanel contentPanel;
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
	PasswordTextBox oldPasswd;
	@UiField
	PasswordTextBox passwd;

	@UiField
	PasswordTextBox confirmPassWd;

	@UiField
	ListBox businessType;

	@UiField
	CheckBox signUpForServiceProvider;

	@UiField
	HTMLPanel serviceProviderInfoPanel;

	@UiField
	Image profileUpdate;

	@UiField
	Image passwdUpdate;
	@UiField
	Image emailUpdate;

	public final Widget widget;

	private Users user = null;;

	@Inject
	public MyProfileView() {
		widget = uiBinder.createAndBindUi(this);
		this.contentPanel.getElement().setId("content");

		for (String type : PropertyOptions.BUSINESS_TYPES) {
			businessType.addItem(type);
		}
		businessType.setSize("130px", "20px");
		serviceProviderInfoPanel.setVisible(false);
		signUpForServiceProvider.setEnabled(true);
		initTextBoxToMsgMap();

	}

	@Override
	public Widget asWidget() {
		return widget;
	}

	@Override
	public void showUser(Users user) {
		setWindowTitle("Hesabım - Gharonda.com");
		this.user = user;
		firstName.setValue(user.getFirstName());

		lastName.setValue(user.getLastName());
		if (user.getPhone() != null) {
			if (user.getPhone().length() > 3) {
				phone_area.setValue(user.getPhone().substring(0, 3));
				phone.setValue(user.getPhone().substring(3));
			} else {
				phone.setValue(user.getPhone());
			}
		}

		if (user.getCell() != null) {
			if (user.getCell().length() > 3) {
				cell_area.setValue(user.getCell().substring(0, 3));
				cell.setValue(user.getCell().substring(3));
			} else {
				cell.setValue(user.getPhone());
			}
		}

		if (user.getServiceproviderdetails() != null) {

			signUpForServiceProvider.setValue(true);

			serviceProviderInfoPanel.setVisible(true);

			companyName.setValue(user.getServiceproviderdetails().getCompanyName());

			address1.setValue(user.getServiceproviderdetails().getAddrLine1());

			address2.setValue(user.getServiceproviderdetails().getAddrLine2());

			suburb.setValue(user.getServiceproviderdetails().getSuburb());

			city.setValue(user.getServiceproviderdetails().getCity());

			state.setValue(user.getServiceproviderdetails().getState());

			zipCode.setValue(user.getServiceproviderdetails().getZip());

			webSite.setValue(user.getServiceproviderdetails().getWebsite());

			businessType.setSelectedIndex(user.getServiceproviderdetails().getBusinessType());
		} else {
			signUpForServiceProvider.setValue(false);

			serviceProviderInfoPanel.setVisible(false);

			companyName.setValue("");

			address1.setValue("");

			address2.setValue("");

			suburb.setValue("");

			city.setValue("");

			state.setValue("");

			zipCode.setValue("");

			webSite.setValue("");

			businessType.setSelectedIndex(0);
		}
	}

	@UiHandler("emailUpdate")
	void onEmailUpdate(ClickEvent event) {
		String newEmail = getNewEmail();
		if (newEmail != null) {
			getUiHandlers().handleUpdateEmail(newEmail);
		}
	}

	@UiHandler({ "myFavorites", "myListings", "myNotifierProfile" })
	void onMyAccountClicked(ClickEvent event) {
		getUiHandlers().handleMyAccountClicked();
	}

	@UiHandler("passwdUpdate")
	void onPasswdUpdate(ClickEvent event) {
		String newPassWd = getNewPassWord();
		String oldPassWd = getOldPassWord();
		if (oldPassWd != null && newPassWd != null) {
			getUiHandlers().handleUpdatePassWord(oldPassWd, newPassWd);
		}
	}

	@UiHandler("profileUpdate")
	void onProfileUpdate(ClickEvent event) {
		if (updateUser()) {
			getUiHandlers().handleUpdateUser(user);
		}
	}

	@UiHandler("signUpForServiceProvider")
	void onSignUpForServiceProvider(ClickEvent event) {
		if (signUpForServiceProvider.getValue()) {
			serviceProviderInfoPanel.setVisible(true);
		} else {
			serviceProviderInfoPanel.setVisible(false);
		}
	}

	private String getNewEmail() {
		String res = null;
		if (!isNull(email) && !isNull(confirmEmail)) {
			String emailVal = (String) email.getValue();
			String confirmEmailVal = (String) confirmEmail.getValue();

			if (emailVal != null && !emailVal.equals(confirmEmailVal)) {
				Window.alert(PropertyOptions.emailsNotMatch);
			} else if (emailVal.equals(user.getEmail())) {
				Window.alert(PropertyOptions.oldNewEmailsSame);
			} else {
				res = emailVal;
			}
		}
		return res;
	}

	private String getNewPassWord() {
		String res = null;
		if (!isNull(passwd) && !isNull(confirmPassWd)) {
			String passwdVal = (String) passwd.getValue();
			String confirmPasswdVal = (String) confirmPassWd.getValue();

			if (passwdVal != null && !passwdVal.equals(confirmPasswdVal)) {
				Window.alert(PropertyOptions.passwdsNotMatch);
			} else {
				res = passwdVal;
			}
		}
		return res;

	}

	private String getOldPassWord() {
		String res = null;
		if (!isNull(oldPasswd)) {
			res = oldPasswd.getValue();
		}
		return res;
	}

	private void initTextBoxToMsgMap() {
		tbToMsgMap.put(email, PropertyOptions.enterEmailAddress);
		tbToMsgMap.put(confirmEmail, PropertyOptions.enterEmailAddress);
		tbToMsgMap.put(oldPasswd, PropertyOptions.enterOldPasswd);
		tbToMsgMap.put(passwd, PropertyOptions.enterPasswd);
		tbToMsgMap.put(confirmPassWd, PropertyOptions.enterPasswd);
		tbToMsgMap.put(firstName, PropertyOptions.enterFirstName);
		tbToMsgMap.put(lastName, PropertyOptions.enterLastName);
		tbToMsgMap.put(companyName, PropertyOptions.enterCompanyName);
		tbToMsgMap.put(phone, PropertyOptions.enterPhone);
		tbToMsgMap.put(cell, PropertyOptions.enterCell);
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

	private boolean updateUser() {
		boolean res = false;
		if (!isNull(firstName) && !isNull(lastName) && !isNull(phone) && !isNull(cell)) {
			if (!PropertyOptions.isValidPhone(phone_area, phone)) {
				Window.alert(PropertyOptions.enterPhone);
				return false;
			}
			if (!PropertyOptions.isValidPhone(cell_area, cell)) {
				Window.alert(PropertyOptions.enterCell);
				return false;
			}
			user.setFirstName(firstName.getValue());
			user.setLastName(lastName.getValue());
			user.setPhone(phone_area.getValue() + phone.getValue());
			user.setCell(cell_area.getValue() + cell.getValue());
			if (signUpForServiceProvider.getValue() && serviceProviderInfoPanel.isVisible()) {
				if (!isNull(companyName)) {
					int businessType = this.businessType.getSelectedIndex();
					if (businessType == -1 || businessType == 0) {
						Window.alert(PropertyOptions.selectBusinessType);
					} else {
						Serviceproviderdetails providerDetails = user.getServiceproviderdetails();
						if (providerDetails == null) {
							providerDetails = new Serviceproviderdetails();
							user.setServiceproviderdetails(providerDetails);
						}
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
						res = true;
					}
				}
			} else {
				res = true;
			}
		}
		return res;
	}
}
