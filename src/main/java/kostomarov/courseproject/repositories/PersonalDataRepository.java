package kostomarov.courseproject.repositories;

import kostomarov.courseproject.models.PersonalData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonalDataRepository extends JpaRepository<PersonalData, Long> {
    @Query("select p from PersonalData p")
    List<PersonalData> getPersonalDatas();

    PersonalData getPersonalDataByEmployee_Id(Long Id);

    @Query("SELECT p.employee.id from PersonalData p where p.email = :email")
    Long findIdByEmail(String email);
}
