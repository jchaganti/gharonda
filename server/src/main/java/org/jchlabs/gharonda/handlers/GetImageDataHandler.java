package org.jchlabs.gharonda.handlers;

import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.jchlabs.gharonda.domain.model.Contentholder;
import org.jchlabs.gharonda.domain.pom.dao.iface.ContentholderDAO;
import org.jchlabs.gharonda.domain.pom.dao.iface.ContentitemsDAO;
import org.jchlabs.gharonda.shared.rpc.GetImageData;
import org.jchlabs.gharonda.shared.rpc.GetImageDataResult;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class GetImageDataHandler implements ActionHandler<GetImageData, GetImageDataResult> {

	private Provider<ContentholderDAO> contentholderDAOProvider;
	private Provider<ContentitemsDAO> itemDAOProvider;
	private final Log logger;

	@Inject
	public GetImageDataHandler(Log logger, Provider<HttpSession> sessionProvider,
			Provider<ContentholderDAO> contentholderDAOProvider, Provider<ContentitemsDAO> itemDAOProvider) {
		this.logger = logger;
		this.contentholderDAOProvider = contentholderDAOProvider;
		this.itemDAOProvider = itemDAOProvider;
	}

	@Override
	public GetImageDataResult execute(GetImageData action, ExecutionContext context) throws ActionException {
		ContentholderDAO cDAO = contentholderDAOProvider.get();
		ContentitemsDAO itemDAO = itemDAOProvider.get();
		if (logger.isDebugEnabled()) {
			logger.debug("The id = " + action.getPid());
		}
		Integer id = action.getPid();
		String imageNum = action.getImageNum();
		System.out.println("The id in GetImageData =" + id);
		System.out.println("The imageNum in imageNum =" + imageNum);
		Contentholder holder = cDAO.get(id);

		Set<org.jchlabs.gharonda.domain.model.Contentitems> items = holder.getContentitems();
		Iterator it = items.iterator();
		String path = null;
		while (it.hasNext()) {
			org.jchlabs.gharonda.domain.model.Contentitems item = (org.jchlabs.gharonda.domain.model.Contentitems) it.next();
			if (item.getName().equals(imageNum)) {
				path = item.getData() + imageNum;
				break;
			}
		}
		return new GetImageDataResult(path);
	}

	@Override
	public Class<GetImageData> getActionType() {
		return GetImageData.class;
	}

	@Override
	public void undo(GetImageData action, GetImageDataResult result, ExecutionContext context) throws ActionException {
		// TODO Auto-generated method stub

	}

}
