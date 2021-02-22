package ImageHoster.service;

import ImageHoster.model.Comment;
import ImageHoster.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

//Adding the CommentService class
@Service
public class CommentService {

    @PersistenceUnit(unitName = "imageHoster")
    private EntityManagerFactory emf;

    @Autowired
    CommentRepository repository;

    //To retrieve all the comments from the CommentRepository
    public List<Comment> getAllComments(){

        return repository.getAllComments();
    }

    //Adding the createComment method called by the business logic of the CommentController method createComment method.
    public void createComment(Comment newComment){
        //setting the created date as local date.
        newComment.setCreatedDate(LocalDate.now());
        //inserting the new comment to be persisted in the database.
        repository.createComment(newComment);
        System.out.println("New Comment: "+newComment);
    }

}
