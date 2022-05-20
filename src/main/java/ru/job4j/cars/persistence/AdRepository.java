package ru.job4j.cars.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.job4j.cars.model.Advert;
import ru.job4j.cars.model.Brand;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;

public class AdRepository {

    private final SessionFactory sf;

    public AdRepository(SessionFactory sf) {
        this.sf = sf;
    }

    public List<Advert> findPostLastDay() {
        return tx(
                session -> session.createQuery(
                        "select distinct p from Advert p "
                                + "join fetch p.car c "
                                + "join fetch c.engine e "
                                + "join fetch c.brand br "
                                + "join fetch c.body bo "
                                + "where p.created between :yesterday and :today", Advert.class
                ).setParameter("yesterday", Timestamp.valueOf(LocalDateTime.now().minusDays(1)))
                        .setParameter("today", Timestamp.valueOf(LocalDateTime.now()))
                        .list(), sf
        );
    }

    public List<Advert> findPostIsPhoto() {
        return tx(
                session -> session.createQuery(
                        "select distinct p from Advert p "
                                + "join fetch p.car c "
                                + "join fetch c.engine e "
                                + "join fetch c.brand br "
                                + "join fetch c.body bo "
                                + "where p.photo.size > 0", Advert.class
                ).list(), sf
        );
    }

    public List<Advert> findPostWithBrand(Brand brand) {
        return tx(
                session -> session.createQuery(
                        "select distinct p from Advert p "
                                + "join fetch p.car c "
                                + "join fetch c.engine e "
                                + "join fetch c.brand br "
                                + "join fetch c.body bo "
                                + "where br = :brand", Advert.class
                ).setParameter("brand", brand).list(), sf
        );
    }

    private <T> T tx(final Function<Session, T> command, SessionFactory sf) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }
}