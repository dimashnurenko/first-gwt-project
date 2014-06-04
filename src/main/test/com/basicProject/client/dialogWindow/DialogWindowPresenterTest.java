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
package com.basicProject.client.dialogWindow;

import com.basicProject.client.entity.Employee;
import com.basicProject.client.mvp.CallBack;
import com.google.gwtmockito.GwtMockitoTestRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Dmitry Shnurenko
 */

@RunWith(GwtMockitoTestRunner.class)
public class DialogWindowPresenterTest {

    private DialogWindowPresenter dialogWindowPresenter = mock(DialogWindowPresenter.class);
    private DialogWindowView      dialogWindowView      = mock(DialogWindowView.class);
    private CallBack              callBackForAdd        = mock(CallBack.class);
    private CallBack              callBackForEdit       = mock(CallBack.class);
    private ArrayList<Employee>   list                  = new ArrayList<>();

    @Before
    public void setUpCallBack() {
        Employee empl = new Employee("1","2","3");

    }


    @Before
    public void setUpDialogPresenter() {

    }

    @Test
    public void onClickButtonOrder() {

        dialogWindowPresenter.onClickAddEmployee();
        dialogWindowPresenter.onClickCancel();

        verify(dialogWindowPresenter).onClickAddEmployee();
        verify(dialogWindowPresenter).onClickCancel();
    }

    @Test
    public void clickAddButtonTest() {

    }


}
