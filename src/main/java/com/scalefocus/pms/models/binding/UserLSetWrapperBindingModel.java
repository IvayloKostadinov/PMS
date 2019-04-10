// All Rights Reserved, Copyright © ScaleFocus

package com.scalefocus.pms.models.binding;

import java.util.Set;

public class UserLSetWrapperBindingModel {

    private Set<UserBindingModel> users;

    public Set<UserBindingModel> getUsers() {
        return users;
    }

    public void setUsers(Set<UserBindingModel> users) {
        this.users = users;
    }

    public void addUser(UserBindingModel user){
        users.add(user);
    }
}
