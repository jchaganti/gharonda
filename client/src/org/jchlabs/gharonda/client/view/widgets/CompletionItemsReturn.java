package org.jchlabs.gharonda.client.view.widgets;

public interface CompletionItemsReturn {
	/**
	 * Handles an array of items. Called by <code>CompletionItems.getCompletionItems</code>
	 * 
	 * @param items The array of completion items
	 */
	public void itemReturn(String[] items);
}
