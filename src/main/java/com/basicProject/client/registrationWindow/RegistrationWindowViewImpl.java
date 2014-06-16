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

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
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
    TextBox loginTextBox;
    @UiField
    TextBox emailTextBox;
    @UiField
    TextBox passwordTextBox;
    @UiField
    Button  registerButton;
    @UiField
    Button  cancel;

    @Inject
    public RegistrationWindowViewImpl(RegistrationWindowViewImplUiBinder ourUiBinder) {
       initWidget(ourUiBinder.createAndBindUi(this));

    }

    @Override
    public void setDelegate(ActionDelegate delegate) {

    }
}