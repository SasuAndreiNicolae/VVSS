package agenda.model.Utility;

import agenda.exceptions.InvalidFormatException;
import agenda.model.base.Contact;

public class ContactUtility
{
    public static boolean validName(String str)
    {

//		String[] s = str.split("[\\p{Punct}\\s]+");
//		if (s.length>2) return false;
        if(str!=null&&str.length()>0)
            return true;
        return false;
    }

    public static boolean validAddress(String str)
    {
        if(str!=null&&str.length()>0)
            return true;
        return true;
    }

    public static boolean validTelefon(String tel)
    {
        //String[] s = tel.split("c");
        if(tel==null) return false;
        if (tel.charAt(0) == '+'&& tel.length()>1 ) return true;
        if (tel.charAt(0) == '0')return true;
        //if (s.length != 1) return false;
        return false;
    }
    public static Contact fromString(String str, String delim) throws InvalidFormatException
    {
        String[] s = str.split(delim);
        //if (s.length!=4) throw new InvalidFormatException("Cannot convert", "Invalid data");
        //if (!validTelefon(s[2])) throw new InvalidFormatException("Cannot convert", "Invalid phone number");
        //if (!validName(s[0])) throw new InvalidFormatException("Cannot convert", "Invalid name");
        //if (!validAddress(s[1])) throw new InvalidFormatException("Cannot convert", "Invalid address");

        return new Contact(s[0], s[1], s[2]);
    }
}
