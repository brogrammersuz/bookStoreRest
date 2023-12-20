package uz.brogrammers.bookStoreRest.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.brogrammers.bookStoreRest.user.entity.Role;
import uz.brogrammers.bookStoreRest.user.enums.RoleName;
import uz.brogrammers.bookStoreRest.user.repository.RoleRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Role findByName(RoleName roleName){
        return roleRepository.findByName(roleName)
                .orElseThrow(()->new RuntimeException("No role found by name " + roleName.name()));
    }
}
