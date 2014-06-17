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

import com.basicProject.client.entity.User;
import com.basicProject.client.eventbus.event.BackButtonEvent;
import com.basicProject.client.eventbus.event.BackButtonEventHandler;
import com.basicProject.client.eventbus.event.RegistrationEvent;
import com.basicProject.client.eventbus.event.RegistrationEventHandler;
import com.basicProject.client.eventbus.event.ShowRegisterUsersEvent;
import com.basicProject.client.eventbus.event.ShowRegisterUsersEventHandler;
import com.basicProject.client.navigator.MainNavigator;
import com.basicProject.client.showRegisterUsers.ShowRegisterUsersView;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasOneWidget;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;
import com.google.inject.Inject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dmitry Shnurenko
 */
public class RegistrationWindowPresenter implements RegistrationWindowView.ActionDelegate,
                                                    RegistrationEventHandler,
                                                    ShowRegisterUsersEventHandler,
                                                    BackButtonEventHandler {

    private RegistrationWindowView registrationWindowView;
    private ShowRegisterUsersView  registerUsersView;
    private MainNavigator          mainNavigator;
    private EventBus               eventBus;
    private SimpleLayoutPanel      layoutPanel;
    private List<User>             users;

    @Inject
    public RegistrationWindowPresenter(RegistrationWindowView registrationWindowView,
                                       ShowRegisterUsersView registerUsersView,
                                       SimpleLayoutPanel layoutPanel,
                                       EventBus eventBus) {
        this.registrationWindowView = registrationWindowView;
        this.layoutPanel = layoutPanel;
        this.eventBus = eventBus;
        this.registerUsersView = registerUsersView;
        this.users = new ArrayList<>();

        this.eventBus.addHandler(RegistrationEvent.TYPE, this);
        this.eventBus.addHandler(ShowRegisterUsersEvent.TYPE, this);
        this.eventBus.addHandler(BackButtonEvent.TYPE, this);
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

        User user = new User(login, email, password);

        users.add(user);

        registrationWindowView.setLogin("");
        registrationWindowView.setEmail("");
        registrationWindowView.setPassword("");

        Window.alert(user.getLogin() + " successfully save to database");
    }

    @Override
    public void backToMainPage(BackButtonEvent backButtonEvent) {
        mainNavigator.showMainWindow(layoutPanel);
    }

    @Override
    public void showAllUsersFromDataBase(ShowRegisterUsersEvent registerUsersEvent) {
        registerUsersView.showRegisterUsers(users);
    }
}
