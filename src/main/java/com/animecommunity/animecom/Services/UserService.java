package com.animecommunity.animecom.Services;

import java.io.IOException;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import com.animecommunity.animecom.Dao.AnswerRepository;
import com.animecommunity.animecom.Dao.CommentRepository;
import com.animecommunity.animecom.Dao.LikeRepository;
import com.animecommunity.animecom.Dao.PostRepository;
import com.animecommunity.animecom.Dao.QuestionRepository;
import com.animecommunity.animecom.Dao.TheoryRepository;
import com.animecommunity.animecom.Dao.UserRepository;
import com.animecommunity.animecom.Models.Answer;
import com.animecommunity.animecom.Models.Comment;
import com.animecommunity.animecom.Models.Commentable;
import com.animecommunity.animecom.Models.Credentials;
import com.animecommunity.animecom.Models.Like;
import com.animecommunity.animecom.Models.Likeable;
import com.animecommunity.animecom.Models.Post;
import com.animecommunity.animecom.Models.Question;
import com.animecommunity.animecom.Models.Roles;
import com.animecommunity.animecom.Models.Theory;
import com.animecommunity.animecom.Models.User;




@Service
public class UserService {
  

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FileService fileService;

    @Autowired
    private QuestionRepository quesRepository;

    @Autowired
    private TheoryRepository theoryRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private AnswerRepository ansRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private LikeRepository likeRepository;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    public String addUser(Credentials cred) {
        User u= new User();
        u.setRole(Roles.USER);
        u.setFirstName(cred.getFirstName());
        u.setLastName(cred.getLastName());
        u.setPassword(passwordEncoder.encode(cred.getPassword()));
        u.setEmail(cred.getEmail());
        u.setImageUrl1("https://icon-library.com/images/default-user-icon/default-user-icon-13.jpg");


        this.userRepository.save(u);
        return u.getFirstName() ;

    }

    public String addAdmin(Credentials cred) {
        User u= new User();
        u.setRole(Roles.ADMIN);
        u.setFirstName(cred.getFirstName());
        u.setLastName(cred.getLastName());
        u.setPassword(passwordEncoder.encode(cred.getPassword()));
        u.setEmail(cred.getEmail());
        u.setImageUrl1("https://icon-library.com/images/default-user-icon/default-user-icon-13.jpg");


        this.userRepository.save(u);
        return u.getFirstName() ;

    }

    public Question createQuestion(String question_statement, MultipartFile file, User user) throws IOException {
        Question ques = new Question();
        ques.setQuestion_img_url(this.fileService.uploadMedia(file, user.getUserId()));
        ques.setQuestion_statement(question_statement);
        ques.setUser(user);
        user.getQuestions().add(ques);
        this.userRepository.save(user);
        this.quesRepository.save(ques);
        return ques;
    }

    public Answer createAnswer(String answer_statement, int questionId, MultipartFile file, User user) throws IOException {
        Answer ans = new Answer();
        ans.setAnswer_img_url(this.fileService.uploadMedia(file, user.getUserId()));
        ans.setAnswer_statement(answer_statement);
        ans.setUser(user);
        Question ques= this.quesRepository.getQuestionByquestion_Id(questionId);
        ques.getAnswers().add(ans);
        this.quesRepository.save(ques);
        this.ansRepository.save(ans);
        return ans;
    }

    public Theory createTheory(String theory_statement,  MultipartFile file, User user) throws IOException {
        Theory theory = new Theory();
        theory.setTheory_img_url(this.fileService.uploadMedia(file, user.getUserId()));
        theory.setTheory_statement(theory_statement);
        theory.setUser(user);
        user.getTheories().add(theory);
        this.userRepository.save(user);
        this.theoryRepository.save(theory);
        return theory;
    }

    public Post createPost(String post_description,  MultipartFile file, User user) throws IOException {
        Post post = new Post();
        post.setPost_img_url(this.fileService.uploadMedia(file, user.getUserId()));
        post.setPost_description(post_description);
        post.setUser(user);
        user.getPosts().add(post);
        this.userRepository.save(user);
        this.postRepository.save(post);
        return post;
    }

    public Post savePost(int post_Id, User user) throws IOException {
        Post post = this.postRepository.getPostBypost_Id(post_Id);
        user.getSavedposts().add(post);
        this.userRepository.save(user);
        return post;
    }

    
    public Post removePost(int post_Id, User user) throws IOException {
        Post post = this.postRepository.getPostBypost_Id(post_Id);
        user.getSavedposts().remove(post);
        this.userRepository.save(user);
        return post;
    }


    public String addLike(String targetID, String targetType, User user){
        Likeable likeAble;
        Like like =new Like();
        like.setTargetId(targetID);
        like.setTargetType(targetType);
        like.setUser(user);

        switch (targetType.toLowerCase()) {

            case "post":
                likeAble= this.postRepository.getPostBypost_Id(Integer.parseInt(targetID)).orElseThrowLikeable();
                break;
            case "comment":
                likeAble= this.commentRepository.getCommentBycommentId(Integer.parseInt(targetID)).orElseThrowLikeable();
                break;
            case "question":
                likeAble= this.quesRepository.getQuestionByquestion_Id(Integer.parseInt(targetID)).orElseThrowLikeable();
                break;
            case "answer":
                likeAble= this.ansRepository.findByanswerId(Integer.parseInt(targetID)).orElseThrowLikeable();
                break;
            case "theory":
                likeAble= this.theoryRepository.getTheoryBytheory_Id(Integer.parseInt(targetID)).orElseThrowLikeable();
                break;
            default:
                throw new IllegalArgumentException("Unknown like type");
        }

        this.likeRepository.save(like);
        user.getLikes().add(like);
        this.userRepository.save(user);
        likeAble.addLike(like);
        return "You have Liked the " + saveLikeable(targetType , likeAble );
    }

    public String addDislike(String targetID, String targetType, User user){
        Likeable likeAble;

        switch (targetType.toLowerCase()) {

            case "post":
                likeAble= this.postRepository.getPostBypost_Id(Integer.parseInt(targetID)).orElseThrowLikeable();
                break;
            case "comment":
                likeAble= this.commentRepository.getCommentBycommentId(Integer.parseInt(targetID)).orElseThrowLikeable();
                break;

            case "question":
                likeAble= this.quesRepository.getQuestionByquestion_Id(Integer.parseInt(targetID)).orElseThrowLikeable();
                break;
            case "answer":
                likeAble= this.ansRepository.findByanswerId(Integer.parseInt(targetID)).orElseThrowLikeable();
                break;
            case "theory":
                likeAble= this.theoryRepository.getTheoryBytheory_Id(Integer.parseInt(targetID)).orElseThrowLikeable();
                break;
            default:
                throw new IllegalArgumentException("Unknown like type");
        }

        Like like =this.likeRepository.findBytargetId(targetID);
        user.getLikes().remove(like);
        this.userRepository.save(user);
        likeAble.removeLike(like);
        return "You have Disiked the " + saveLikeable(targetType , likeAble );
    }

    public String saveLikeable(String targetType, Likeable likeAble){

        switch (targetType.toLowerCase()) {

            case "post":
                this.postRepository.save((Post)likeAble);
                break;
            case "comment":
                this.commentRepository.save((Comment)likeAble);
                break;

             case "question":
                this.quesRepository.save((Question)likeAble);
                break;
            case "answer":
                this.ansRepository.save((Answer)likeAble);
                break;
            case "theory":
                this.theoryRepository.save((Theory)likeAble);
                break;
            default:
                throw new IllegalArgumentException("Unknown like type");
        }

        return targetType+" " + likeAble.getLikeableId();
    }

    public String addComment(String targetID, String targetType, String commentBody, MultipartFile file, User user) throws IOException{
        Commentable commentable;
        Comment comment= new Comment();
        comment.setComment_img_url(this.fileService.uploadMedia(file, user.getUserId()));
        comment.setCommentBody(commentBody);
        comment.setUser(user);


        switch (targetType.toLowerCase()) {

            case "post":
                commentable = this.postRepository.getPostBypost_Id(Integer.parseInt(targetID)).orElseThrowCommentable();
                break;
            case "answer":
                commentable = this.ansRepository.findByanswerId(Integer.parseInt(targetID)).orElseThrowCommentable();
                break;
            case "theory":
                commentable = this.theoryRepository.getTheoryBytheory_Id(Integer.parseInt(targetID)).orElseThrowCommentable();
                break;
            default:
                throw new IllegalArgumentException("Unknown like type");
        }

        user.getComments().add(comment);
        this.userRepository.save(user);
        commentable.addComment(comment);
        return "You have Disiked the " + saveCommentable(targetType , commentable );
    }


    public String deleteComment(String targetID, String targetType, User user) throws IOException{
        Commentable commentable;

        switch (targetType.toLowerCase()) {

            case "post":
                commentable = this.postRepository.getPostBypost_Id(Integer.parseInt(targetID)).orElseThrowCommentable();
                break;
            case "answer":
                commentable = this.ansRepository.findByanswerId(Integer.parseInt(targetID)).orElseThrowCommentable();
                break;
            case "theory":
                commentable = this.theoryRepository.getTheoryBytheory_Id(Integer.parseInt(targetID)).orElseThrowCommentable();
                break;
            default:
                throw new IllegalArgumentException("Unknown like type");
        }

        Comment comment= this.commentRepository.getCommentBycommentId(Integer.parseInt(targetID));
        user.getComments().add(comment);
        this.userRepository.save(user);
        commentable.removeComment(comment);
        return "You have deleted the comment " + saveCommentable(targetType , commentable );
    }


    public String saveCommentable(String targetType, Commentable commentable){

        switch (targetType.toLowerCase()) {

            case "post":
                this.postRepository.save((Post)commentable);
                break;
            case "answer":
                this.ansRepository.save((Answer)commentable);
                break;
            case "theory":
                this.theoryRepository.save((Theory)commentable);
                break;
            default:
                throw new IllegalArgumentException("Unknown like type");
        }

        return targetType+" " + commentable.getCommentableId();
    }

    // public void disconnect(User user){
    //     var storedUser = userRepository.findById(user.getUserId()).orElse(null);
    //     if ((storedUser != null)) {
    //         storedUser.setStatus(Status.OFFLINE);
    //         userRepository.save(storedUser);
    //     }
    // }

    // public java.util.List<User> findConnectUsers(){
    //     return userRepository.findAllByStatus(Status.ONLINE);

    // }


    // public String UpdateUserProfile(UpdatedUserDetails updatedDetails, User user){
    //     user.setNickName(updatedDetails.getNickName());
    //     user.setDob(updatedDetails.getDob());
    //     user.setMaritalStatus(updatedDetails.getMaritalStatus());
    //     user.setPhoneNumber(updatedDetails.getPhoneNumber());
    //     user.setAddress(updatedDetails.getAddress());

    //     user.setFatherName(updatedDetails.getFatherName());
    //     user.setMotherName(updatedDetails.getMotherName());
    //     user.setSiblings(updatedDetails.getSiblings());

    //     user.setHighestEducation(updatedDetails.getHighestEducation());

    //     user.setEmployerName(updatedDetails.getEmployerName());
    //     user.setAnnualIncome(updatedDetails.getAnnualIncome());

    //     user.setHeight(updatedDetails.getHeight());
    //     user.setComplexion(updatedDetails.getComplexion());
    //     user.setBodyType(updatedDetails.getBodyType());

    //     user.setReligion(updatedDetails.getReligion());

    //     user.setDrinkingHabit(updatedDetails.getDrinkingHabit());
    //     user.setLoveToEat(updatedDetails.getLoveToEat());
    //     user.setSmokingHabit(updatedDetails.getSmokingHabit());

    //     user.setActivitiesTheyEnjoy(updatedDetails.getActivitiesTheyEnjoy());
    //     user.setHobbiesAndInterests(updatedDetails.getHobbiesAndInterests());

    //     user.setLeastAge(updatedDetails.getLeastAge());
    //     user.setMostAge(updatedDetails.getMostAge());
    //     user.setPreferredQualification(updatedDetails.getPreferredQualification());
    //     user.setPreferredOccupation(updatedDetails.getPreferredOccupation());
    //     user.setPreferredPlace(updatedDetails.getPreferredPlace());
        

    //     user.setAboutMyself(updatedDetails.getAboutMyself());

    //     user.setPreferredModeOfContact(updatedDetails.getPreferredModeOfContact());
    //     user.setPreferredTimeForContact(updatedDetails.getPreferredTimeForContact());

       
    //     this.userRepository.save(user);

    //     return "User with name "+user.getFirstName()+ "has updated profile.";
    // }

    
} 
