package com.ibt.intellimeet.data;

import com.ibt.intellimeet.ejb.EJBTest;
import org.testng.annotations.Test;

import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.transaction.TransactionManager;
import java.util.logging.Logger;

/**
 * UserTest Unit test for the User Entity
 */
public class UserTest
        extends EJBTest
{

    Logger log = Logger.getLogger("UserTest");

    public UserTest()
    {
        super();
    }

    @Test
    public void testUser()
            throws Exception
    {

        new ComponentTest()
        {

            protected void testComponents()
                    throws Exception
            {
                EntityManager em = (EntityManager)
                        new InitialContext().lookup
                                ("java:/EntityManagers/DefaultDS");

                // Obtain JBoss transaction
                TransactionManager tm =
                        (TransactionManager) new InitialContext().lookup
                                ("java:/TransactionManager");

                tm.begin();

                User user = new User();
                user.setEmail("test1@test.com");
                user.setPassword("test1");
                em.persist(user);

                assert(user.getId() > 0);

                tm.commit();
                long id = user.getId();
                log.info("created user 'test1@test.com' in DB with id: " + id);

                tm.begin();
                user = em.find(User.class, id);
                assert(null != user);
                tm.commit();
                log.info("found user 'test1@test.com' in DB with id: " + id);
            }
        }.run();

    }

}