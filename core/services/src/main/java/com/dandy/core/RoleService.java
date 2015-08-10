package com.dandy.core;

import java.util.List;

public class RoleService {
    private RoleDao roleDao;

    public List<Role> getRoles() {
        roleDao = new RoleDao();
        return roleDao.getRoles();
    }

}
