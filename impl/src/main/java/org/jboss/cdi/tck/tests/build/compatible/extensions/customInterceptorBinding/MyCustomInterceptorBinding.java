package org.jboss.cdi.tck.tests.build.compatible.extensions.customInterceptorBinding;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface MyCustomInterceptorBinding {
    String value();
}
