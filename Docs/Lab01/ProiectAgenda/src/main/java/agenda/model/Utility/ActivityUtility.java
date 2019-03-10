package agenda.model.Utility;

import agenda.model.base.Activity;
import agenda.model.base.Contact;
import agenda.repository.interfaces.RepositoryContact;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class ActivityUtility
{
    public static Activity fromString(String line, RepositoryContact repcon) {
        try {
            String[] str = line.split("#");
            String name = str[0];
            Date start = new Date(Long.parseLong(str[1]));
            Date duration = new Date(Long.parseLong(str[2]));
            String description = str[3];
            List<Contact> conts = new LinkedList<Contact>();
            for (int i = 5; i < str.length; i++) {
                conts.add(repcon.getByName(str[i]));
            }
            return new Activity(name, start, duration, conts, description);
        } catch (Exception e) {
            return null;
        }
    }

    public static String toReadableString(Activity activity)
    {
        StringBuilder sb = new StringBuilder();
        sb.append(activity.getName());
        sb.append("#");
        sb.append(activity.getStart());
        sb.append("#");
        sb.append(activity.getDuration());
        sb.append("#");
        sb.append(activity.getDescription());
        sb.append("#");
        return sb.toString();
    }
    public boolean intersect(Activity a1 ,Activity a2) {
        if (a1.getStart().compareTo(a2.getDuration()) < 0
                && a2.getStart().compareTo(a1.getDuration()) < 0)
            return true;
        return false;
    }
}
