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

import com.basicProject.client.entity.Employee;
import com.basicProject.client.entity.Note;
import com.basicProject.client.mvp.View;
import com.google.gwt.user.client.ui.HasOneWidget;

import java.util.List;

/**
 * @author Dmitry Shnurenko
 */
public interface MainWindowView extends View<MainWindowView.ActionDelegate> {

    public interface ActionDelegate {

        void onAddButtonClicked();

        void onEditButtonClicked();

        void onRemoveButtonClicked();

        void onAddNoteButtonClicked();

        void onShowNotesButtonClicked();

        void onRegistrationButtonClicked();

        void onSelectedEmployee(Employee employee);

        void go(HasOneWidget widget);

    }

    void setEmployeesList(List<Employee> list);

    void setExceptionMessage(String message);

}
