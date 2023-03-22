package com.Model.dao;

import com.Model.map.UserData;

import java.sql.SQLException;

public class UserDataAccessor extends Accessor<UserData> {

    public UserDataAccessor() throws SQLException, ClassNotFoundException {
        super();
    }

    @Override
    public UserData find(int id) {
        return null;
    }

    @Override
    public int create(UserData obj) {
        return 0;
    }

    @Override
    public int update(UserData obj) {

        return 0;
    }

    @Override
    public void delete(int id ) {

    }
}
