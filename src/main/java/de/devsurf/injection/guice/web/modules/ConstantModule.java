package de.devsurf.injection.guice.web.modules;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

import de.devsurf.injection.guice.scanner.annotations.GuiceModule;

@GuiceModule
public class ConstantModule extends AbstractModule{

    @Override
    protected void configure() {
	bindConstant().annotatedWith(Names.named("answer")).to("Got you! Injection...");
    }

}
