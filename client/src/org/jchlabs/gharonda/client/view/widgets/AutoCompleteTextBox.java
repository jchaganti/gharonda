package org.jchlabs.gharonda.client.view.widgets;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.KeyboardListener;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

@SuppressWarnings("deprecation")
public class AutoCompleteTextBox extends TextBox implements KeyboardListener, ChangeHandler, ClickHandler {

	public interface SelectionCompleteHandler {
		boolean onComplete(String selection);
	}

	protected PopupPanel choicesPopup = new PopupPanel(true);
	protected ListBox choices = new ListBox();
	protected CompletionItems items;
	protected boolean visible = false;
	private boolean selectionCompleted = false;

	private SelectionCompleteHandler handler;

	/**
	 * Default Constructor
	 * 
	 */
	public AutoCompleteTextBox() {
		super();
		this.addKeyboardListener(this);
		choices.addChangeHandler(this);
		choices.addClickHandler(this);
		this.setStyleName("AutoCompleteTextBox");

		choicesPopup.add(choices);
		choicesPopup.addStyleName("AutoCompleteChoices");
		choices.setStyleName("list");
		RootPanel.get().add(choicesPopup);
		choicesPopup.hide();
	}

	public AutoCompleteTextBox(CompletionItems items, SelectionCompleteHandler handler) {
		this();
		this.items = items;
		this.handler = handler;
	}

	/**
	 * Returns the used CompletionItems object
	 * 
	 * @return CompletionItems implementation
	 */
	public CompletionItems getCompletionItems() {
		return this.items;
	}

	public boolean isSelectionCompleted() {
		return selectionCompleted;
	}

	/**
	 * A mouseclick in the list of items
	 */
	public void onChange(Widget arg0) {
		complete();
	}

	public void onClick(Widget arg0) {
		complete();
	}

	/**
	 * Not used at all
	 */
	public void onKeyDown(Widget arg0, char arg1, int arg2) {
	}

	/**
	 * Not used at all
	 */
	public void onKeyPress(Widget arg0, char arg1, int arg2) {
	}

	/**
	 * A key was released, start autocompletion
	 */
	public void onKeyUp(Widget arg0, char arg1, int arg2) {
		if (arg1 == KEY_DOWN) {
			int selectedIndex = choices.getSelectedIndex();
			selectedIndex++;
			if (selectedIndex > choices.getItemCount()) {
				selectedIndex = 0;
			}
			choices.setSelectedIndex(selectedIndex);

			return;
		}

		if (arg1 == KEY_UP) {
			int selectedIndex = choices.getSelectedIndex();
			selectedIndex--;
			if (selectedIndex < 0) {
				selectedIndex = choices.getItemCount();
			}
			choices.setSelectedIndex(selectedIndex);

			return;
		}

		if (arg1 == KEY_ENTER) {
			if (visible) {
				complete();
			}

			return;
		}

		if (arg1 == KEY_ESCAPE) {
			choices.clear();
			choicesPopup.hide();
			visible = false;

			return;
		}

		String text = this.getText();
		String[] matches = new String[] {};
		if (text.length() > 0) {
			matches = items.getCompletionItems(text);
		}

		if (matches.length > 0) {
			choices.clear();

			for (int i = 0; i < matches.length; i++) {
				choices.addItem((String) matches[i]);
			}

			// if there is only one match and it is what is in the
			// text field anyways there is no need to show autocompletion
			if (matches.length == 1 && matches[0].compareTo(text) == 0) {
				choicesPopup.hide();
			} else {
				choices.setSelectedIndex(0);
				choices.setVisibleItemCount(matches.length + 1);

				choicesPopup.show();
				visible = true;
				choicesPopup.setPopupPosition(this.getAbsoluteLeft(), this.getAbsoluteTop() + this.getOffsetHeight());
				// choicesPopup.setWidth(this.getOffsetWidth() + "px");
				choices.setWidth(this.getOffsetWidth() + "px");
			}

		} else {
			visible = false;
			choicesPopup.hide();
		}
	}

	/**
	 * Sets an "algorithm" returning completion items You can define your own way how the textbox retrieves
	 * autocompletion items by implementing the CompletionItems interface and setting the according object
	 * 
	 * @see SimpleAutoCompletionItem
	 * @param items CompletionItem implementation
	 */
	public void setCompletionItems(CompletionItems items) {
		this.items = items;
	}

	public void setSelectionCompleted(boolean selectionCompleted) {
		this.selectionCompleted = selectionCompleted;
	}

	// add selected item to textbox
	protected void complete() {
		if (choices.getItemCount() > 0) {
			this.setText(choices.getItemText(choices.getSelectedIndex()));
		}

		choices.clear();
		choicesPopup.hide();
		if (handler != null) {
			handler.onComplete(this.getValue());
		}
		selectionCompleted = true;
	}

	@Override
	public void onChange(ChangeEvent event) {
		complete();

	}

	@Override
	public void onClick(ClickEvent event) {
		complete();
	}
}