package org.jchlabs.gharonda.handlers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.jchlabs.gharonda.common.Utils;
import org.jchlabs.gharonda.domain.model.Emailfrequencies;
import org.jchlabs.gharonda.domain.pom.dao.iface.ContentholderDAO;
import org.jchlabs.gharonda.domain.pom.dao.iface.EmailfrequenciesDAO;
import org.jchlabs.gharonda.shared.rpc.DeleteProperty;
import org.jchlabs.gharonda.shared.rpc.DeletePropertyResult;
import org.jchlabs.gharonda.shared.rpc.Status;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

public class DeletePropertyHandler extends AbstractSessionHandler<DeleteProperty, DeletePropertyResult> {

	private Provider<ContentholderDAO> contentholderDAOProvider;
	private final Provider<EmailfrequenciesDAO> emailfrequenciesDAOProvider;

	@Inject
	public DeletePropertyHandler(Log logger, Provider<HttpSession> sessionProvider,
			Provider<ContentholderDAO> contentholderDAOProvider,
			Provider<EmailfrequenciesDAO> emailfrequenciesDAOProvider) {
		super(logger, sessionProvider);
		this.contentholderDAOProvider = contentholderDAOProvider;
		this.emailfrequenciesDAOProvider = emailfrequenciesDAOProvider;
	}

	@Override
	protected DeletePropertyResult executeInternal(DeleteProperty action, ExecutionContext context)
			throws ActionException {
		ContentholderDAO cDAO = contentholderDAOProvider.get();

		if (logger.isDebugEnabled()) {
			logger.debug("The id to be deleted = " + action.getPid());
		}
		Integer id = action.getPid();
		Status status = Status.FAIL;
		System.out.println("The input id in DeleteProperty =" + id);
		try {
			cDAO.delete(id);
			postDeleteproperties(id);
			status = Status.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new DeletePropertyResult(status);
	}

	@Override
	public Class<DeleteProperty> getActionType() {
		return DeleteProperty.class;
	}

	private void postDeleteproperties(Integer id) {
		List<Emailfrequencies> frequencies = emailfrequenciesDAOProvider.get().findAll();
		for (Emailfrequencies frequency : frequencies) {
			byte[] bytes = frequency.getPropertyIds();
			if (bytes != null) {
				bytes = Utils.removeId(bytes, id);
				frequency.setPropertyIds(bytes);
				emailfrequenciesDAOProvider.get().saveOrUpdate(frequency);
			}
		}
	}

	@Override
	public void undo(DeleteProperty action, DeletePropertyResult result, ExecutionContext context)
			throws ActionException {
		// TODO Auto-generated method stub

	}

}
