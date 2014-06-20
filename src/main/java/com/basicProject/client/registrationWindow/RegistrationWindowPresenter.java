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
package com.basicProject.client.registrationWindow;

import com.basicProject.client.ClientDecoratedResources;
import com.basicProject.client.Localization;
import com.basicProject.client.entity.User;
import com.basicProject.client.eventbus.event.BackButtonEvent;
import com.basicProject.client.eventbus.event.BackButtonEventHandler;
import com.basicProject.client.eventbus.event.RegistrationEvent;
import com.basicProject.client.eventbus.event.RegistrationEventHandler;
import com.basicProject.client.eventbus.event.ShowRegisterUsersEvent;
import com.basicProject.client.eventbus.event.ShowRegisterUsersEventHandler;
import com.basicProject.client.eventbus.event.ShowTextEvent;
import com.basicProject.client.eventbus.event.ShowTextEventHandler;
import com.basicProject.client.navigator.MainNavigator;
import com.basicProject.client.regex.Regex;
import com.basicProject.client.regex.StringMatcher;
import com.basicProject.client.showRegisterUsers.ShowRegisterUsersView;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.resources.client.ResourceCallback;
import com.google.gwt.resources.client.ResourceException;
import com.google.gwt.resources.client.TextResource;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasOneWidget;
import com.google.inject.Inject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dmitry Shnurenko
 */
public class RegistrationWindowPresenter implements RegistrationWindowView.ActionDelegate,
                                                    RegistrationEventHandler,
                                                    ShowRegisterUsersEventHandler,
                                                    ShowTextEventHandler,
                                                    BackButtonEventHandler {

    private final RegistrationWindowView registrationWindowView;
    private final ShowRegisterUsersView  registerUsersView;
    private final EventBus               eventBus;
    private final List<User>             users;
    private final Localization           localization;

    private MainNavigator mainNavigator;

    @Inject
    public RegistrationWindowPresenter(RegistrationWindowView registrationWindowView,
                                       ShowRegisterUsersView registerUsersView,
                                       Localization localization,
                                       EventBus eventBus) {
        this.registrationWindowView = registrationWindowView;
        this.eventBus = eventBus;
        this.registerUsersView = registerUsersView;
        this.localization = localization;
        this.users = new ArrayList<>();

        this.eventBus.addHandler(RegistrationEvent.TYPE, this);
        this.eventBus.addHandler(ShowRegisterUsersEvent.TYPE, this);
        this.eventBus.addHandler(BackButtonEvent.TYPE, this);
        this.eventBus.addHandler(ShowTextEvent.TYPE, this);
    }

    public void setMainNavigator(MainNavigator mainNavigator) {
        this.mainNavigator = mainNavigator;
    }

    public void go(HasOneWidget widget) {
        widget.setWidget(registrationWindowView);
    }

    @Override
    public void saveEmployeeToDataBase(RegistrationEvent registrationEvent) {
        String login = registrationWindowView.getLogin();
        String email = registrationWindowView.getEmail();
        String password = registrationWindowView.getPassword();

        if (!StringMatcher.match(Regex.LOGIN, login) || login.isEmpty()) {
            registrationWindowView.setErrorLogin(localization.errorLogin());
        } else if (!StringMatcher.match(Regex.EMAIL, email) || email.isEmpty()) {
            registrationWindowView.setErrorEmail(localization.errorEmail());
        } else if (!StringMatcher.match(Regex.PASSWORD, password) || password.isEmpty()) {
            registrationWindowView.setErrorPassword(localization.errorPassword());
        } else {
            registrationWindowView.setErrorLogin("");
            registrationWindowView.setErrorEmail("");
            registrationWindowView.setErrorPassword("");

            User user = User.make()
                            .login(login)
                            .email(email)
                            .password(password);

            users.add(user);

            registrationWindowView.setLogin("");
            registrationWindowView.setEmail("");
            registrationWindowView.setPassword("");

            Window.alert(user.getLogin() + "\n" + "successfully save to database");
        }
    }

    @Override
    public void backToMainPage(BackButtonEvent backButtonEvent) {
        mainNavigator.showMainWindow();
    }

    @Override
    public void showAllUsersFromDataBase(ShowRegisterUsersEvent registerUsersEvent) {
        registerUsersView.showRegisterUsers(users);
        registerUsersView.showWindow();
    }

    @Override
    public void onTextExternalResourceChange(ShowTextEvent textEvent) {
        try {
            ClientDecoratedResources.INSTANCE.getExternalText().getText(new ResourceCallback<TextResource>() {
                @Override
                public void onError(ResourceException e) {
                    Window.alert("download external text failed..." + e.getMessage());
                }

                @Override
                public void onSuccess(TextResource resource) {
                    registrationWindowView.setText(resource.getText());
                }
            });

        } catch (ResourceException e) {
            Window.alert("download external text failed..." + e.getMessage());
        }
    }
}
