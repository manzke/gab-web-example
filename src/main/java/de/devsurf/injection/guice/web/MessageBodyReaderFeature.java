package de.devsurf.injection.guice.web;

import java.lang.annotation.Annotation;
import java.util.Map;
import java.util.logging.Logger;

import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;

import com.google.inject.Singleton;

import de.devsurf.injection.guice.scanner.BindingScannerFeature;
import de.devsurf.injection.guice.scanner.InstallationContext.BindingStage;

@Singleton
public class MessageBodyReaderFeature extends BindingScannerFeature {
    private Logger _logger = Logger.getLogger(MessageBodyReaderFeature.class.getName());

    @Override
    public BindingStage accept(Class<Object> annotatedClass, Map<String, Annotation> annotations) {
	if (annotations.containsKey(Provider.class.getName())) {
	    if (MessageBodyReader.class.isAssignableFrom(annotatedClass)) {
		return BindingStage.BINDING;
	    }
	}
	return BindingStage.IGNORE;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void process(final Class<Object> annotatedClass,
	    final Map<String, Annotation> annotations) {
	_logger.info("Binding " + annotatedClass + " as MessageBodyReader");
	Class<? extends MessageBodyReader> subclass = annotatedClass.asSubclass(MessageBodyReader.class);
	    _binder.bind(MessageBodyReader.class).to(subclass);
    }
}
