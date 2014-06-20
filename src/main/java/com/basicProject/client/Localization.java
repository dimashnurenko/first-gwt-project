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
package com.basicProject.client;

import com.google.gwt.i18n.client.Constants;

/**
 * @author Dmitry Shnurenko
 */
public interface Localization extends Constants {

    String firstName();

    String middleName();

    String lastName();

    String addButton();

    String cancelButton();

    String removeButton();

    String editButton();

    String error();

    String textNote();

    String titleNote();

    String listOfNotes();

    String showNotes();

    String okButton();

    String addNote();

    String registerButton();

    String login();

    String email();

    String password();

    String showUsers();

    String backButton();

    String showExternalText();

    String errorLogin();

    String errorEmail();

    String errorPassword();

    String employeeHasMessage();
}
