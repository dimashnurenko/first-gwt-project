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
package com.basicProject.client.mainWindow;

import com.basicProject.client.Localization;
import com.basicProject.client.entity.Employee;
import com.basicProject.client.entity.Note;
import com.gargoylesoftware.htmlunit.javascript.host.HashChangeEvent;
import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasAllMouseHandlers;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.inject.Inject;

import java.util.List;


/**
 * @author Dmitry Shnurenko
 */
public class MainWindowViewImpl extends Composite implements MainWindowView {

    interface MainWindowImplUiBinder extends UiBinder<Widget, MainWindowViewImpl> {
    }

    @UiField(provided = true)
    CellTable<Employee> tableOfEmployees;
    @UiField
    Button              addButton;
    @UiField
    Button              editButton;
    @UiField
    Button              removeButton;
    @UiField
    Label               textBox;
    @UiField
    Button              showNotes;
    @UiField
    Button              registerButton;

    private ButtonCell addNoteButton;

    private ActionDelegate delegate;

    @Inject
    public MainWindowViewImpl(MainWindowImplUiBinder ourUiBinder, Localization localization) {
        this.tableOfEmployees = createTable(localization);
        initWidget(ourUiBinder.createAndBindUi(this));
    }

    private CellTable<Employee> createTable(final Localization local) {
        CellTable<Employee> table = new CellTable<>();

        TextColumn<Employee> firstName = new TextColumn<Employee>() {
            @Override
            public String getValue(Employee object) {
                return object.getFirstName();
            }
        };

        TextColumn<Employee> middleName = new TextColumn<Employee>() {
            @Override
            public String getValue(Employee object) {
                return object.getMiddleName();
            }
        };

        TextColumn<Employee> lastName = new TextColumn<Employee>() {
            @Override
            public String getValue(Employee object) {
                return object.getLastName();
            }
        };

        addNoteButton = new ButtonCell();

        Column<Employee, String> addNote = new Column<Employee, String>(addNoteButton) {
            @Override
            public String getValue(Employee object) {
                return local.addNote();
            }
        };

        addNote.setFieldUpdater(new FieldUpdater<Employee, String>() {
            @Override
            public void update(int index, Employee object, String value) {
                delegate.onAddNoteButtonClicked();
            }
        });

        table.addColumn(firstName, local.firstName());
        table.addColumn(middleName, local.middleName());
        table.addColumn(lastName, local.lastName());
        table.addColumn(addNote, local.listOfNotes());

        final SingleSelectionModel<Employee> selectionModel = new SingleSelectionModel<>();
        selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
            @Override
            public void onSelectionChange(SelectionChangeEvent event) {
                Employee selectedObject = selectionModel.getSelectedObject();
                delegate.onSelectedEmployee(selectedObject);
            }
        });
        table.setSelectionModel(selectionModel);

        return table;
    }

    @Override
    public void setDelegate(ActionDelegate delegate) {
        this.delegate = delegate;
    }

    @UiHandler("addButton")
    void onAddButtonClicked(ClickEvent event) {
        delegate.onAddButtonClicked();
    }

    @UiHandler("editButton")
    void onEditButtonClicked(ClickEvent event) {
        delegate.onEditButtonClicked();
    }

    @UiHandler("removeButton")
    void onRemoveButtonClicked(ClickEvent event) {
        delegate.onRemoveButtonClicked();
    }

    @UiHandler("showNotes")
    void onShowNotesButtonClicked(ClickEvent event){
        delegate.onShowNotesButtonClicked();
    }

    @UiHandler("registerButton")
    void onRegistrationButtonClicked(ClickEvent event){
        delegate.onRegistrationButtonClicked();
    }

    @Override
    public void setEmployeesList(List<Employee> employees) {
        tableOfEmployees.setRowData(employees);
    }

    @Override
    public void setExceptionMessage(String message) {
        textBox.setText(message);
    }
}