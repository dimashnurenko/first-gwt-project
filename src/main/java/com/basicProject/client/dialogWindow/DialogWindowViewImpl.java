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
package com.basicProject.client.dialogWindow;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Dmitry Shnurenko
 */
public class DialogWindowViewImpl extends DialogBox implements DialogWindowView {

    interface DialogWindowViewImplUiBinder extends UiBinder<Widget, DialogWindowViewImpl> { }

    private static DialogWindowViewImplUiBinder ourUiBinder = GWT.create(DialogWindowViewImplUiBinder.class);

    @UiField
    TextBox firstName;
    @UiField
    TextBox middleName;
    @UiField
    TextBox lastName;
    @UiField
    Button  button;
    @UiField
    Button  cancelButton;

    private ActionDelegate delegate;


    public DialogWindowViewImpl() {
        add(ourUiBinder.createAndBindUi(this));
    }

    @Override
    public void setDelegate(ActionDelegate delegate) {
        this.delegate = delegate;
    }

    @UiHandler("button")
    void onClickAddButton(ClickEvent event) {
        delegate.onClickAddEmployee();
    }

    @UiHandler("cancelButton")
    void onClickCancelButton(ClickEvent event) {
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
    public String getFirstName() {
        return firstName.getText();
    }

    @Override
    public void setFirstName(String fName) {
        firstName.setText(fName);
    }

    @Override
    public String getMiddleName() {
        return middleName.getText();
    }

    @Override
    public void setMiddleName(String mName) {
        middleName.setText(mName);
    }

    @Override
    public String getLastName() {
        return lastName.getText();
    }

    @Override
    public void setLastName(String lName) {
        lastName.setText(lName);
    }

}