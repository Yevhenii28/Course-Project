package kostomarov.courseproject.services;

import kostomarov.courseproject.models.PersonalData;
import kostomarov.courseproject.repositories.PersonalDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonalDataService {
    private final PersonalDataRepository personalDataRepository;

    public List<PersonalData> getPersonalDatas() {
        return personalDataRepository.getPersonalDatas();
    }

    public PersonalData getPersonalDataByEmployee_Id(Long id) {
        return personalDataRepository.getPersonalDataByEmployee_Id(id);
    }

    public PersonalData getPersonalDataByEmployee_Email(String email) {
        Long id = personalDataRepository.findIdByEmail(email);
        return getPersonalDataByEmployee_Id(id);
    }

    public void addPersonalData(PersonalData personalData) {
        personalDataRepository.save(personalData);
    }

    public void editPersonalData(PersonalData personalData) {
        personalDataRepository.save(personalData);
    }
}
