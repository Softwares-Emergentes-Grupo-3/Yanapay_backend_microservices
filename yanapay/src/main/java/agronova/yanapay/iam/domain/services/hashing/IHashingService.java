package agronova.yanapay.iam.domain.services.hashing;

public interface IHashingService {
    String encode(CharSequence rawPassword);
    boolean matches(CharSequence rawPassword, String encodedPassword);
}
