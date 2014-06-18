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
package com.basicProject.client.entity;

/**
 * @author Dmitry Shnurenko
 */
public class User {
    private final String login;
    private final String email;
    private final String password;

    public User(UserBuilder builder) {
        this.login = builder.login;
        this.email = builder.email;
        this.password = builder.password;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public static class UserBuilder {
        private String login;
        private String email;
        private String password;

        public UserBuilder() {
        }

        public UserBuilder login(String login) {
            this.login = login;
            return this;
        }

        public UserBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder password(String password) {
            this.password = password;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
