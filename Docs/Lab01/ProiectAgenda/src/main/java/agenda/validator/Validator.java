package agenda.validator;

public interface Validator<T>
{
    boolean validate(T t);
}
