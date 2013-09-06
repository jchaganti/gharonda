package org.jchlabs.gharonda.handlers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.jchlabs.gharonda.common.Utils;
import org.jchlabs.gharonda.domain.model.Emailfrequencies;
import org.jchlabs.gharonda.domain.model.Notifierprofiles;
import org.jchlabs.gharonda.domain.model.NumberSearchCriteria;
import org.jchlabs.gharonda.domain.model.SearchCriteriaIFace;
import org.jchlabs.gharonda.domain.model.Serviceproviderdetails;
import org.jchlabs.gharonda.domain.model.StringEqualSearchCriteria;
import org.jchlabs.gharonda.domain.model.Users;
import org.jchlabs.gharonda.domain.pom.dao.iface.EmailfrequenciesDAO;
import org.jchlabs.gharonda.domain.pom.dao.iface.NotifierprofilesDAO;
import org.jchlabs.gharonda.domain.pom.dao.iface.ServiceproviderdetailsDAO;
import org.jchlabs.gharonda.domain.pom.dao.iface.UsersDAO;
import org.jchlabs.gharonda.search.SearchService;
import org.jchlabs.gharonda.shared.rpc.ModifyUser;
import org.jchlabs.gharonda.shared.rpc.ModifyUserResult;
import org.jchlabs.gharonda.shared.rpc.ModifyUserType;
import org.jchlabs.gharonda.shared.rpc.RegistrationStatus;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

/**
 * Handler for login a user via email and password
 * 
 */
public class ModifyUserHandler implements ActionHandler<ModifyUser, ModifyUserResult> {

	private final Log logger;
	private final Provider<HttpSession> sessionProvider;
	private final Provider<UsersDAO> uDAOProvider;
	private final Provider<ServiceproviderdetailsDAO> serviceProviderDAOProvider;
	private final Provider<NotifierprofilesDAO> notifierprofilesProvider;
	private final Provider<EmailfrequenciesDAO> emailfrequenciesDAOProvider;

	@Inject
	public ModifyUserHandler(Log logger, Provider<HttpSession> sessionProvider, Provider<UsersDAO> uDAOProvider,
			Provider<ServiceproviderdetailsDAO> serviceProvider,
			Provider<NotifierprofilesDAO> notifierprofilesProvider,
			Provider<EmailfrequenciesDAO> emailfrequenciesDAOProvider) {

		this.logger = logger;
		this.sessionProvider = sessionProvider;
		this.uDAOProvider = uDAOProvider;
		this.serviceProviderDAOProvider = serviceProvider;
		this.notifierprofilesProvider = notifierprofilesProvider;
		this.emailfrequenciesDAOProvider = emailfrequenciesDAOProvider;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.customware.gwt.dispatch.server.ActionHandler#execute(net.customware .gwt.dispatch.shared.Action,
	 * net.customware.gwt.dispatch.server.ExecutionContext)
	 */
	public ModifyUserResult execute(ModifyUser action, ExecutionContext context) throws ActionException {
		Users user = action.getUser();
		RegistrationStatus status;
		assert (user != null);
		UsersDAO uDAO = uDAOProvider.get();
		Users existingUser = uDAO.load(action.getUser().getId());
		Integer oldFreqtype = -1;
		if (existingUser == null) {
			existingUser = uDAO.get(action.getUser().getId());
		} else {
			((org.jchlabs.gharonda.domain.pom.dao.UsersDAO) uDAO).getSession().refresh(existingUser);
		}

		try {
			System.out.println("ModifyUser: user email name from client =" + user.getEmail());

			if (action.getModType().equals(ModifyUserType.MOD_PASSWD) && action.getOldPassWord() != null
					&& action.getNewPassWord() != null) {
				if (existingUser.getPasswd().equals(action.getOldPassWord())) {
					existingUser.setPasswd(action.getNewPassWord());
					uDAO.saveOrUpdate(existingUser);
					HttpSession session = sessionProvider.get();
					session.setAttribute("user", existingUser);
					status = RegistrationStatus.SUCCESS;
				} else {
					status = RegistrationStatus.PASSWDS_NOT_MATCH;
					existingUser = user;
				}

			} else if (action.getModType().equals(ModifyUserType.MOD_EMAIL) && action.getNewEmail() != null) {
				List<SearchCriteriaIFace> criteriaList = new ArrayList<SearchCriteriaIFace>();
				StringEqualSearchCriteria emailSrchCriteria = new StringEqualSearchCriteria("Email",
						action.getNewEmail());
				criteriaList.add(emailSrchCriteria);
				SearchService service = new SearchService(Users.class, (org.jchlabs.gharonda.domain.pom.dao.UsersDAO) uDAO);
				List<Object> users = service.search(criteriaList);
				Users _user = null;
				if (users.size() > 0) {
					_user = action.getUser();
					logger.debug("The user with email has been already registered. So new mail is not valid "
							+ _user.getEmail());
					status = RegistrationStatus.ALREADY_EXISTS;
					existingUser = user;
				} else {
					existingUser.setEmail(action.getNewEmail());
					uDAO.saveOrUpdate(existingUser);
					HttpSession session = sessionProvider.get();
					session.setAttribute("user", existingUser);
					status = RegistrationStatus.SUCCESS;

				}
			} else if (action.getModType().equals(ModifyUserType.MOD_DETAILS)) {
				if (user.getServiceproviderdetails() != null) {
					Serviceproviderdetails newServiceProvider = user.getServiceproviderdetails();
					ServiceproviderdetailsDAO sDAO = serviceProviderDAOProvider.get();
					if (newServiceProvider.getId() == null) {
						// First time Serviceproviderdetails being created
						sDAO.saveOrUpdate(newServiceProvider);
						existingUser.setServiceproviderdetails(newServiceProvider);

					} else {
						Serviceproviderdetails existingProvider = sDAO.load(newServiceProvider.getId());
						if (existingProvider == null) {
							existingProvider = sDAO.get(newServiceProvider.getId());
						} else {
							((org.jchlabs.gharonda.domain.pom.dao.ServiceproviderdetailsDAO) sDAO).getSession().refresh(
									existingProvider);
						}
						existingProvider.setAddrLine1(newServiceProvider.getAddrLine1());
						existingProvider.setAddrLine2(newServiceProvider.getAddrLine2());
						existingProvider.setCompanyName(newServiceProvider.getCompanyName());
						existingProvider.setBusinessType(newServiceProvider.getBusinessType());
						existingProvider.setSuburb(newServiceProvider.getSuburb());
						existingProvider.setCity(newServiceProvider.getCity());
						existingProvider.setState(newServiceProvider.getState());
						existingProvider.setZip(newServiceProvider.getZip());
						// existingProvider.setPhone(newServiceProvider.getPhone());
						// existingProvider.setFax(newServiceProvider.getFax());
						existingProvider.setWebsite(newServiceProvider.getWebsite());
						sDAO.saveOrUpdate(existingProvider);
					}
				}
				existingUser.setFirstName(user.getFirstName());
				existingUser.setLastName(user.getLastName());
				existingUser.setPhone(user.getPhone());
				existingUser.setCell(user.getCell());
				uDAO.saveOrUpdate(existingUser);
				// uDAO.saveOrUpdate(user);
				HttpSession session = sessionProvider.get();
				session.setAttribute("user", existingUser);
				status = RegistrationStatus.SUCCESS;
				// existingUser = user;
			} else if (action.getModType().equals(ModifyUserType.MOD_NOTIFIER_PROFILE)) {
				Notifierprofiles newNotifierprofiles = null;
				if (user.getNotifierprofiles() != null) {
					newNotifierprofiles = user.getNotifierprofiles();
					NotifierprofilesDAO nDAO = notifierprofilesProvider.get();
					if (newNotifierprofiles.getId() == null) {
						nDAO.saveOrUpdate(newNotifierprofiles);

					} else {
						Notifierprofiles existingNotifierprofiles = nDAO.load(newNotifierprofiles.getId());
						if (existingNotifierprofiles == null) {
							existingNotifierprofiles = nDAO.get(newNotifierprofiles.getId());
						} else {
							((org.jchlabs.gharonda.domain.pom.dao.NotifierprofilesDAO) nDAO).getSession().refresh(
									existingNotifierprofiles);
						}
						oldFreqtype = existingNotifierprofiles.getFrequency();
						existingNotifierprofiles.setState(newNotifierprofiles.getState());
						existingNotifierprofiles.setCity(newNotifierprofiles.getCity());
						existingNotifierprofiles.setSuburb(newNotifierprofiles.getSuburb());
						existingNotifierprofiles.setFrequency(newNotifierprofiles.getFrequency());
						existingNotifierprofiles.setPType(newNotifierprofiles.getPType());
						existingNotifierprofiles.setPriceLVal(newNotifierprofiles.getPriceLVal());
						existingNotifierprofiles.setPriceComparator(newNotifierprofiles.getPriceComparator());
						existingNotifierprofiles.setPriceRVal(newNotifierprofiles.getPriceRVal());
						existingNotifierprofiles.setRoomNoLVal(newNotifierprofiles.getRoomNoLVal());
						existingNotifierprofiles.setRoomNoComparator(newNotifierprofiles.getRoomNoComparator());
						existingNotifierprofiles.setRoomNoRVal(newNotifierprofiles.getRoomNoRVal());
						existingNotifierprofiles.setSftLVal(newNotifierprofiles.getSftLVal());
						existingNotifierprofiles.setSftRVal(newNotifierprofiles.getSftRVal());
						existingNotifierprofiles.setSftComparator(newNotifierprofiles.getSftComparator());
						existingNotifierprofiles.setAmenity1(newNotifierprofiles.getAmenity1());
						existingNotifierprofiles.setAmenity2(newNotifierprofiles.getAmenity2());
						existingNotifierprofiles.setAmenity3(newNotifierprofiles.getAmenity3());
						existingNotifierprofiles.setAmenity4(newNotifierprofiles.getAmenity4());
						nDAO.saveOrUpdate(existingNotifierprofiles);
						newNotifierprofiles = existingNotifierprofiles;
					}
				}
				existingUser.setNotifierprofiles(newNotifierprofiles);
				existingUser.setLastName(user.getLastName());
				uDAO.saveOrUpdate(existingUser);
				if (action.getModType().equals(ModifyUserType.MOD_NOTIFIER_PROFILE)) {
					postSaveOrUpdateNotifierProfile(existingUser, oldFreqtype);
				}
				HttpSession session = sessionProvider.get();
				session.setAttribute("user", existingUser);
				status = RegistrationStatus.SUCCESS;
			} else {
				status = RegistrationStatus.FAIL;
			}
			return new ModifyUserResult(existingUser, status);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Unable to authenticate user " + user);
			throw new ActionException(e);
		}
	}

	private void postSaveOrUpdateNotifierProfile(Users user, Integer oldFreqtype) {
		Integer freqId = user.getNotifierprofiles().getFrequency();
		if (freqId == null) {
			freqId = user.getNotifierprofiles().getFrequency();
		}
		List<SearchCriteriaIFace> cList = new ArrayList<SearchCriteriaIFace>();
		NumberSearchCriteria criteria = new NumberSearchCriteria("FrequencyType", freqId);
		cList.add(criteria);
		SearchService service = new SearchService(Emailfrequencies.class,
				(org.jchlabs.gharonda.domain.pom.dao.EmailfrequenciesDAO) emailfrequenciesDAOProvider.get());
		List<Object> freqList = service.search(cList);
		Emailfrequencies frequency = null;
		if (freqList != null && freqList.size() > 0) {
			frequency = (Emailfrequencies) freqList.get(0);
		} else {
			frequency = new Emailfrequencies();
			frequency.setFrequencyType(freqId);
		}
		if (frequency != null) {
			byte[] bytes = frequency.getUserIds();
			bytes = Utils.addId(bytes, user.getId());
			if (bytes != null) {
				frequency.setUserIds(bytes);
				emailfrequenciesDAOProvider.get().saveOrUpdate(frequency);
			}
			if (oldFreqtype != null && oldFreqtype != -1
					&& oldFreqtype.intValue() != frequency.getFrequencyType().intValue()) {
				cList = new ArrayList<SearchCriteriaIFace>();
				criteria = new NumberSearchCriteria("FrequencyType", oldFreqtype);
				cList.add(criteria);
				freqList = service.search(cList);
				if (freqList != null && freqList.size() > 0) {
					frequency = (Emailfrequencies) freqList.get(0);
					if (frequency.getUserIds() != null) {
						bytes = Utils.removeId(frequency.getUserIds(), user.getId());
						frequency.setUserIds(bytes);
						emailfrequenciesDAOProvider.get().saveOrUpdate(frequency);
					}
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.customware.gwt.dispatch.server.ActionHandler#getActionType()
	 */
	public Class<ModifyUser> getActionType() {
		return ModifyUser.class;
	}

	@Override
	public void undo(ModifyUser action, ModifyUserResult result, ExecutionContext context) throws ActionException {
		// TODO Auto-generated method stub

	}
}
