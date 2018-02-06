package net.service.testcrossfitresult.domain;

import java.util.Iterator;
import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import net.service.testcrossfitresult.model.WorkoutType;
import net.service.testcrossfitresult.util.HIbernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Main {

    public static void main(String[] args) {
        SessionFactory sFactory = HIbernateUtil.sessionFactory;
        Session session = sFactory.openSession();
        List workType = null;

        try {
            session.beginTransaction();
            CriteriaQuery cq = session.getCriteriaBuilder().createQuery(WorkoutType.class);
            cq.from(WorkoutType.class);
            workType = session.createQuery(cq).getResultList();
            
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.out.println("Error! " + e);
        } finally {
            session.close();
            sFactory.close();
        }
        for (Iterator it = workType.iterator(); it.hasNext();) {
                WorkoutType value = (WorkoutType) it.next();
                System.out.println(value.toString());
            }
    }
}
