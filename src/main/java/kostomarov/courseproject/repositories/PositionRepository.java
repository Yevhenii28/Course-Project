package kostomarov.courseproject.repositories;

import kostomarov.courseproject.models.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {
    @Query("select p from Position p")
    List<Position> getPositions();

    Position getPositionByName(String name);
}
