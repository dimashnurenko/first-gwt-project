/*
 * CODENVY CONFIDENTIAL
 * __________________
 * 
 * [2012] - [2014] Codenvy, S.A. 
 * All Rights Reserved.
 * 
 * NOTICE:  All information contained herein is, and remains
 * the property of Codenvy S.A. and its suppliers,
 * if any.  The intellectual and technical concepts contained
 * herein are proprietary to Codenvy S.A.
 * and its suppliers and may be covered by U.S. and Foreign Patents,
 * patents in process, and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Codenvy S.A..
 */
package com.basicProject.client.noteDialogWindow;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

/**
 * @author Dmitry Shnurenko
 */
public class NoteDialogWindowViewImpl extends DialogBox implements NoteDialogWindowView {

    interface NoteDialogWindowViewImplUiBinder extends UiBinder<Widget, NoteDialogWindowViewImpl> {
    }

    @UiField
    TextBox  title;
    @UiField
    TextArea text;
    @UiField
    Button   addNote;
    @UiField
    Button   cancel;

    private ActionDelegate delegate;

    @Inject
    public NoteDialogWindowViewImpl(NoteDialogWindowViewImplUiBinder ourUiBinder) {
        add(ourUiBinder.createAndBindUi(this));
    }

    @UiHandler("addNote")
    public void onNoteAddButtonClicked(ClickEvent event) {
        delegate.onClickAddNote();
    }

    @UiHandler("cancel")
    public void onCancelButtonClicked(ClickEvent event) {
        delegate.onClickCancel();
    }

    @Override
    public void showWindow() {
        show();
    }

    @Override
    public void hideWindow() {
        hide();
    }

    @Override
    public void setDelegate(ActionDelegate delegate) {
        this.delegate = delegate;
    }

    public String getTitle() {
        return title.getText();
    }

    public void setTitle(String title) {
        this.title.setText(title);
    }

    public String getText() {
        return text.getText();
    }

    public void setText(String text) {
        this.text.setText(text);
    }
}