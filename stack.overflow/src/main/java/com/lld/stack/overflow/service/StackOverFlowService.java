package com.lld.stack.overflow.service;

import com.lld.stack.overflow.entities.*;
import com.lld.stack.overflow.repository.AnswerRepository;
import com.lld.stack.overflow.repository.CommentRepository;
import com.lld.stack.overflow.repository.QuestionsRepository;
import com.lld.stack.overflow.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class StackOverFlowService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    QuestionsRepository questionsRepository;

    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    CommentRepository commentRepository;

    public void createUser(Map<String, Object> requestMap) {
        Users user = Users.builder().name(requestMap.get("userName").toString())
                .address(requestMap.get("address").toString())
                .contact(requestMap.get("contact").toString())
                .password(requestMap.get("password").toString())
                .email(requestMap.get("email").toString())
                .build();

        userRepository.save(user);
    }


    public void post(Map<String, Object> requestMap) {
        String type = requestMap.get("type").toString();
        String userName = requestMap.get("userName").toString();
        Users user = userRepository.findByName(userName);
        switch (type) {
            case "Question" -> {
                Question question = Question.builder()
                        .userId(user.getId())
                        .body(requestMap.get("body").toString())
                        .heading(requestMap.get("heading").toString())
                        .build();

                questionsRepository.save(question);

            }
            case "Answer" -> {
                Answer answer = Answer.builder()
                        .userId(user.getId())
                        .body(requestMap.get("body").toString())
                        .questionId(Long.parseLong(requestMap.get("questionId").toString()))
                        .build();

                answerRepository.save(answer);

            }

            case "Comment" -> {
                String subType = requestMap.get("subType").toString();
                switch (subType) {
                    case "Question" -> {

                        Comment commentOnQuestion = Comment.builder()
                                .userId(user.getId())
                                .body(requestMap.get("body").toString())
                                .questionId(Long.parseLong(requestMap.get("questionId").toString()))
                                .commentType(subType)
                                .build();

                        commentRepository.save(commentOnQuestion);
                    }
                    case "Answer" -> {

                        Comment commentOnAnswer = Comment.builder()
                                .userId(user.getId())
                                .body(requestMap.get("body").toString())
                                .answerId(Long.parseLong(requestMap.get("answerId").toString()))
                                .commentType(subType)
                                .build();

                        commentRepository.save(commentOnAnswer);

                    }
                }

            }
        }


    }

    public List<QuestionWithDetails> getAllQuestionsWithDetails() {
        List<Question> questions = questionsRepository.findAll();
        return questions.stream()
                .map(this::mapQuestionToQuestionWithDetails)
                .collect(Collectors.toList());
    }

    public QuestionWithDetails getQuestionWithDetails(Long id) {
        Question question = questionsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found"));
        return mapQuestionToQuestionWithDetails(question);
    }

    private QuestionWithDetails mapQuestionToQuestionWithDetails(Question question) {
        Users user = userRepository.findById(question.getUserId())
                .orElse(null);

        List<Answer> answers = answerRepository.findByQuestionId(question.getId());
        List<Comment> comments = commentRepository.findByQuestionId(question.getId());

        return QuestionWithDetails.builder()
                .id(question.getId())
                .heading(question.getHeading())
                .body(question.getBody())
                .userId(question.getUserId())
                .userName(user != null ? user.getName() : "Unknown")
                .answerCount(answers.size())
                .build();
    }

    public List<Answer> getAnswersForQuestion(Long questionId) {
        return answerRepository.findByQuestionId(questionId);
    }

    public List<Comment> getCommentsForQuestion(Long questionId) {
        return commentRepository.findByQuestionIdAndCommentType(questionId, "Question");
    }

    public List<Comment> getCommentsForAnswer(Long answerId) {
        return commentRepository.findByAnswerIdAndCommentType(answerId, "Answer");
    }

    public Users signIn(Map<String, Object> requestMap) {
        String userName = requestMap.get("userName").toString();
        String password = requestMap.get("password").toString();

        Users user = userRepository.findByName(userName);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }

        return null;
    }
}
