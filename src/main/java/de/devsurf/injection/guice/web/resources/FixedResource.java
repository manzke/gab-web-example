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
package de.devsurf.injection.guice.web.resources;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

import de.devsurf.injection.guice.aop.Intercept;
import de.devsurf.injection.guice.scanner.annotations.Bind;


@Bind
@Path("/fixed")
@Singleton
public class FixedResource {
    
    @Inject
    @Named("answer")
    String answer;
    
    Date date;
    
    @GET
    @Path("view.html")
    @Produces("text/html")
    @Intercept
    public String getAnswer(){
	return "<html><head/><body>This is my Anwser! Fixed Date: "+date+" incl. Named String-Binding: "+answer+" </body></html>";
    }
    
    @PostConstruct
    public void init(){
	 date = new Date();
    }
}
