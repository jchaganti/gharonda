package org.jchlabs.gharonda.handlers;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.dozer.DozerBeanMapperSingletonWrapper;
import org.jchlabs.gharonda.content.BaseWIPSvcHelper;
import org.jchlabs.gharonda.domain.model.Contentholder;
import org.jchlabs.gharonda.domain.model.Properties;
import org.jchlabs.gharonda.domain.model.PropertiesDTO;
import org.jchlabs.gharonda.domain.model.Users;
import org.jchlabs.gharonda.domain.pom.dao.iface.ContentholderDAO;
import org.jchlabs.gharonda.domain.pom.dao.iface.ContentitemsDAO;
import org.jchlabs.gharonda.server.test.LoadProperties;
import org.jchlabs.gharonda.shared.rpc.CreateProperty;
import org.jchlabs.gharonda.shared.rpc.CreatePropertyResult;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

public class CreatePropertyHandler extends AbstractSessionHandler<CreateProperty, CreatePropertyResult> {

	private Provider<ContentholderDAO> contentholderDAOProvider;
	private Provider<ContentitemsDAO> itemDAOProvider;

	@Inject
	public CreatePropertyHandler(Log logger, Provider<HttpSession> sessionProvider,
			Provider<ContentholderDAO> contentholderDAOProvider, Provider<ContentitemsDAO> itemDAOProvider) {
		super(logger, sessionProvider);
		this.contentholderDAOProvider = contentholderDAOProvider;
		this.itemDAOProvider = itemDAOProvider;
	}

	@Override
	protected CreatePropertyResult executeInternal(CreateProperty action, ExecutionContext context)
			throws ActionException {
		Properties property = new Properties();
		loadProperty(property);
		Users user = getUser();
		System.out.println("The user  =" + user);
		property.setUser(user);
		BaseWIPSvcHelper helper = new BaseWIPSvcHelper((Contentholder) property, contentholderDAOProvider.get(),
				itemDAOProvider.get());
		property = (Properties) helper.create(property);
		System.out.println("The property  that got finally created =" + property.getId());
		PropertiesDTO pDTO = DozerBeanMapperSingletonWrapper.getInstance().map(property, PropertiesDTO.class);
		if (property.getId() == 1) {
			new LoadProperties();
		}
		return new CreatePropertyResult(pDTO);
	}

	@Override
	public Class<CreateProperty> getActionType() {
		return CreateProperty.class;
	}

	private void loadProperty(Properties property) {
		property.setLat(41.079578);
		property.setLng(29.011889);
		property.setTimeStamp(System.currentTimeMillis());
		property.setCreated(System.currentTimeMillis());
		property.setAddrNum("");
		property.setBathRooms(null);
		property.setBedRooms(0);
		property.setBuildDate("");
		property.setCity("");
		property.setDescription("");
		property.setHeat(0);
		property.setHomeLoan(0);
		property.setFloor(0);
		property.setPlanType(0);
		property.setSqrft(null);
		property.setPrice(null);
		property.setState("");
		property.setStreetName("");
		property.setSuburb("");
		property.setTitle("");
		property.setView(0);
		property.setPType(0);
		property.setPMode(1);
		property.setCurrency(0);
		property.setIsactive(1);
		property.setAmenity1(0);
		property.setAmenity2(0);
		property.setAmenity3(0);
		property.setAmenity4(0);
	}

	@Override
	public void undo(CreateProperty action, CreatePropertyResult result, ExecutionContext context)
			throws ActionException {
		// TODO Auto-generated method stub

	}
}
