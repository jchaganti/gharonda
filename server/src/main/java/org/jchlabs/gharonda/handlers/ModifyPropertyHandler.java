package org.jchlabs.gharonda.handlers;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.dozer.DozerBeanMapperSingletonWrapper;
import org.jchlabs.gharonda.content.BaseWIPSvcHelper;
import org.jchlabs.gharonda.domain.model.Contentholder;
import org.jchlabs.gharonda.domain.model.Properties;
import org.jchlabs.gharonda.domain.model.PropertiesDTO;
import org.jchlabs.gharonda.domain.pom.dao.iface.ContentholderDAO;
import org.jchlabs.gharonda.domain.pom.dao.iface.ContentitemsDAO;
import org.jchlabs.gharonda.shared.rpc.ModifyProperty;
import org.jchlabs.gharonda.shared.rpc.ModifyPropertyResult;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

public class ModifyPropertyHandler extends AbstractSessionHandler<ModifyProperty, ModifyPropertyResult> {

	private Provider<ContentholderDAO> contentholderDAOProvider;
	private Provider<ContentitemsDAO> itemDAOProvider;

	@Inject
	public ModifyPropertyHandler(Log logger, Provider<HttpSession> sessionProvider,
			Provider<ContentholderDAO> contentholderDAOProvider, Provider<ContentitemsDAO> itemDAOProvider) {
		super(logger, sessionProvider);
		this.contentholderDAOProvider = contentholderDAOProvider;
		this.itemDAOProvider = itemDAOProvider;
	}

	@Override
	protected ModifyPropertyResult executeInternal(ModifyProperty action, ExecutionContext context)
			throws ActionException {
		ContentholderDAO cDAO = contentholderDAOProvider.get();
		ContentitemsDAO itemDAO = itemDAOProvider.get();
		if (logger.isDebugEnabled()) {
			logger.debug("The id = " + action.getPid());
		}
		Integer id = action.getPid();

		System.out.println("The input id in ModifyProperty =" + id);
		Properties property = (Properties) cDAO.get(id);
		Contentholder checkedOutHolder = null;
		if (property.getOtherSideId() != null) {
			checkedOutHolder = (Contentholder) cDAO.get(property.getOtherSideId());
		} else {
			BaseWIPSvcHelper helper = new BaseWIPSvcHelper((Contentholder) property, cDAO, itemDAO);
			checkedOutHolder = helper.checkout();
		}

		PropertiesDTO pDTO = DozerBeanMapperSingletonWrapper.getInstance().map((Properties) checkedOutHolder,
				PropertiesDTO.class);
		System.out.println("The output id in ModifyProperty =" + pDTO.getId());
		return new ModifyPropertyResult(pDTO);
	}

	@Override
	public Class<ModifyProperty> getActionType() {
		return ModifyProperty.class;
	}

	@Override
	public void undo(ModifyProperty action, ModifyPropertyResult result, ExecutionContext context)
			throws ActionException {
		// TODO Auto-generated method stub

	}

}
