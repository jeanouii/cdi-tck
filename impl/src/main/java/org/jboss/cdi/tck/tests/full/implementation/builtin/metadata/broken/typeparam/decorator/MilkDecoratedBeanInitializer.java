/*
 * JBoss, Home of Professional Open Source
 * Copyright 2010, Red Hat, Inc., and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,  
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.cdi.tck.tests.full.implementation.builtin.metadata.broken.typeparam.decorator;

import jakarta.annotation.Priority;
import jakarta.decorator.Decorator;
import jakarta.decorator.Delegate;
import jakarta.enterprise.inject.spi.Bean;
import jakarta.inject.Inject;

import org.jboss.cdi.tck.tests.implementation.builtin.metadata.broken.typeparam.Cream;
import org.jboss.cdi.tck.tests.implementation.builtin.metadata.broken.typeparam.Milk;

@Decorator
@Priority(100)
public class MilkDecoratedBeanInitializer implements Milk {

    @Inject
    @Delegate
    Milk milk;

    @Inject
    public void setDecorator(Bean<Cream> bean) {
    }

    @Override
    public void ping() {
        milk.ping();
    }

}
