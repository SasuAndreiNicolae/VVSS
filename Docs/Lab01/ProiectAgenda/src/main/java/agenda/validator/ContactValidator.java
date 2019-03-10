package agenda.validator;

import agenda.model.Utility.ContactUtility;
import agenda.model.base.Contact;

public class ContactValidator implements Validator<Contact>
{
    private static ContactValidator contactValidator;
    private ContactValidator()
    {

    }

    public static ContactValidator getInstance()
    {
        if(contactValidator==null)
            contactValidator= new ContactValidator();
        return contactValidator;
    }
    @Override
    public boolean validate(Contact contact)
    {
        try
        {
            ContactUtility.validAddress(contact.getAddress());
            ContactUtility.validName(contact.getName());
            ContactUtility.validTelefon(contact.getTelefon());
            return true;
        }
        catch (Exception e)
        {
            return false;
        }

    }
}
