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
package com.basicProject.client.showRegisterUsers;

import com.basicProject.client.Localization;
import com.basicProject.client.entity.User;
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
public class ShowRegisterUsersViewImpl extends DialogBox implements ShowRegisterUsersView {

    interface ShowRegisterUsersViewImplUiBinder extends UiBinder<Widget, ShowRegisterUsersViewImpl> {
    }

    @UiField(provided = true)
    CellTable tableOfUsers;
    @UiField
    Button    cancel;

    private ActionDelegate delegate;

    @Inject
    public ShowRegisterUsersViewImpl(ShowRegisterUsersViewImplUiBinder ourUiBinder, Localization localization) {
        this.tableOfUsers = createTable(localization);
        add(ourUiBinder.createAndBindUi(this));
    }

    public CellTable<User> createTable(Localization localization) {
        CellTable<User> table = new CellTable<>();

        TextColumn<User> login = new TextColumn<User>() {
            @Override
            public String getValue(User object) {
                return object.getLogin();
            }
        };

        TextColumn<User> email = new TextColumn<User>() {
            @Override
            public String getValue(User object) {
                return object.getEmail();
            }
        };

        table.addColumn(login, localization.login());
        table.addColumn(email, localization.email());
        return table;
    }

    @UiHandler("cancel")
    void onCancelButtonClicked(ClickEvent event) {
        hide();
    }

    public void hideWindow() {
        hide();
    }

    public void showWindow() {
        show();
    }

    @Override
    public void setDelegate(ActionDelegate delegate) {
        this.delegate = delegate;
    }

    public void showRegisterUsers(List<User> list) {
        tableOfUsers.setRowData(list);
    }
}