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
package com.basicProject.client.showNotesWindow;

import com.basicProject.client.Localization;
import com.basicProject.client.entity.Employee;
import com.basicProject.client.entity.Note;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

import java.util.List;

/**
 * @author Dmitry Shnurenko
 */
public class ShowNotesWindowViewImpl extends DialogBox implements ShowNotesWindowView {

    interface ShowNotesWindowUiBinder extends UiBinder<Widget, ShowNotesWindowViewImpl> {
    }

    @UiField(provided = true)
    CellTable tableOfNotes;
    @UiField
    Button    cancel;

    private ActionDelegate delegate;

    @Inject
    public ShowNotesWindowViewImpl(ShowNotesWindowUiBinder ourUiBinder, Localization localization) {
        this.tableOfNotes = createTable(localization);

        add(ourUiBinder.createAndBindUi(this));
    }

    public CellTable<Note> createTable(Localization localization) {
        CellTable<Note> table = new CellTable<>();

        TextColumn<Note> title = new TextColumn<Note>() {
            @Override
            public String getValue(Note object) {
                return object.getTitle();
            }
        };

        TextColumn<Note> text = new TextColumn<Note>() {
            @Override
            public String getValue(Note object) {
                return object.getText();
            }
        };

        table.addColumn(title, localization.titleNote());
        table.addColumn(text, localization.textNote());
        return table;
    }

    @UiHandler("cancel")
    public void onCancelButtonClicked(ClickEvent event) {
        hide();
    }

    @Override
    public void setDelegate(ActionDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public void showWindow(Employee employee) {
        List<Note> noteList = employee.getListOfNotes();
        tableOfNotes.setRowData(noteList);
        show();
    }

    @Override
    public void hideWindow() {
        hide();
    }
}