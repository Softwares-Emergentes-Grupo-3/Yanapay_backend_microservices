package agronova.yanapay.iam.domain.services.hashing.bcrypt;

import agronova.yanapay.iam.domain.services.hashing.IHashingService;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface BCryptHashingService extends IHashingService, PasswordEncoder {
}
