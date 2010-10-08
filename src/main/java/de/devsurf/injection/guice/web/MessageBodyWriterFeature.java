package de.devsurf.injection.guice.web;

import java.lang.annotation.Annotation;
import java.util.Map;
import java.util.logging.Logger;

import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import com.google.inject.Singleton;

import de.devsurf.injection.guice.scanner.BindingScannerFeature;
import de.devsurf.injection.guice.scanner.InstallationContext.BindingStage;

@Singleton
public class MessageBodyWriterFeature extends BindingScannerFeature {
    private Logger _logger = Logger.getLogger(MessageBodyWriterFeature.class.getName());

    @Override
    public BindingStage accept(Class<Object> annotatedClass, Map<String, Annotation> annotations) {
	if (annotations.containsKey(Provider.class.getName())) {
	    if (MessageBodyWriter.class.isAssignableFrom(annotatedClass)) {
		return BindingStage.BINDING;
	    }
	}
	return BindingStage.IGNORE;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void process(final Class<Object> annotatedClass,
	    final Map<String, Annotation> annotations) {

	if (MessageBodyWriter.class.isAssignableFrom(annotatedClass)) {
	    _logger.info("Binding " + annotatedClass + " as MessageWriterReader");
	    Class<? extends MessageBodyWriter> subclass = annotatedClass
		.asSubclass(MessageBodyWriter.class);
	    _binder.bind(MessageBodyWriter.class).to(subclass);
	}
    }
}
