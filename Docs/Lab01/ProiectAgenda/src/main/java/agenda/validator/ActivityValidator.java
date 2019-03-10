package agenda.validator;

import agenda.model.base.Activity;

public class ActivityValidator implements Validator<Activity>
{
    private static ActivityValidator activityValidator;
    private ActivityValidator()
    {

    }

    public static ActivityValidator getInstance()
    {
        if(activityValidator==null)
            activityValidator= new ActivityValidator();
        return activityValidator;
    }

    @Override
    public boolean validate(Activity activity)
    {
        int ok= activity.getStart().compareTo(activity.getDuration());
        if(ok<=0)
            return false;
        return activity.getName() != null && activity.getName().length() != 0;
    }
}
