package Model.dao;

import Model.map.User;

import java.sql.SQLException;

public class UserAccessor extends Accessor<User> {

    public UserAccessor() throws SQLException, ClassNotFoundException {
        super();
    }

    @Override
    public User find(int id) {
        return null;
    }

    @Override
    public User create(User obj) {
        return null;
    }

    @Override
    public User update(User obj) {
        return null;
    }

    @Override
    public void delete(User obj) {

    }
}
