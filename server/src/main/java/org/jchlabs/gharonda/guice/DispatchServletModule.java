/****************************************************************
 * Licensed to the Apache Software Foundation (ASF) under one * or more contributor license agreements. See the NOTICE
 * file * distributed with this work for additional information * regarding copyright ownership. The ASF licenses this
 * file * to you under the Apache License, Version 2.0 (the * "License"); you may not use this file except in compliance
 * * with the License. You may obtain a copy of the License at * * http://www.apache.org/licenses/LICENSE-2.0 * * Unless
 * required by applicable law or agreed to in writing, * software distributed under the License is distributed on an *
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY * KIND, either express or implied. See the License for the *
 * specific language governing permissions and limitations * under the License. *
 ****************************************************************/

package org.jchlabs.gharonda.guice;

import org.jchlabs.gharonda.server.servlet.UploadImageServlet;

import com.google.inject.servlet.ServletModule;
import com.gwtplatform.dispatch.server.DispatchServiceImpl;
import com.gwtplatform.dispatch.shared.ActionImpl;

public class DispatchServletModule extends ServletModule {
	@Override
	public void configureServlets() {
		serve("/com.Gharonda.Gharonda/" + ActionImpl.DEFAULT_SERVICE_NAME + "*")
				.with(DispatchServiceImpl.class);
		serve("/com.Gharonda.Gharonda/servlet.gupld").with(UploadImageServlet.class);

	}
}