package org.jchlabs.gharonda.handlers;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.jchlabs.gharonda.common.GharondaThumbnails;
import org.jchlabs.gharonda.content.BaseContentSvcHelper;
import org.jchlabs.gharonda.domain.model.Contentholder;
import org.jchlabs.gharonda.domain.pom.dao.iface.ContentholderDAO;
import org.jchlabs.gharonda.domain.pom.dao.iface.ContentitemsDAO;
import org.jchlabs.gharonda.shared.rpc.DeleteImage;
import org.jchlabs.gharonda.shared.rpc.DeleteImageResult;
import org.jchlabs.gharonda.shared.rpc.Status;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

public class DeleteImageHandler extends AbstractSessionHandler<DeleteImage, DeleteImageResult> {

	private Provider<ContentholderDAO> contentholderDAOProvider;
	private Provider<ContentitemsDAO> itemDAOProvider;

	@Inject
	public DeleteImageHandler(Log logger, Provider<HttpSession> sessionProvider,
			Provider<ContentholderDAO> contentholderDAOProvider, Provider<ContentitemsDAO> itemDAOProvider) {
		super(logger, sessionProvider);
		this.contentholderDAOProvider = contentholderDAOProvider;
		this.itemDAOProvider = itemDAOProvider;
	}

	@Override
	protected DeleteImageResult executeInternal(DeleteImage action, ExecutionContext context) throws ActionException {
		ContentholderDAO cDAO = contentholderDAOProvider.get();
		ContentitemsDAO itemDAO = itemDAOProvider.get();
		if (logger.isDebugEnabled()) {
			logger.debug("The id = " + action.getPid() + " imageNum = " + action.getImageNum());
		}
		Integer id = action.getPid();
		cDAO.load(id);
		Contentholder holder = cDAO.load(id);
		if (holder == null) {
			holder = cDAO.get(id);
		} else {
			((org.jchlabs.gharonda.domain.pom.dao.ContentholderDAO) cDAO).getSession().refresh(holder);
		}

		String imageNum = action.getImageNum();
		GharondaThumbnails stn = new GharondaThumbnails();
		BaseContentSvcHelper helper = null;
		try {
			helper = new BaseContentSvcHelper(imageNum, holder, stn, cDAO, itemDAO);
			helper.delete();
		} catch (Exception e) {
			e.printStackTrace();
			return new DeleteImageResult(Status.FAIL);
		}
		return new DeleteImageResult(Status.SUCCESS);
	}

	@Override
	public Class<DeleteImage> getActionType() {
		return DeleteImage.class;
	}

	@Override
	public void undo(DeleteImage action, DeleteImageResult result, ExecutionContext context) throws ActionException {
		// TODO Auto-generated method stub

	}

}
