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

import org.jchlabs.gharonda.domain.model.Users;

import com.gwtplatform.dispatch.shared.Action;

public class ModifyUser implements Action<ModifyUserResult> {

	private static final long serialVersionUID = -7541443368424711159L;

	private Users user;

	private String oldPassWord;
	private String newPassWord;
	private String newEmail;
	private ModifyUserType modType;

	@SuppressWarnings("unused")
	private ModifyUser() {
	}

	public Users getUser() {
		return user;
	}

	public ModifyUser(Users user, ModifyUserType modType, String oldPassWord, String newPassWord, String newEmail) {
		this.user = user;
		this.oldPassWord = oldPassWord;
		this.newPassWord = newPassWord;
		this.newEmail = newEmail;
		this.modType = modType;
	}

	public String getOldPassWord() {
		return oldPassWord;
	}

	public String getNewPassWord() {
		return newPassWord;
	}

	public String getNewEmail() {
		return newEmail;
	}

	public ModifyUserType getModType() {
		return modType;
	}

	@Override
	public String getServiceName() {
		return Action.DEFAULT_SERVICE_NAME + "ModifyUser";
	}

	@Override
	public boolean isSecured() {
		// TODO Auto-generated method stub
		return false;
	}

}
