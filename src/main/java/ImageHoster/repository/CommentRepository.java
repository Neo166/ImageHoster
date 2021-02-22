package ImageHoster.repository;

import ImageHoster.model.Comment;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

//Adding Comment Repository

@Repository
public class CommentRepository {
    @PersistenceUnit(unitName = "imageHoster")
    private EntityManagerFactory emf;

    public List<Comment> getAllComments(){
        EntityManager em = emf.createEntityManager();

        //using JPQL to generate the list of all comments from the DB.

        TypedQuery<Comment> query = em.createQuery("SELECT p from Comment p", Comment.class);
        List<Comment> resultList = query.getResultList();
        return resultList;

    }

        //Persisting a new comment into the DB, however following atomicity principles ensuring that if there is any problem with the persist operation..
        //..rollback the transaction.
    public Comment createComment(Comment newComment){
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(newComment);
            transaction.commit();
        }
        catch (Exception e) {
            transaction.rollback();
        }

        return newComment;
    }


}
