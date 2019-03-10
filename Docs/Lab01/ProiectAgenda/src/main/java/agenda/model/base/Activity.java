package agenda.model.base;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import agenda.repository.interfaces.RepositoryContact;

public class Activity {
	private String name;
	private Date start;
	private Date duration;
	private List<Contact> contacts;
	private String description;
	
	public Activity(String name, Date start, Date end, List<Contact> contacts,
			String description) {
		this.name = name;
		this.description = description;
		if (contacts == null)
			this.contacts = new LinkedList<>();
		else
			this.contacts = new LinkedList<>(contacts);

		this.start = new Date();
		this.start.setTime(start.getTime());
		this.duration = new Date();
		this.duration.setTime(end.getTime());
	}

	public String getName() {
		return name;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getDuration() {
		return duration;
	}

	public void setDuration(Date duration) {
		this.duration = duration;
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Activity))
			return false;
		Activity act = (Activity) obj;
		if (act.description.equals(description) && start.equals(act.start)
				&& duration.equals(act.duration) && name.equals(act.name))
			return true;
		return false;
	}



	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(name);
		sb.append("#");
		sb.append(start.getTime());
		sb.append("#");
		sb.append(duration.getTime());
		sb.append("#");
		sb.append(description);
		sb.append("#");
		for (Contact c : contacts) {
			sb.append("#");
			sb.append(c.getName());
		}
		return sb.toString();
	}


}
