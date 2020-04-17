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
package org.jboss.cdi.tck.tests.implementation.simple.newSimpleBean;

import java.io.Serializable;

import jakarta.enterprise.context.SessionScoped;
import jakarta.enterprise.inject.Default;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@SessionScoped
@Named("Fred")
@Default
public class ExplicitContructorSimpleBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private static int constructorCalls = 0;

    public ExplicitContructorSimpleBean() {

    }

    @Inject
    public ExplicitContructorSimpleBean(Shop shop) {
        constructorCalls++;
    }

    public void ping() {

    }

    public static int getConstructorCalls() {
        return constructorCalls;
    }

    public static void setConstructorCalls(int constructorCalls) {
        ExplicitContructorSimpleBean.constructorCalls = constructorCalls;
    }

}
