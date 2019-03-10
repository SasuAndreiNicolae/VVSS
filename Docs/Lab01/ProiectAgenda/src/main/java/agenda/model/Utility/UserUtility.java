package agenda.model.Utility;

import agenda.model.base.User;

public class UserUtility
{
    public static User fromString(String s)
    {
        String[] str = s.split("#");
        try
        {
            return new User(str[0], str[1], str[2]);
        }
        catch (Exception e)
        {
            return null;
        }
    }
}
