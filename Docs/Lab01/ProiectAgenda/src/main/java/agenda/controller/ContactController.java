package agenda.controller;

import agenda.exceptions.InvalidFormatException;
import agenda.model.base.Contact;
import agenda.repository.interfaces.RepositoryContact;
import agenda.validator.ContactValidator;

import java.util.List;

public class ContactController
{
    private RepositoryContact repositoryContact;

    public ContactController(RepositoryContact repositoryContact)
    {
        this.repositoryContact = repositoryContact;
    }

    public List<Contact> getContacts()
    {
        return repositoryContact.getContacts();
    }

    public void addContact(Contact contact) throws InvalidFormatException
    {
        if(!ContactValidator.getInstance().validate(contact))
            throw  new InvalidFormatException("Invalid contact");
        repositoryContact.addContact(contact);
    }
    public boolean removeContact(Contact contact) {
        return repositoryContact.removeContact(contact);
    }
    public boolean saveContracts() {
        return repositoryContact.saveContracts();
    }
    public int count()
    {
        return repositoryContact.count();
    }
}
