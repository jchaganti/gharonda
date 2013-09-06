package org.jchlabs.gharonda.content;

/*
 * The interface defines the methods to be implemented for the Content Service framework that it would use while storing
 * the content to database. For e.g PropertyContentSvcHelper would know how to save an object of type "Properties"
 */
public interface ContentSvcHelperIFace {

	/*
	 * save method performs the save of the current context object with a new Contentitem
	 */
	public boolean save(String path);

	/*
	 * Retrieves the data stored against the current context attribute
	 */
	public String retrieve();

	/*
	 * Deletes the current Contentitem
	 */
	public boolean delete();

	/*
	 * Resets the content items so that temp content items get deleted
	 */
	public boolean reset();

	/*
	 * Updates the content items so that temp content items become current content items
	 */
	public boolean update();

	/*
	 * This gets the prefix to be applied before the content path
	 */
	public String getContentPathPrefix();
}
