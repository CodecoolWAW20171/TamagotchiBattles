import com.codecool.tamagotchi.model.tamagotchi.Pet;
import com.codecool.tamagotchi.dao.DataBaseConnection;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    private static final SessionFactory ourSessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    public static void main(final String[] args) throws Exception {

        DataBaseConnection dataBaseConnection = new DataBaseConnection();

        dataBaseConnection.connectDB();
        for (Object pet : dataBaseConnection.runQuery("from Pet")) {
            System.out.println(((Pet)pet).getName());
        }

        dataBaseConnection.disconnectDB();
        /*final Session session = getSession();
        try {
            System.out.println("querying all the managed entities...");
            String hql = "FROM Pet";
            Query query = session.createQuery(hql);
            List<Pet> results = query.list();
            System.out.println("Coś z bazy danych: " + results);
            for (Pet pet: results) {
                System.out.println(pet.getName());
            }
            *//* final Metamodel metamodel = session.getSessionFactory().getMetamodel();
            for (EntityType<?> entityType : metamodel.getEntities()) {
                final String entityName = "pet"; //entityType.getName();
                final Query query = session.createQuery("from " + entityName);
                System.out.println("executing: " + query.getQueryString());
                for (Object o : query.list()) {
                    System.out.println("  " + o);
                }
            }*//*
        } finally {
            session.close();
        }*/
    }
}