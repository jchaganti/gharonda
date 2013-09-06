/****************************************************************
 * Licensed to the Apache Software Foundation (ASF) under one * or more contributor license agreements. See the NOTICE
 * file * distributed with this work for additional information * regarding copyright ownership. The ASF licenses this
 * file * to you under the Apache License, Version 2.0 (the * "License"); you may not use this file except in compliance
 * * with the License. You may obtain a copy of the License at * * http://www.apache.org/licenses/LICENSE-2.0 * * Unless
 * required by applicable law or agreed to in writing, * software distributed under the License is distributed on an *
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY * KIND, either express or implied. See the License for the *
 * specific language governing permissions and limitations * under the License. *
 ****************************************************************/

package org.jchlabs.gharonda.shared.rpc;

import com.gwtplatform.dispatch.shared.Action;

public class LoginUser implements Action<LoginUserResult> {

	private static final long serialVersionUID = -7541443368424711160L;

	private String email;
	private String password;

	@SuppressWarnings("unused")
	private LoginUser() {
	}

	public LoginUser(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public String getServiceName() {
		return Action.DEFAULT_SERVICE_NAME + "LoginUser";
	}

	@Override
	public boolean isSecured() {
		// TODO Auto-generated method stub
		return false;
	}

}
