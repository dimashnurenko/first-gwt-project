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
package com.basicProject.client.registrationWindow;

import com.basicProject.client.eventbus.event.BackButtonEvent;
import com.basicProject.client.eventbus.event.RegistrationEvent;
import com.basicProject.client.eventbus.event.ShowRegisterUsersEvent;
import com.basicProject.client.eventbus.event.ShowTextEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

/**
 * @author Dmitry Shnurenko
 */
public class RegistrationWindowViewImpl extends Composite implements RegistrationWindowView {

    interface RegistrationWindowViewImplUiBinder extends UiBinder<Widget, RegistrationWindowViewImpl> {
    }

    @UiField
    TextBox   loginTextBox;
    @UiField
    TextBox   emailTextBox;
    @UiField
    TextBox   passwordTextBox;
    @UiField
    Button    registerButton;
    @UiField
    Button    cancel;
    @UiField
    Button    showUsersButton;
    @UiField
    Hyperlink showText;
    @UiField
    Label     textLabel;

    private final EventBus       eventBus;
    private       ActionDelegate delegate;

    @Inject
    public RegistrationWindowViewImpl(RegistrationWindowViewImplUiBinder ourUiBinder,
                                      EventBus eventBus) {

        this.eventBus = eventBus;
        initWidget(ourUiBinder.createAndBindUi(this));
    }


    @UiHandler("registerButton")
    void onRegisterButtonClicked(ClickEvent event) {
        eventBus.fireEvent(new RegistrationEvent());
    }

    @UiHandler("showUsersButton")
    void onShowUsersButtonClicked(ClickEvent event) {
        eventBus.fireEvent(new ShowRegisterUsersEvent());
    }

    @UiHandler("cancel")
    void onBackButtonClicked(ClickEvent event) {
        eventBus.fireEvent(new BackButtonEvent());
    }

    @UiHandler("showText")
    void onShowTextHyperLinkClicked(ClickEvent event) {
        eventBus.fireEvent(new ShowTextEvent());
    }

    @Override
    public void setDelegate(ActionDelegate delegate) {
        //do nothing
    }

    public String getLogin() {
        return loginTextBox.getText();
    }

    public void setLogin(String login) {
        loginTextBox.setText(login);
    }

    public String getEmail() {
        return emailTextBox.getText();
    }

    public void setEmail(String email) {
        emailTextBox.setText(email);
    }

    public String getPassword() {
        return passwordTextBox.getText();
    }

    public void setPassword(String password) {
        passwordTextBox.setText(password);
    }

    public void setText(String text){
        textLabel.setText(text);
    }
}