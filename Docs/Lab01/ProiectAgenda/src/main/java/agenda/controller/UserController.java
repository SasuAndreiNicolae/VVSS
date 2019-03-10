package agenda.controller;

import agenda.model.base.User;
import agenda.repository.interfaces.RepositoryUser;
import java.util.List;

public class UserController
{
    private RepositoryUser repositoryUser;

    public UserController(RepositoryUser repositoryUser )
    {
        this.repositoryUser = repositoryUser;
    }

    public User getByUsername(String username) {
        return repositoryUser.getByUsername(username);
    }
    public User getByName(String name) {
        return repositoryUser.getByName(name);
    }

    public boolean changePasswd(User user, String oldPasswd, String newPasswd) {
        return repositoryUser.changePasswd(user,oldPasswd,newPasswd);
    }
    public boolean save() {
        return repositoryUser.save();
    }
    public List<User> getUsers()
    {
        return repositoryUser.getUsers();
    }

    public int getCount()
    {
        return repositoryUser.getCount();
    }
}
