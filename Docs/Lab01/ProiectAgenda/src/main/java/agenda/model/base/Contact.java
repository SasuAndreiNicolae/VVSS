package agenda.model.base;

import agenda.exceptions.InvalidFormatException;

public class Contact {
	private String Name;
	private String Address;
	private String Telefon;
	
	public Contact(){
		Name = "";
		Address = "";
		Telefon = "";
	}
	
	public Contact(String name, String address, String telefon) throws InvalidFormatException{
		//if (!ContactUtility.validTelefon(telefon)) throw new InvalidFormatException("Cannot convert", "Invalid phone number");
		//if (!ContactUtility.validName(name)) throw new InvalidFormatException("Cannot convert", "Invalid name");
		//if (!ContactUtility.validAddress(address)) throw new InvalidFormatException("Cannot convert", "Invalid address");
		Name = name;
		Address = address;
		Telefon = telefon;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) throws InvalidFormatException {
		//if (!ContactUtility.validName(name)) throw new InvalidFormatException("Cannot convert", "Invalid name");
		Name = name;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) throws InvalidFormatException {
		//if (!ContactUtility.validAddress(address)) throw new InvalidFormatException("Cannot convert", "Invalid address");
		Address = address;
	}

	public String getTelefon() {
		return Telefon;
	}

	public void setTelefon(String telefon) throws InvalidFormatException {
		//if (!ContactUtility.validTelefon(telefon)) throw new InvalidFormatException("Cannot convert", "Invalid phone number");
		Telefon = telefon;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(Name);
		sb.append("#");
		sb.append(Address);
		sb.append("#");
		sb.append(Telefon);
		sb.append("#");
		return sb.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (! (obj instanceof Contact)) return false;
		Contact o = (Contact)obj;
		if (Name.equals(o.Name) && Address.equals(o.Address) &&
				Telefon.equals(o.Telefon))
			return true;
		return false;
	}
	
}
