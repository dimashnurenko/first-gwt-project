/*
 * Copyright 2014 Codenvy, S.A.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.basicProject.client.mainWindow;

import com.basicProject.client.Localization;
import com.basicProject.client.dialogWindow.DialogWindowPresenter;
import com.basicProject.client.entity.Employee;
import com.basicProject.client.entity.Note;
import com.basicProject.client.mvp.CallBack;
import com.basicProject.client.mvp.CallBackForNote;
import com.basicProject.client.navigator.MainNavigator;
import com.basicProject.client.noteDialogWindow.NoteDialogWindowPresenter;
import com.basicProject.client.showNotesWindow.ShowNotesWindowPresenter;
import com.google.gwt.user.client.ui.HasOneWidget;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dmitry Shnurenko
 */
@Singleton
public class MainWindowPresenter implements MainWindowView.ActionDelegate {

    private final List<Employee>            employees;
    private final MainWindowView            view;
    private final CallBack                  editEmployeeCallBack;
    private final CallBack                  addEmployeeCallBack;
    private final CallBackForNote           callBackForNote;
    private final ShowNotesWindowPresenter  showNotesWindowPresenter;
    private final DialogWindowPresenter     dialogWindowPresenter;
    private final NoteDialogWindowPresenter noteDialogWindowPresenter;
    private final Localization              localization;

    private Employee      selectedEmployee;
    private MainNavigator mainNavigator;

    @Inject
    public MainWindowPresenter(final MainWindowView view,
                               DialogWindowPresenter dialogWindowPresenter,
                               NoteDialogWindowPresenter noteDialogWindowPresenter,
                               final ShowNotesWindowPresenter showNotesWindowPresenter,
                               final Localization localization) {

        this.view = view;
        this.showNotesWindowPresenter = showNotesWindowPresenter;
        this.view.setDelegate(this);
        this.localization = localization;
        this.dialogWindowPresenter = dialogWindowPresenter;
        this.noteDialogWindowPresenter = noteDialogWindowPresenter;
        this.employees = new ArrayList<>();

        addEmployeeCallBack = new CallBack() {
            @Override
            public void onChangeTableOfEmployee(Employee newEmpl) {
                if (employees.contains(newEmpl)) {
                    view.setExceptionMessage(localization.error());
                    //Window.alert(localization.error());
                } else {
                    view.setExceptionMessage("");
                    employees.add(newEmpl);
                    MainWindowPresenter.this.view.setEmployeesList(employees);
                }
            }
        };

        editEmployeeCallBack = new CallBack() {
            @Override
            public void onChangeTableOfEmployee(Employee newEmpl) {
                if (employees.contains(newEmpl)) {
                    view.setExceptionMessage(localization.error());
                    //Window.alert(localization.error());
                } else {
                    view.setExceptionMessage("");
                    employees.remove(selectedEmployee);
                    employees.add(newEmpl);

                    MainWindowPresenter.this.view.setEmployeesList(employees);
                }
            }
        };

        callBackForNote = new CallBackForNote() {
            @Override
            public void onChangeTableOfNotes(Note note) {
                selectedEmployee.getListOfNotes().add(note);
            }
        };
    }

    @Override
    public void onAddNoteButtonClicked() {
        noteDialogWindowPresenter.showWindow(callBackForNote);
    }

    @Override
    public void onAddButtonClicked() {
        dialogWindowPresenter.showWindow(addEmployeeCallBack);
    }

    @Override
    public void onEditButtonClicked() {
        dialogWindowPresenter.showWindowForEdit(editEmployeeCallBack, selectedEmployee);
    }

    @Override
    public void onShowNotesButtonClicked() {
        showNotesWindowPresenter.showNotesSelectedEmployee(selectedEmployee);
    }

    @Override
    public void onRegistrationButtonClicked() {
        mainNavigator.showRegistrationWindow();
    }

    @Override
    public void onRemoveButtonClicked() {
        employees.remove(selectedEmployee);
        view.setEmployeesList(employees);
    }

    @Override
    public void onSelectedEmployee(Employee employee) {
        selectedEmployee = employee;
    }

    public void setMainNavigator(MainNavigator mainNavigator) {
        this.mainNavigator = mainNavigator;
    }

    public void go(HasOneWidget widget) {
        widget.setWidget(view);
    }
}
