package com.stolser.javatraining.webproject.model.dao.role;

import java.util.Set;

public interface RoleDao {
    Set<String> getRolesByUserName(String userName);
}
