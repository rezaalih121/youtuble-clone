package com.rezaalih121.youtubleclone.service;

import com.rezaalih121.youtubleclone.model.User;
import com.rezaalih121.youtubleclone.model.Video;
import com.rezaalih121.youtubleclone.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User getCurrentUser(){
       String sub = ((Jwt)(SecurityContextHolder.getContext().getAuthentication().getPrincipal())).getClaim("sub");
        return userRepository.findBySub(sub).orElseThrow(()->new IllegalArgumentException("Can not find user with sub - " + sub));
    }

    public void addToLikedVideos(String videoId) {

        User currentUser = getCurrentUser();
        currentUser.addToLikedVideos(videoId);
        userRepository.save(currentUser);

    }
// to make sure if video is liked by user we change like and dislike counts
    public boolean ifLikedVideo(String videoId){
         return getCurrentUser().getLikedVideos().stream().anyMatch(likedVideo -> likedVideo.equals(videoId));
    }
    public boolean ifDisLikedVideo(String videoId){
        return getCurrentUser().getDisLikedVideos().stream().anyMatch(disLikedVideo -> disLikedVideo.equals(videoId));
    }

    public void removeFromLikedVideos(String videoId) {
        User currentUser = getCurrentUser();
        currentUser.removeFromLikedVideos(videoId);
        userRepository.save(currentUser);
    }

    public void removeFromDisLikedVideos(String videoId) {
        User currentUser = getCurrentUser();
        currentUser.removeFromDisLikedVideos(videoId);
        userRepository.save(currentUser);
    }

    public void removeFromDislikedVideos(String videoId) {
        User currentUser = getCurrentUser();
        currentUser.removeFromDisLikedVideos(videoId);
        userRepository.save(currentUser);
    }

    public void addToDisLikedVideos(String videoId) {
        User currentUser = getCurrentUser();
        currentUser.addToDisLikedVideos(videoId);
        userRepository.save(currentUser);
    }

    public void addVideoToHistory(String videoId) {
        User currentUser = getCurrentUser();
        currentUser.addToVdieoHistory(videoId);
        userRepository.save(currentUser);
    }

    public void subscribeUser(String userId) {
        // Retrieve the current user and add the userId to the subscribed to users set
        // Retrieve the target user and add the current user to the subscribers lists
        User currentUser = getCurrentUser();
        currentUser.addToSubscribedToUsers(userId);

        User user = getUserById(userId);
        user.addToSubscriberss(userId);

        userRepository.save(currentUser);
        userRepository.save(user);

    }

    public void unSubscribeUser(String userId) {
        // Retrieve the current user and add the userId to the subscribed to users set
        // Retrieve the target user and add the current user to the subscribers lists
        User currentUser = getCurrentUser();
        currentUser.removeFromSubscribedToUsers(userId);

        User user = getUserById(userId);
        user.removeFromSubscriberss(userId);

        userRepository.save(currentUser);
        userRepository.save(user);

    }

    public Set<String> userHistory(String userId) {
        User user = getUserById(userId);
        return user.getVideoHistory();
    }

    public User getUserById(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Cannot find user with userId : " + userId));
        return user;
    }
}
