/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package experimentorm;

import controller.AddressJpaController;
import controller.WriterJpaController;
import entity.Address;
import entity.Book;
import entity.Reader;
import entity.Writer;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author jvm
 */
public class ExperimentOrm {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Book book1 = new Book("Это книга 1");
        Book book2 = new Book("Это книга 2");
        Book book3 = new Book("Это книга 3");

        Address addressWriter = new Address("13","12a","улица 1", "Таллинн", "Харьюмаа", "23434", "Эстония");
        Writer writer = new Writer(book3, addressWriter, "Nikolay", "Nikolaev", "37509069323");
        Address addressReader = new Address("14","12a","street3", "Johve", "Ida-Virumaa", "41434", "Estonia");
        Reader reader = new Reader(book3, addressReader, "Николай", "Николаев", "37806065543");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ExperimentOrmPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
           Query query = em.createNamedQuery("Write.findWithParam").setParameter("fcode",writer.getCode());
           List<Writer> writers = query.getResultList();
           if(writers.isEmpty()){
               em.persist(writer);
           }

//           em.persist(reader); 
//           em.persist(book1);
//             Reader readerAddBook= em.find(Reader.class, 1L);
//             readerAddBook.addBook(book3);
//             em.persist(readerAddBook);
//             Book addBook = em.find(Book.class, 2L);
//             Writer writerAddBook= em.find(Writer.class, 2L);
//             writerAddBook.addBook(addBook);
//             em.persist(writerAddBook);
//           Book removeBook = em.find(Book.class, 1L);
//           em.remove(removeBook);
        tx.commit();
        em.close();
        emf.close();
        
    }

}
