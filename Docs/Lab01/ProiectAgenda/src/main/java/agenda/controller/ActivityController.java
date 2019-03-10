package agenda.controller;

import agenda.exceptions.InvalidFormatException;
import agenda.model.base.Activity;
import agenda.repository.interfaces.RepositoryActivity;
import agenda.validator.ActivityValidator;
import agenda.validator.Validator;

import java.util.Date;
import java.util.List;

public class ActivityController
{
    private RepositoryActivity repositoryActivity;

    public ActivityController(RepositoryActivity repositoryActivity )
    {
        this.repositoryActivity = repositoryActivity;
    }
    public List<Activity> getActivities()
    {
        return repositoryActivity.getActivities();
    }

    public boolean addActivity(Activity activity) throws InvalidFormatException
    {
        if(!ActivityValidator.getInstance().validate(activity))
            throw new InvalidFormatException("Invalid activity");
        return repositoryActivity.addActivity(activity);

    }
    public boolean removeActivity(Activity activity) {
        return repositoryActivity.removeActivity(activity);
    }

    public boolean saveActivities() {
        return repositoryActivity.saveActivities();
    }

    public int count()
    {
        return repositoryActivity.count();
    }

    public List<Activity> activitiesByName(String name) {
        return repositoryActivity.activitiesByName(name);
    }
    public List<Activity> activitiesOnDate(String name, Date d)
    {
        return repositoryActivity.activitiesOnDate(name,d);
    }

}
