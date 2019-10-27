package naumen.livinir.authorization.repo;


import naumen.livinir.entity.Resident;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResidentRepo extends JpaRepository<Resident, Long>
{
    Optional<Resident> findByUsername(String username);
}