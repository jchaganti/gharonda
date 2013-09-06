package org.jchlabs.gharonda.handlers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.dozer.DozerBeanMapper;
import org.dozer.DozerBeanMapperSingletonWrapper;
import org.jchlabs.gharonda.common.Utils;
import org.jchlabs.gharonda.content.BaseWIPSvcHelper;
import org.jchlabs.gharonda.domain.model.Contentholder;
import org.jchlabs.gharonda.domain.model.Emailfrequencies;
import org.jchlabs.gharonda.domain.model.Properties;
import org.jchlabs.gharonda.domain.model.PropertiesDTO;
import org.jchlabs.gharonda.domain.model.Users;
import org.jchlabs.gharonda.domain.pom.dao.iface.ContentholderDAO;
import org.jchlabs.gharonda.domain.pom.dao.iface.ContentitemsDAO;
import org.jchlabs.gharonda.domain.pom.dao.iface.EmailfrequenciesDAO;
import org.jchlabs.gharonda.shared.rpc.SaveProperty;
import org.jchlabs.gharonda.shared.rpc.SavePropertyResult;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

public class SavePropertyHandler extends AbstractSessionHandler<SaveProperty, SavePropertyResult> {

	private Provider<ContentholderDAO> contentholderDAOProvider;
	private Provider<ContentitemsDAO> itemDAOProvider;
	private final Provider<EmailfrequenciesDAO> emailfrequenciesDAOProvider;

	@Inject
	public SavePropertyHandler(Log logger, Provider<HttpSession> sessionProvider,
			Provider<ContentholderDAO> contentholderDAOProvider, Provider<ContentitemsDAO> itemDAOProvider,
			Provider<EmailfrequenciesDAO> emailfrequenciesDAOProvider) {
		super(logger, sessionProvider);
		this.contentholderDAOProvider = contentholderDAOProvider;
		this.itemDAOProvider = itemDAOProvider;
		this.emailfrequenciesDAOProvider = emailfrequenciesDAOProvider;
	}

	@Override
	protected SavePropertyResult executeInternal(SaveProperty action, ExecutionContext context) throws ActionException {
		DozerBeanMapper mapper = new DozerBeanMapper();

		Properties property = mapper.map(action.getProperties(), Properties.class);

		Contentholder originalProperty = contentholderDAOProvider.get().get(action.getProperties().getId());
		property.setContentitems(originalProperty.getContentitems());
		property.setTimeStamp(System.currentTimeMillis());
		System.out.println("The property  being saved = " + property.getId());
		Users user = getUser();
		System.out.println("The user  =" + user);
		property.setUser(user);
		BaseWIPSvcHelper helper = new BaseWIPSvcHelper((Contentholder) property, contentholderDAOProvider.get(),
				itemDAOProvider.get());
		property = (Properties) helper.checkin();
		postSaveOrUpdateproperties(property);
		System.out.println("The property  that got checkedin =" + property.getId());
		PropertiesDTO pDTO = DozerBeanMapperSingletonWrapper.getInstance().map(property, PropertiesDTO.class);
		return new SavePropertyResult(pDTO);
	}

	@Override
	public Class<SaveProperty> getActionType() {
		return SaveProperty.class;
	}

	private void postSaveOrUpdateproperties(Properties property) {
		List<Emailfrequencies> frequencies = emailfrequenciesDAOProvider.get().findAll();
		for (Emailfrequencies frequency : frequencies) {
			byte[] bytes = frequency.getPropertyIds();
			bytes = property.getIsactive().equals(1) ? Utils.addId(bytes, property.getId()) : Utils.removeId(bytes,
					property.getId());
			if (bytes != null) {
				frequency.setPropertyIds(bytes);
				emailfrequenciesDAOProvider.get().saveOrUpdate(frequency);
			}
		}
	}

	@Override
	public void undo(SaveProperty action, SavePropertyResult result, ExecutionContext context) throws ActionException {
		// TODO Auto-generated method stub

	}
}
