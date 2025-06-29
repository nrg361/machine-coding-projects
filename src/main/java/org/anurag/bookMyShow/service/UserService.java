package org.anurag.bookMyShow.service;

import lombok.AllArgsConstructor;
import org.anurag.bookMyShow.models.User;
import org.anurag.bookMyShow.models.UserType;
import org.anurag.bookMyShow.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class UserService {
    List<User> userList;

    public User getUserDetails(String userId){
        return userList.stream().filter(user -> user.getUserId().equals(userId)).findFirst().orElse(null);
    }

    public String createUser(String name, UserType userType){
        User user = User.builder()
                .bookings(new ArrayList<>())
                .name(name)
                .userId(CommonUtils.getUniqueId())
                .userType(userType)
                .build();
        userList.add(user);
        return user.getUserId();
    }
}
