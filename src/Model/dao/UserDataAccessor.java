package Model.dao;

import Model.map.UserData;

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
    public UserData create(UserData obj) {
        return null;
    }

    @Override
    public UserData update(UserData obj) {
        return null;
    }

    @Override
    public void delete(UserData obj) {

    }
}
