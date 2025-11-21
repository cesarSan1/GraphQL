package ws.beauty.salon.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ws.beauty.salon.model.StylistService;
import ws.beauty.salon.model.StylistServiceId;

@Repository
public interface StylistServiceRepository extends JpaRepository<StylistService, StylistServiceId> {

    Page<StylistService> findByStylist_Id(Integer stylistId, Pageable pageable);

    // Buscar todos los servicios que ofrece un estilista
    List<StylistService> findByStylist_Id(Integer stylistId);

    @Query("SELECT ss FROM StylistService ss " +
       "LEFT JOIN FETCH ss.stylist " +
       "LEFT JOIN FETCH ss.service " +
       "LEFT JOIN FETCH ss.appointment " +
       "WHERE ss.id.appointmentId = :appointmentId " +
       "AND ss.id.serviceId = :serviceId " +
       "AND ss.id.stylistId = :stylistId")
    Optional<StylistService> findByCompositeId(
            @Param("stylistId") Integer stylistId,
            @Param("serviceId") Integer serviceId,
            @Param("appointmentId") Integer appointmentId
    );

    @Query("SELECT DISTINCT ss FROM StylistService ss " +
           "LEFT JOIN FETCH ss.stylist " +
           "LEFT JOIN FETCH ss.service " +
           "LEFT JOIN FETCH ss.appointment")
    List<StylistService> findAllWithRelations();

    // Buscar todos los estilistas que ofrecen un servicio
    List<StylistService> findByService_Id(Integer serviceId);

   // Buscar por nombre de estilista
     @Query("SELECT ss FROM StylistService ss WHERE LOWER(CONCAT(ss.stylist.firstName, ' ', ss.stylist.lastName)) LIKE LOWER(CONCAT('%', :name, '%'))")
    Page<StylistService> findByStylistNameContaining(@Param("name") String name, Pageable pageable);
    
    // Buscar por nombre de servicio
     @Query("SELECT ss FROM StylistService ss WHERE LOWER(ss.service.serviceName) LIKE LOWER(CONCAT('%', :serviceName, '%'))")
    Page<StylistService> findByServiceNameContaining(@Param("serviceName") String serviceName, Pageable pageable); 
}

