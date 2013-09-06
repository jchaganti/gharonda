package org.jchlabs.gharonda.handlers;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.dozer.DozerBeanMapperSingletonWrapper;
import org.jchlabs.gharonda.domain.model.Properties;
import org.jchlabs.gharonda.domain.model.PropertiesDTO;
import org.jchlabs.gharonda.domain.pom.dao.iface.ContentholderDAO;
import org.jchlabs.gharonda.shared.rpc.CompareProperties;
import org.jchlabs.gharonda.shared.rpc.ComparePropertiesResult;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

/**
 * Handler for handle logout requests
 * 
 */
public class ComparePropertiesHandler implements ActionHandler<CompareProperties, ComparePropertiesResult> {

	private Provider<ContentholderDAO> contentholderDAOProvider;

	@Inject
	public ComparePropertiesHandler(Log logger, Provider<HttpSession> provider,
			Provider<ContentholderDAO> contentholderDAOProvider) {
		this.contentholderDAOProvider = contentholderDAOProvider;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.customware.gwt.dispatch.server.ActionHandler#getActionType()
	 */
	public Class<CompareProperties> getActionType() {
		return CompareProperties.class;
	}

	@Override
	public ComparePropertiesResult execute(CompareProperties action, ExecutionContext arg1) throws ActionException {
		ContentholderDAO cDAO = contentholderDAOProvider.get();
		System.out.println("The compare properties input size =" + action.getPids().size());
		List<List<Integer>> pidsList = action.getPids();
		List<List<PropertiesDTO>> pList = new LinkedList<List<PropertiesDTO>>();
		for (int i = action.getFirstResult(); i < action.getNumRows(); i++) {
			List<Integer> pids = pidsList.get(i);
			List<PropertiesDTO> _pList = new LinkedList<PropertiesDTO>();
			for (Integer pid : pids) {
				Properties property = (Properties) cDAO.get(pid);
				PropertiesDTO pDTO = DozerBeanMapperSingletonWrapper.getInstance().map(property, PropertiesDTO.class);
				_pList.add(pDTO);
			}
			pList.add(_pList);
		}
		System.out.println("The compare properties output size =" + pList.size());
		return new ComparePropertiesResult(pList);
	}

	@Override
	public void undo(CompareProperties action, ComparePropertiesResult result, ExecutionContext context)
			throws ActionException {
		// TODO Auto-generated method stub

	}
}
