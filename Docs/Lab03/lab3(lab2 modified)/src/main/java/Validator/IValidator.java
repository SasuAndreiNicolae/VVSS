package Validator;

public interface IValidator<T>
{
    void validate(T t) throws Exception;
}
