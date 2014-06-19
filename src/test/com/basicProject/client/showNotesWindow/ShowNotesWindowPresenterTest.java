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
package com.basicProject.client.showNotesWindow;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

/**
 * @author Dmitry Shnurenko
 */
@RunWith(MockitoJUnitRunner.class)
public class ShowNotesWindowPresenterTest {

    @Mock
    private ShowNotesWindowView      showNotesWindowView;
    @InjectMocks
    private ShowNotesWindowPresenter showNotesWindowPresenter;

    @Test
    public void windowWithNotesShouldBeDisappeared() throws Exception {
        showNotesWindowPresenter.onCancelButtonClicked();

        verify(showNotesWindowView).hideWindow();
    }

}
