package ws.beauty.salon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ws.beauty.salon.model.RescheduleRequest;

@Repository
public interface RescheduleRequestRepository extends JpaRepository<RescheduleRequest, Integer> {

    //  Buscar solicitudes por estado 
    List<RescheduleRequest> findByStatus(String status);

    //  Buscar todas las solicitudes de un cliente por su ID
    List<RescheduleRequest> findByClientId(Integer clientId);

    //  Buscar solicitudes con texto en el motivo
    @Query("SELECT r FROM RescheduleRequest r WHERE LOWER(r.reason) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<RescheduleRequest> searchByReason(@Param("keyword") String keyword);

    //  Buscar solicitudes más recientes primero
    @Query("SELECT r FROM RescheduleRequest r ORDER BY r.createdAt DESC")
    List<RescheduleRequest> findAllOrderByCreatedAtDesc(); 
}
