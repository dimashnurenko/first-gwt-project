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
package com.basicProject.client.keyboardShortcut;

import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Window;

/**
 * @author Dmitry Shnurenko
 */
public class KeyboardShortcut {

    public KeyboardShortcut() {
        HandlerRegistration handlerRegistration = Event.addNativePreviewHandler(new Event.NativePreviewHandler() {
            @Override
            public void onPreviewNativeEvent(Event.NativePreviewEvent event) {
                NativeEvent nativeEvent = event.getNativeEvent();
                if (nativeEvent.getAltKey()) {
                    if (nativeEvent.getKeyCode() == 'd' || nativeEvent.getKeyCode() == 'D') {
                        nativeEvent.preventDefault();
                        Window.alert("Hello alt D");
                    }
                }
            }
        });

        HandlerRegistration ctrlF = Event.addNativePreviewHandler(new Event.NativePreviewHandler() {
            @Override
            public void onPreviewNativeEvent(Event.NativePreviewEvent event) {
                NativeEvent nativeEvent = event.getNativeEvent();
                if (nativeEvent.getCtrlKey()) {
                    if (nativeEvent.getKeyCode() == 'f' || nativeEvent.getKeyCode() == 'F') {
                        nativeEvent.preventDefault();
                        Window.alert("Hello ctrl F");
                    }
                }
            }
        });
    }
}
