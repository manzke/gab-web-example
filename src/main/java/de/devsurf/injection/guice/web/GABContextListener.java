/**
 * Copyright (C) 2010 Daniel Manzke <daniel.manzke@googlemail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.devsurf.injection.guice.web;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

import de.devsurf.injection.guice.DynamicModule;
import de.devsurf.injection.guice.aop.Interceptor.InterceptorFeature;
import de.devsurf.injection.guice.configuration.PropertiesConfigurationFeature;
import de.devsurf.injection.guice.scanner.StartupModule;
import de.devsurf.injection.guice.scanner.asm.ASMClasspathScanner;

public class GABContextListener extends GuiceServletContextListener {
    public static Injector injector;
    
    static{
	StartupModule startupModule = StartupModule.create(ASMClasspathScanner.class, "de.devsurf");
	startupModule.addFeature(PropertiesConfigurationFeature.class);
	startupModule.addFeature(InterceptorFeature.class);
	injector = Guice.createInjector(startupModule);

	injector = Guice.createInjector(injector.getInstance(DynamicModule.class));
    }
    
//    @Override
    protected Injector getInjector() {
	return injector;
    }

}
