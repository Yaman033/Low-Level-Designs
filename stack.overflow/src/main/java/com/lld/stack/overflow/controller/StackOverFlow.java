package com.lld.stack.overflow.controller;

import com.lld.stack.overflow.entities.Answer;
import com.lld.stack.overflow.entities.Comment;
import com.lld.stack.overflow.entities.QuestionWithDetails;
import com.lld.stack.overflow.entities.Users;
import com.lld.stack.overflow.service.StackOverFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/stack/overflow")
public class StackOverFlow {

    @Autowired
    StackOverFlowService stackOverFlowService;

    @PostMapping("/create-user")
    public String userCreate(@RequestBody Map<String, Object> requestMap) {
        stackOverFlowService.createUser(requestMap);
        return "User Created Succesfully";
    }


    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@RequestBody Map<String, Object> requestMap) {
        try {
            Users user = stackOverFlowService.signIn(requestMap);
            if (user != null) {
                // In production, use proper session management or JWT
                return ResponseEntity.ok(user);
            }
            return ResponseEntity.status(401).body("Invalid credentials");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error signing in");
        }
    }


    @PostMapping("/post")
    public String post(@RequestBody Map<String, Object> requestMap) {
        stackOverFlowService.post(requestMap);
        return "Post Successfull";
    }

    @GetMapping("/questions")
    public List<QuestionWithDetails> getAllQuestions() {
        return stackOverFlowService.getAllQuestionsWithDetails();
    }

    @GetMapping("/questions/{id}")
    public QuestionWithDetails getQuestion(@PathVariable Long id) {
        return stackOverFlowService.getQuestionWithDetails(id);
    }

    @GetMapping("/questions/{questionId}/answers")
    public List<Answer> getAnswersForQuestion(@PathVariable Long questionId) {
        return stackOverFlowService.getAnswersForQuestion(questionId);
    }

    @GetMapping("/questions/{questionId}/comments")
    public List<Comment> getCommentsForQuestion(@PathVariable Long questionId) {
        return stackOverFlowService.getCommentsForQuestion(questionId);
    }

    @GetMapping("/answers/{answerId}/comments")
    public List<Comment> getCommentsForAnswer(@PathVariable Long answerId) {
        return stackOverFlowService.getCommentsForAnswer(answerId);
    }
}
