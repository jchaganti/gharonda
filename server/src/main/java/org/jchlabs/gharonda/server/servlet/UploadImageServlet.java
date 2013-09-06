package org.jchlabs.gharonda.server.servlet;

import gwtupload.server.UploadAction;
import gwtupload.server.exceptions.UploadActionException;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.logging.Log;
import org.jchlabs.gharonda.common.GharondaThumbnails;
import org.jchlabs.gharonda.content.BaseContentSvcHelper;
import org.jchlabs.gharonda.domain.model.Contentholder;
import org.jchlabs.gharonda.domain.pom.dao.iface.ContentholderDAO;
import org.jchlabs.gharonda.domain.pom.dao.iface.ContentitemsDAO;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class UploadImageServlet extends UploadAction {

	private static final long serialVersionUID = 4936687307133529124L;

	private Log logger;
	private Provider<ContentholderDAO> contentholderDAOProvider;
	private Provider<ContentitemsDAO> itemDAOProvider;

	@Inject
	public UploadImageServlet(Log logger, Provider<ContentholderDAO> contentholderDAOProvider,
			Provider<ContentitemsDAO> itemDAOProvider) {
		this.logger = logger;
		this.contentholderDAOProvider = contentholderDAOProvider;
		this.itemDAOProvider = itemDAOProvider;
	}

	@Override
	public String executeAction(HttpServletRequest request, List<FileItem> sessionFiles) throws UploadActionException {
		if (logger.isDebugEnabled()) {
			logger.debug("The id = " + request.getParameter("pid") + " imageNum = " + request.getParameter("imageNum"));
		}
		ContentholderDAO cDAO = contentholderDAOProvider.get();
		ContentitemsDAO itemDAO = itemDAOProvider.get();
		String idStr = request.getParameter("pid");
		String imageNum = request.getParameter("imageNum");
		Integer id = Integer.parseInt(idStr);
		Contentholder holder = cDAO.get(id);
		GharondaThumbnails stn = new GharondaThumbnails(getServletContext().getRealPath("/"));
		BaseContentSvcHelper helper = null;
		try {
			helper = new BaseContentSvcHelper(imageNum, holder, stn, cDAO, itemDAO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (FileItem item : sessionFiles) {
			try {
				helper.store(item.getInputStream());
				break;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		removeSessionFileItems(request, true);
		return "";
	}

	@Override
	public void removeItem(HttpServletRequest request, FileItem item) throws UploadActionException {

	}
}
