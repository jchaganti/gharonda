package org.jchlabs.gharonda.content;

import java.util.Set;

import org.jchlabs.gharonda.common.ContentholderWIPState;
import org.jchlabs.gharonda.common.GharondaThumbnails;
import org.jchlabs.gharonda.domain.model.Contentholder;
import org.jchlabs.gharonda.domain.model.Contentitems;
import org.jchlabs.gharonda.domain.pom.dao.iface.ContentholderDAO;
import org.jchlabs.gharonda.domain.pom.dao.iface.ContentitemsDAO;


public class BaseWIPSvcHelper implements WIPSvcHelperIFace {
	private Integer contextId;
	private Contentholder contextHolder;
	private BaseContentSvcHelper contentHelper;
	private ContentholderDAO holderDAO;
	private ContentitemsDAO itemDAO;

	public BaseWIPSvcHelper(Contentholder holder, ContentholderDAO holderDAO, ContentitemsDAO itemDAO) {
		super();
		this.contextId = holder.getId();
		this.contextHolder = holder;
		this.holderDAO = holderDAO;
		this.itemDAO = itemDAO;
		if (this.contextId != null) {
			this.contentHelper = new BaseContentSvcHelper(this.contextId, new GharondaThumbnails(), holderDAO,
					itemDAO);
		} else {
			this.contentHelper = new BaseContentSvcHelper(holder, new GharondaThumbnails(), holderDAO, itemDAO);
		}

	}

	public Contentholder create(Contentholder holder) {
		Contentholder result = null;
		if (holder != null) {
			holder.setWipState(ContentholderWIPState.NEW.value());
			holder.setOtherSideId(null);
			Integer id = holderDAO.save(holder);
			result = holderDAO.get(id);
		}
		return result;
	}

	public Contentholder checkin() {
		// All the below operations have to happen in a single transaction
		Contentholder result = null;

		if (ContentholderWIPState.WORKING_CPY.value().equals(contextHolder.getWipState())) {
			boolean updated = contentHelper.update();
			if (updated) {
				Integer otherSideId = contextHolder.getOtherSideId();
				Integer newId = contextHolder.getId();
				contextHolder.setId(otherSideId);
				contextHolder.setWipState(ContentholderWIPState.CHECKED_IN.value());
				contextHolder.setOtherSideId(null);
				holderDAO.saveOrUpdate(contextHolder);
				// Delete the other side holder which is Original copy
				holderDAO.delete(holderDAO.get(newId));
				result = contextHolder;
			}

		} else if (ContentholderWIPState.NEW.value().equals(contextHolder.getWipState())) {

			contextHolder.setWipState(ContentholderWIPState.CHECKED_IN.value());
			contextHolder.setOtherSideId(null);
			holderDAO.saveOrUpdate(contextHolder);
			result = contextHolder;
		}

		return result;
	}

	public Contentholder checkout() {

		Contentholder result = null;
		if (!ContentholderWIPState.WORKING_CPY.value().equals(contextHolder.getWipState())) {
			Contentholder newContentHolder = contextHolder;
			Set<Contentitems> items = newContentHolder.getContentitems();
			newContentHolder.setWipState(ContentholderWIPState.WORKING_CPY.value());
			newContentHolder.setOtherSideId(contextId);
			try {
				Integer id = holderDAO.save(newContentHolder);

				Contentholder oldContentHolder = holderDAO.get(contextId);
				if (!oldContentHolder.getWipState().equals(ContentholderWIPState.NEW.value())) {
					oldContentHolder.setWipState(ContentholderWIPState.CHECKED_IN.value());
				}
				oldContentHolder.setOtherSideId(id);
				// Since the above save updates the content items of original
				// copy, let's create new content items of the original and
				// assign it to new original copy
				for (Contentitems item : items) {
					Integer id1 = itemDAO.save(item);
					oldContentHolder.addTocontentitems(itemDAO.get(id1));
				}
				holderDAO.saveOrUpdate(oldContentHolder);
				result = holderDAO.get(id);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return result;
	}

	public Contentholder undoCheckout() {
		Contentholder result = null;
		if (ContentholderWIPState.WORKING_CPY.value().equals(contextHolder.getWipState())) {
			// Reset the content items
			boolean reset = contentHelper.reset();
			if (reset) {
				// Set the state to CHECKED_IN for the Original copy -
				// ContentHolder
				// and set the other side id to null
				Integer otherSideId = contextHolder.getOtherSideId();
				Contentholder otherSideHolder = holderDAO.get(otherSideId);
				if (!otherSideHolder.getWipState().equals(ContentholderWIPState.NEW.value())) {
					otherSideHolder.setWipState(ContentholderWIPState.CHECKED_IN.value());
				}
				otherSideHolder.setOtherSideId(null);
				holderDAO.saveOrUpdate(otherSideHolder);
				// Delete the Working copy context holder
				holderDAO.delete(contextHolder);
				result = otherSideHolder;
			}
		} else if (ContentholderWIPState.NEW.value().equals(contextHolder.getWipState())) {
			holderDAO.delete(contextHolder);
		}
		return result;
	}

}
