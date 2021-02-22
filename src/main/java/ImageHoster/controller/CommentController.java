package ImageHoster.controller;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.model.User;
import ImageHoster.service.CommentService;
import ImageHoster.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private ImageService imageService;

    //Controller class method maps to POST request URL ‘/image/{imageId}/{imageTitle}/comments’ for creating a new comment.

    @RequestMapping(value = "/image/{imageId}/{imageTitle}/comments", method = RequestMethod.POST)
    // Added PathVariable to identify the imageId and imageTitle from the URI and the RequestParam to get the query parameter values for 'comment'.
    public String createComment(@PathVariable(name="imageId") Integer imageId, @PathVariable(name="imageTitle") String imageTitle, @RequestParam(name="comment") String newComment, HttpSession session){
        Image image = imageService.getImage(imageId);
        User user = (User) session.getAttribute("loggeduser");
        Comment comment = new Comment();
        comment.setUser(user);
        comment.setText(newComment);
        comment.setCreatedDate(LocalDate.now());
        comment.setImage(image);
        commentService.createComment(comment);

        //After inserting the comment with required fields and committing to DB, redirecting to showImage() method of the ImageController.
        return "redirect:/images/+" + imageId + "/" + imageTitle;
    }
}
