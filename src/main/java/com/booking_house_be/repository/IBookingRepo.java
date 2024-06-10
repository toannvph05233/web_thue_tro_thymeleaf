package com.booking_house_be.repository;

import com.booking_house_be.dto.query.SpendingDto;
import com.booking_house_be.entity.Booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;;

public interface IBookingRepo extends JpaRepository<Booking, Integer> {
    @Query(nativeQuery = true, value = "select * from booking where account_id= :idAccount")
    Page<Booking> getByIdAccount(Pageable pageable, @Param("idAccount") int idAccount);

    @Query(nativeQuery = true, value = "select * from booking where account_id= :idAccount and (:status is null or status =:status)")
    List<Booking> getByIdAccountAndStatus(@Param("idAccount") int idAccount, @Param("status") String status);

    List<Booking> getBookingByHouseOwnerId(int OwnerId);

    @Query("SELECT b from Booking b WHERE b.house.id = :houseId AND b.status IN ('Chờ xác nhận', 'Chờ khám')")
    List<Booking> findAllByHouseIdAndStatus(@Param("houseId") int houseId);

    Booking findById(int id);


    @Query(value =
            "SELECT DAY(b.startTime) AS day, " +
                    "SUM(CASE WHEN b.status = 'Khám xong' THEN 0 ELSE 0 END) AS revenue " +
                    "FROM Booking b " +
                    "LEFT JOIN b.house d " +
                    "WHERE YEAR(b.startTime) = :year " +
                    "AND MONTH(b.startTime) = :month " +
                    "AND d.owner.id = :ownerId " +
                    "AND DAY(b.startTime) BETWEEN :startDay AND :endDay " +
                    "GROUP BY DAY(b.startTime)"
    )
    List<Object[]> getDailyRevenueByOwnerAndWeek(
            @Param("ownerId") int ownerId,
            @Param("month") int month,
            @Param("year") int year,
            @Param("startDay") int startDay,
            @Param("endDay") int endDay);


    @Query("SELECT b FROM Booking b " +
            "JOIN House h ON h.id = b.house.id " +
            "WHERE (:nameSearch IS NULL OR h.name LIKE CONCAT('%', :nameSearch, '%')) " +
            "AND (:startTime IS NULL OR DATE(b.startTime) = DATE(:startTime)) " +
            "AND (:status IS NULL OR b.status LIKE CONCAT('%', :status, '%')) " +
            "AND h.owner.id = :ownerId")
    Page<Booking> findByHouseAndStartTimeAndEndTimeAndStatus(
            @Param("ownerId") int ownerId,
            @Param("nameSearch") String nameSearch,
            @Param("status") String status,
            @Param("startTime") LocalDateTime startTime,
            Pageable pageable
    );

    @Query(nativeQuery = true, value = "select sum(b.total) as spending " +
            "from booking b where status = 'Đã trả phòng' and b.account_id = :idAccount ; ")
    SpendingDto getSpendingUser(@Param("idAccount") int idAccount);


    @Query(value = "SELECT * FROM booking b " +
            "JOIN house h ON h.id = b.house_id " +
            "WHERE (:houseName IS NULL OR h.name LIKE CONCAT('%', :houseName, '%')) " +
            "AND (:startTime IS NULL OR DATE(b.start_time) = DATE(:startTime)) " +
            "AND (:status IS NULL OR b.status LIKE CONCAT('%', :status, '%')) " +
            "AND b.account_id = :idAccount", nativeQuery = true)
    Page<Booking> getHistoryRentalByIdAccount(Pageable pageable, @Param("idAccount") int idAccount
            , @Param("houseName") String houseName
            , @Param("startTime") LocalDateTime startTime
            , @Param("status") String status);
}
