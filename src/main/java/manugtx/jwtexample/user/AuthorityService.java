package manugtx.jwtexample.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorityService {

    private final AuthorityRepository authorityRepository;

    @Autowired
    public AuthorityService(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    public Authority getAuthorityByName(String authorityName) {
        return authorityRepository.findByAuthority(authorityName)
                .orElseThrow(() -> new RuntimeException("Authority not found: " + authorityName));
    }
}
