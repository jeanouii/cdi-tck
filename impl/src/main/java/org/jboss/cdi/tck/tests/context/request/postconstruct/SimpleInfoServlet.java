/*
 * JBoss, Home of Professional Open Source
 * Copyright 2012, Red Hat, Inc., and individual contributors
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

package org.jboss.cdi.tck.tests.context.request.postconstruct;

import java.io.IOException;

import jakarta.enterprise.context.spi.CreationalContext;
import jakarta.enterprise.inject.spi.Bean;
import jakarta.enterprise.inject.spi.BeanManager;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @author Martin Kouba
 * 
 */
@SuppressWarnings("serial")
@WebServlet("/simple")
public class SimpleInfoServlet extends HttpServlet {

    @Inject
    RequestContextObserver observer;

    @Inject
    BeanManager beanManager;

    @SuppressWarnings("unchecked")
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Bean<SimpleBean> bean = (Bean<SimpleBean>) beanManager.resolve(beanManager.getBeans(SimpleBean.class));
        CreationalContext<SimpleBean> ctx = beanManager.createCreationalContext(bean);
        SimpleBean simpleBean = bean.create(ctx);
        boolean isActive = simpleBean.isRequestContextActiveDuringPostConstruct();
        bean.destroy(simpleBean, ctx);

        resp.getWriter().append("Active:" + isActive);
        resp.getWriter().append("\n");
        resp.getWriter().append("Initialized requests:" + observer.getInitializations().get());
        resp.getWriter().append("\n");
        resp.getWriter().append("Destroyed requests:" + observer.getDestructions().get());
        resp.setContentType("text/plain");
    }

}
