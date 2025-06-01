package com.animecommunity.animecom.Controllers;

import java.io.IOException;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.animecommunity.animecom.Dao.UserRepository;
import com.animecommunity.animecom.Models.Answer;
import com.animecommunity.animecom.Models.Post;
import com.animecommunity.animecom.Models.Question;
import com.animecommunity.animecom.Models.Theory;
import com.animecommunity.animecom.Models.User;
import com.animecommunity.animecom.Services.UserService;

@RestController
@RequestMapping("/api/user/")
public class UserController {

    @Autowired 
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    //http://localhost:5050/api/user/test
    @GetMapping("test")
    @PreAuthorize("hasRole('USER')")
    public String getMethodName() {
        return "This is  userpage";
    }
    //http://localhost:5050/api/user/create-question
    @PostMapping("create-question")
    @PreAuthorize("hasRole('USER')")
    public String createQuestion(@RequestBody String question_statement, MultipartFile file,  Principal principal) throws IOException {
        User user = this.userRepository.getUserByusername(principal.getName());
        Question ques = this.userService.createQuestion(question_statement, file, user);
        return "Question created with Id: " + ques.getQuestion_id();
    }
    //http://localhost:5050/api/user/create-answer
    @PostMapping("create-answer/{questionId}")
    @PreAuthorize("hasRole('USER')")
    public String createAnswer(@PathVariable int questionId, @RequestBody String answer_statement, MultipartFile file,  Principal principal) throws IOException {
        User user = this.userRepository.getUserByusername(principal.getName());
        Answer ans = this.userService.createAnswer(answer_statement,questionId,  file, user);
        return "Answer created with Id: " + ans.getAnswer_id();
    }

    //http://localhost:5050/api/user/create-theory
    @PostMapping("create-theory")
    @PreAuthorize("hasRole('USER')")
    public String createTheory(@RequestBody String theory_statement, MultipartFile file,  Principal principal) throws IOException {
        User user = this.userRepository.getUserByusername(principal.getName());
        Theory theory = this.userService.createTheory(theory_statement,file, user);
        return "Theory created with Id: " + theory.getTheory_Id();
    }

    //http://localhost:5050/api/user/create-post
    @PostMapping("create-post")
    @PreAuthorize("hasRole('USER')")
    public String createPost(@RequestBody String post_description, MultipartFile file,  Principal principal) throws IOException {
        User user = this.userRepository.getUserByusername(principal.getName());
        Post post = this.userService.createPost(post_description,file, user);
        return "Post created with Id: " + post.getPost_Id();
    }
    //http://localhost:5050/api/user/save-post
    @PostMapping("save-post/{post_Id}")
    @PreAuthorize("hasRole('USER')")
    public String savePost(@PathVariable int post_Id,  Principal principal) throws IOException {
        User user = this.userRepository.getUserByusername(principal.getName());
        Post post = this.userService.savePost(post_Id, user);
        return "Post saved with Id: " + post.getPost_Id();
    }

    //http://localhost:5050/api/user/remove-post
    @PostMapping("remove-post/{post_Id}")
    @PreAuthorize("hasRole('USER')")
    public String removePost(@PathVariable int post_Id,  Principal principal) throws IOException {
        User user = this.userRepository.getUserByusername(principal.getName());
        Post post = this.userService.removePost(post_Id, user);
        return "Post removed with Id: " + post.getPost_Id();
    }

    //http://localhost:5050/api/user/like/{targetType}/{targetId}
    @PostMapping("like/{targetType}/{targetId}")
    @PreAuthorize("hasRole('USER')")
    public String like(@PathVariable String targerType, @PathVariable String targetId ,   Principal principal) throws IOException {
        User user = this.userRepository.getUserByusername(principal.getName());
        return this.userService.addLike(targetId, targetId, user);
    }

    //http://localhost:5050/api/user/dislike/{targetType}/{targetId}
    @PostMapping("dislike/{targetType}/{targetId}")
    @PreAuthorize("hasRole('USER')")
    public String dislike(@PathVariable String targerType, @PathVariable String targetId ,Principal principal) throws IOException {
        User user = this.userRepository.getUserByusername(principal.getName());
        return this.userService.addDislike(targetId, targetId, user);
    }

    //http://localhost:5050/api/user/make-comment/{targetType}/{targetId}
    @PostMapping("make-comment/{targetType}/{targetId}")
    @PreAuthorize("hasRole('USER')")
    public String makeComment(@PathVariable String targertType, @PathVariable String targetId , @RequestBody String commentBody, MultipartFile file, Principal principal) throws IOException {
        User user = this.userRepository.getUserByusername(principal.getName());
        return this.userService.addComment(targetId, targetId, commentBody, file, user);
    }


    //http://localhost:5050/api/user/delete-comment/{targetType}/{targetId}
    @PostMapping("delete-comment/{targetType}/{targetId}")
    @PreAuthorize("hasRole('USER')")
    public String deleteComment(@PathVariable String targertType, @PathVariable String targetId , Principal principal) throws IOException {
        User user = this.userRepository.getUserByusername(principal.getName());
        return this.userService.deleteComment(targetId, targetId,  user);
    }
}
