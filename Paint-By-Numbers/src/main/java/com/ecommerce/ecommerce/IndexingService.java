package com.ecommerce.ecommerce;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;


//https://wkrzywiec.medium.com/full-text-search-with-hibernate-search-lucene-part-1-e245b889aa8e
//https://reflectoring.io/hibernate-search/
public class IndexingService  implements ApplicationListener<ContextRefreshedEvent> {
    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(em);
        System.out.println("Start Lucene Indexing ...");
        try {
            fullTextEntityManager.createIndexer().startAndWait();
            System.out.println("Finish Lucene Indexing ...");
        } catch (InterruptedException e) {
            System.out.println("Error occurred trying to build Hibernate Search indexes " + e.toString());
        }
    }

}
