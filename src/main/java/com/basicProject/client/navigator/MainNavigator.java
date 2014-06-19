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
package com.basicProject.client.navigator;

import com.basicProject.client.keyboardShortcut.KeyboardShortcut;
import com.basicProject.client.mainWindow.MainWindowPresenter;
import com.basicProject.client.registrationWindow.RegistrationWindowPresenter;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;
import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * @author Dmitry Shnurenko
 */
@Singleton
public class MainNavigator {

    private final RegistrationWindowPresenter registrationWindowPresenter;
    private final MainWindowPresenter         mainWindowPresenter;
    private final KeyboardShortcut            keyboardShortcut;

    private SimpleLayoutPanel simpleLayoutPanel;

    @Inject
    public MainNavigator(RegistrationWindowPresenter registrationWindowPresenter,
                         KeyboardShortcut keyboardShortcut,
                         MainWindowPresenter mainWindowPresenter) {
        this.mainWindowPresenter = mainWindowPresenter;
        this.keyboardShortcut = keyboardShortcut;
        this.mainWindowPresenter.setMainNavigator(this);
        this.registrationWindowPresenter = registrationWindowPresenter;
        this.registrationWindowPresenter.setMainNavigator(this);
    }

    public void go(SimpleLayoutPanel panel) {
        this.simpleLayoutPanel = panel;
        mainWindowPresenter.go(panel);
    }

    public void showMainWindow() {
        simpleLayoutPanel.clear();
        mainWindowPresenter.go(simpleLayoutPanel);
    }

    public void showRegistrationWindow() {
        simpleLayoutPanel.clear();
        registrationWindowPresenter.go(simpleLayoutPanel);
    }
}
