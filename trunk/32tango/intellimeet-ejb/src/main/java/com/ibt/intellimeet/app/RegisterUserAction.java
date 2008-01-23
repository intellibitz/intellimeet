/*
 *  Copyright 2008 vijayan.
 * 
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  under the License.
 */

package com.ibt.intellimeet.app;

import com.ibt.intellimeet.data.User;
import static org.jboss.seam.ScopeType.EVENT;
import org.jboss.seam.annotations.Destroy;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.log.Log;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

/**
 * @author vijayan
 */
@Stateful
@Scope(EVENT)
@Name("registerUserAction")
public class RegisterUserAction
        implements IRegisterUserActionLocal, Serializable
{
    @Logger
    Log log;

    @In(required = true)
    private User user;

    @PersistenceContext
    private EntityManager entityManager;

    private String verify;
    private boolean registered;

    public User findUser(long id)
    {
        return entityManager.find(User.class, id);
    }

    public long register()
    {
        if (user.getPassword().equals(verify))
        {
            List existing = entityManager.createQuery
                    ("select u.email from User u where u.email=#{user.email}")
                    .getResultList();
            if (existing.size() == 0)
            {
                entityManager.persist(user);
                // facesMessages.add("Successfully registered as #{user.username}");
                log.info("Username #{user.email} already exists");
                registered = true;
            }
            else
            {
                log.info("Username #{user.email} already exists");
            }
        }
        else
        {
            log.info("Re-enter your password");
            verify = null;
        }
        return user.getId();
    }

    public String getVerify()
    {
        return verify;
    }

    public void setVerify(String verify)
    {
        this.verify = verify;
    }

    public boolean isRegistered()
    {
        return registered;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    @Destroy
    @Remove
    public void destroy()
    {
        log.info("registerUserAction#destroy.. Done!");
    }

}
