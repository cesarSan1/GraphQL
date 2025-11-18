package ws.beauty.salon.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ws.beauty.salon.model.ServicePriceHistory;
@Repository
public interface ServicePriceHistoryRepository extends JpaRepository<ServicePriceHistory, Integer>{

    Page<ServicePriceHistory> findAll(Pageable pageable);
    
    Page<ServicePriceHistory> findByService_Id(Integer serviceId, Pageable pageable);  
    
    Page<ServicePriceHistory> findByChangedBy_Id(Integer userId, Pageable pageable);

    List<ServicePriceHistory> findByService_Id(Integer serviceId); 
    
    List<ServicePriceHistory> findByChangedBy_Id(Integer userId);
    
    List<ServicePriceHistory> findByOldPriceGreaterThan(Double value);
    
    List<ServicePriceHistory> findByNewPriceLessThan(Double value);
    // Último cambio de precio de un servicio
    Optional<ServicePriceHistory> findFirstByService_IdOrderByChangedAtDesc(Integer serviceId);  
    @Query("SELECT s FROM ServicePriceHistory s ORDER BY s.changedAt DESC")
    List<ServicePriceHistory> findAllOrderByChangedAtDesc();

    @Query("SELECT s FROM ServicePriceHistory s WHERE s.changedAt BETWEEN :startDate AND :endDate ORDER BY s.changedAt DESC")
    List<ServicePriceHistory> findByChangedAtBetween(
        @Param("startDate") LocalDateTime startDate,
        @Param("endDate") LocalDateTime endDate
    );
}
