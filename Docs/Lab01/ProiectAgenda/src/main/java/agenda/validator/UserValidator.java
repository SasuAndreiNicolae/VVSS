package agenda.validator;

import agenda.model.base.User;

public class UserValidator implements Validator<User>
{
    private static UserValidator userValidator;
    private UserValidator()
    {

    }

    public static UserValidator getInstance()
    {
        if(userValidator==null)
            userValidator= new UserValidator();
        return userValidator;
    }

    @Override
    public boolean validate(User user)
    {
        if(user.getName()==null||user.getName().length()==0)
            return false;
        if(user.getUsername()==null||user.getUsername().length()==0)
            return false;
        return true;
    }
}
