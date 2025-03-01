package com.openclassrooms.mddapi.repository;

import com.openclassrooms.mddapi.model.Topic;
import com.openclassrooms.mddapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.mddapi.model.Post;

import java.util.Collection;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{

//    List<Post> findAllByUserInAndTopicIn(Collection<User> user, Collection<Topic> topic);

    List<Post> findAllByTopicIn(Collection<Topic> topic);
}
