package com.rafetdurgut.springportal.services;

import com.rafetdurgut.springportal.models.Comment;
import com.rafetdurgut.springportal.models.Post;
import com.rafetdurgut.springportal.repos.CommentRepository;
import com.rafetdurgut.springportal.repos.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@Transactional
public class PostService {

    private  final PostRepository postRepository;
    private final CommentRepository commentRepository;
    public PostService(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    public Post addPost(Post post)
    {
        return postRepository.save(post);
    }

    public List<Post> getAllPost()
    {
        return postRepository.findAll();
    }
    public List<Post> getAllPostByUserId(Long userId)
    {
        return postRepository.findAllByUserId(userId);
    }
    public Post getById(Long id)
    {
        Optional<Post> post = postRepository.findById(id);
        if(post == null)
            throw new ResponseStatusException(NOT_FOUND, "Post is not found");
        return post.get();
    }

    public Post addComment(Long id, Comment comment) {
        Post post = getById(id);
        comment.setPost(post);
        commentRepository.save(comment);
        return  post;
    }
}
