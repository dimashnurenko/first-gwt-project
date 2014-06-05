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
package com.basicProject.client.dialogWindow;

import com.basicProject.client.entity.Employee;
import com.basicProject.client.mvp.CallBack;
import com.google.inject.Inject;

/**
 * @author Dmitry Shnurenko
 */
public class DialogWindowPresenter implements DialogWindowView.ActionDelegate {

    private final DialogWindowView view;
    private       CallBack         callBack;

    @Inject
    public DialogWindowPresenter(DialogWindowView view) {
        this.view = view;
        this.view.setDelegate(this);
    }

    @Override
    public void onClickAddEmployee() {

        String firstName = view.getFirstName();
        String middleName = view.getMiddleName();
        String lastName = view.getLastName();

        Employee newEmpl = new Employee(firstName, middleName, lastName);

        callBack.onChangeTableOfEmployee(newEmpl);

        view.hideWindow();
    }

    @Override
    public void onClickCancel() {
        view.hideWindow();
    }

    public void showWindow(CallBack callBack) {
        this.callBack = callBack;

        view.setFirstName("");
        view.setMiddleName("");
        view.setLastName("");

        view.showWindow();
    }

    public void showWindowForEdit(CallBack callBack, Employee employee) {
        this.callBack = callBack;

        view.setFirstName(employee.getFirstName());
        view.setMiddleName(employee.getMiddleName());
        view.setLastName(employee.getLastName());

        view.showWindow();
    }
}
