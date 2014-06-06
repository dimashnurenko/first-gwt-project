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
package com.basicProject.client.mainWindow;

import com.basicProject.client.entity.Employee;
import com.basicProject.client.mvp.CallBack;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.inOrder;

/**
 * @author Dmitry Shnurenko
 */
@RunWith(MockitoJUnitRunner.class)
public class AddEmployeeCallBackTest {

    @Mock
    private List<Employee> employees;
    @Mock
    private MainWindowView view;
    @Mock
    private Employee       employee;

    private CallBack addEmplyeeCallback;

    @Before
    public void setUp() {

        this.addEmplyeeCallback = new CallBack() {
            @Override
            public void onChangeTableOfEmployee(Employee empl) {
                employees.add((Employee)anyObject());

                view.setEmployeesList(employees);
            }
        };
    }

    @Test
    public void testAddEmployeeCallBackBehavior() {


        addEmplyeeCallback.onChangeTableOfEmployee(employee);

        InOrder order = inOrder(employees, view);

        order.verify(employees).add((Employee)anyObject());
        order.verify(view).setEmployeesList((List)anyObject());
    }
}
