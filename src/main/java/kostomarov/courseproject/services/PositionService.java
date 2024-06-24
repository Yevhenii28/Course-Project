package kostomarov.courseproject.services;

import kostomarov.courseproject.models.Department;
import kostomarov.courseproject.models.Position;
import kostomarov.courseproject.repositories.PositionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PositionService {
    private final PositionRepository positionRepository;

    public List<Position> getPositions() {
        return positionRepository.getPositions();
    }

    public Position getPositionByName(String name) {
        return positionRepository.getPositionByName(name);
    }

    public List<Position> getPositionsByDepartmenName(String department) {
        return positionRepository.getPositionsByDepartment_Name(department);
    }
}
