package org.jchlabs.gharonda.content;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.jchlabs.gharonda.common.AppThumbnailsIFace;
import org.jchlabs.gharonda.common.Thumbnail;
import org.jchlabs.gharonda.common.ThumbnailParams;
import org.jchlabs.gharonda.domain.model.Contentholder;
import org.jchlabs.gharonda.domain.model.Contentitems;
import org.jchlabs.gharonda.domain.model.SearchCriteriaIFace;
import org.jchlabs.gharonda.domain.model.StringEqualSearchCriteria;
import org.jchlabs.gharonda.domain.pom.dao.iface.ContentholderDAO;
import org.jchlabs.gharonda.domain.pom.dao.iface.ContentitemsDAO;
import org.jchlabs.gharonda.search.SearchService;
import org.jchlabs.gharonda.search.SearchServiceIFace;


public class BaseContentSvcHelper implements ContentSvcHelperIFace {

	protected String contextContentName;
	protected Integer id;
	protected Contentholder contextHolder;
	protected AppThumbnailsIFace tnParamsImpl;
	// protected static String fileSep = System.getProperty("file.separator");
	protected static String fileSep = "/";
	private static final String ATTR_NAME = "Name";
	private static final String IMAGE_ROOT_DIR = "property_images";
	private static final int IMAGE_QUALITY = 200;
	private ContentholderDAO holderDAO;
	private ContentitemsDAO itemDAO;

	public BaseContentSvcHelper(String _contextContentName, Contentholder _contextHolder,
			AppThumbnailsIFace _tnParamsImpl, ContentholderDAO holderDAO, ContentitemsDAO itemDAO) throws Exception {
		validate(_contextContentName, _contextHolder);
		this.contextContentName = _contextContentName;
		this.contextHolder = _contextHolder;
		this.holderDAO = holderDAO;
		this.itemDAO = itemDAO;
		this.tnParamsImpl = _tnParamsImpl;
	}

	public BaseContentSvcHelper(Contentholder _contextHolder, AppThumbnailsIFace tnParamsImpl,
			ContentholderDAO holderDAO, ContentitemsDAO itemDAO) {
		this.id = _contextHolder.getId();
		this.holderDAO = holderDAO;
		this.itemDAO = itemDAO;
		this.contextHolder = _contextHolder;
		this.tnParamsImpl = tnParamsImpl;
	}

	public BaseContentSvcHelper(Integer id, AppThumbnailsIFace tnParamsImpl, ContentholderDAO holderDAO,
			ContentitemsDAO itemDAO) {
		this(holderDAO.get(id), tnParamsImpl, holderDAO, itemDAO);
	}

	public String getContentPathPrefix() {
		String prefix = IMAGE_ROOT_DIR + fileSep + contextHolder.getUser().getId() + fileSep + contextHolder.getId()
				+ fileSep;
		return prefix;
	}

	public boolean save(String path) {
		boolean result = false;
		Contentitems item = getCurrentContentItem();
		if (item == null && path != null)
			item = new Contentitems();

		if (item != null) {
			// TODO - Need to investigate why there is already a
			// some other reference of the item which doesn't
			// allow me to save the "item" that I obtained above
			if (item.getId() != null)
				item = itemDAO.get(item.getId());
			item.setData(path);
			item.setName(contextContentName);
			itemDAO.saveOrUpdate(item);
			contextHolder.addTocontentitems(item);
			holderDAO.saveOrUpdate(contextHolder);
			result = true;
		}
		return result;
	}

	public String retrieve() {
		String path = null;
		Contentitems item = getCurrentContentItem();
		if (item != null) {
			path = item.getData();
		}
		return path;
	}

	public boolean delete() {
		Contentitems item = getCurrentContentItem();
		return deleteContentItem(item);
	}

	public boolean reset() {
		Set<Contentitems> thisSideContentItems = contextHolder.getContentitems();
		// Delete all the content items
		// TODO - Try deleting the entire directory
		for (Contentitems thisSideContentItem : thisSideContentItems) {
			deleteContentItem(thisSideContentItem);
		}
		return true;
	}

	public boolean update() {
		Integer otherSideId = contextHolder.getOtherSideId();
		Contentholder otherSideHolder = holderDAO.get(otherSideId);
		Set<Contentitems> otherSideContentItems = otherSideHolder.getContentitems();
		// Delete all the content items of the other side(Original copy)
		for (Contentitems otherSideContentItem : otherSideContentItems) {
			deleteContentItem(otherSideContentItem);
		}

		return true;
	}

	public void store(InputStream in) {
		ThumbnailParams[] tnParams = tnParamsImpl.getAppThumbnailParams();
		if (in != null && tnParams != null && tnParams.length > 0) {
			// TOD- Needs refactoring of the class Thumbnail
			Thumbnail tn = new Thumbnail();
			for (ThumbnailParams tnParam : tnParams) {
				int width = tnParam.getWidth();
				int height = tnParam.getHeight();
				String suffix = tnParam.getSuffix();
				String fileName = tnParamsImpl.getRootPath() + fileSep + getContentPathPrefix() + contextContentName
						+ "_" + suffix + ".jpg";
				try {
					File dir = new File(tnParamsImpl.getRootPath() + fileSep + getContentPathPrefix());
					boolean dirExists = (!dir.exists()) ? new File(tnParamsImpl.getRootPath() + fileSep
							+ getContentPathPrefix()).mkdirs() : true;
					if (dirExists) {
						File file = new File(fileName);
						boolean fileExists = (!file.exists()) ? file.createNewFile() : true;
						if (fileExists) {
							FileOutputStream out = new FileOutputStream(file);
							byte[] content = tn.createThumbArray(in, width, height, IMAGE_QUALITY);
							out.write(content);
							out.flush();
							out.close();
						} else {
							throw (new Exception("The file could not be created"));
						}
					}

				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			// save the file name to db.
			save(getContentPathPrefix());
		}

	}

	private void validate(String _contextContentName, Contentholder _contextHolder) throws Exception {
		if (_contextContentName == null || _contextHolder == null)
			throw new Exception("The contextAttribute or _contextHolder is null");
	}

	private List<Object> getAllContentItems() {

		Set<Contentitems> itemsSet = contextHolder.getContentitems();
		List<Object> itemsList = new ArrayList<Object>(itemsSet);
		return itemsList;

	}

	private Contentitems getCurrentContentItem() {
		Contentitems item = null;
		List<Object> itemsList = getAllContentItems();

		SearchServiceIFace searchSvc = new SearchService(Contentitems.class,
				(org.jchlabs.gharonda.domain.pom.dao.ContentitemsDAO) itemDAO);
		List<SearchCriteriaIFace> cList = new ArrayList<SearchCriteriaIFace>();
		StringEqualSearchCriteria strCriteria = new StringEqualSearchCriteria(ATTR_NAME, contextContentName);
		cList.add(strCriteria);
		List<Object> objects = searchSvc.search(itemsList, cList);
		if (objects != null && objects.size() > 0) {
			Object o = objects.get(0);
			if (o instanceof Contentitems) {
				item = (Contentitems) o;
			}
		}

		return item;
	}

	private boolean deleteContentItem(Contentitems item) {
		boolean result = true;
		if (item != null) {
			itemDAO.delete(item);
			String path = item.getData();
			if (tnParamsImpl != null) {
				ThumbnailParams[] tnParams = tnParamsImpl.getAppThumbnailParams();
				if (tnParams != null) {
					for (ThumbnailParams tnParam : tnParams) {
						String fileName = path + fileSep + contextContentName + "_" + tnParam.getSuffix();
						File target = new File(fileName);
						if (target != null && target.exists()) {
							result = target.delete() && result;
						} else {
							result = false; // target not found
						}
					}
				}

			}

		} else {
			result = false; // item not found
		}
		return result;
	}
}
