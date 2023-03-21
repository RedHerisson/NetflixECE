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
    public int create(User obj) {

        return 0;
    }

    @Override
    public int update(User obj) {

        return 0;
    }

    @Override
    public void delete( int id ) {

    }
}
