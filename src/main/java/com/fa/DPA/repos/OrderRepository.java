package com.fa.DPA.repos;

import com.fa.DPA.model.Order;
import com.fa.DPA.model.StaffAccount;
import com.fa.DPA.model.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface OrderRepository extends BaseRepository<Order> {

    /**
     *
     * @return
     */
    @Override
    List<Order> findAll();


    /**
     *
     * @param pageable
     * @param ids
     * @return
     */
    @Query("select o from Order o inner join o.constructionDrawings cd where o.status.id in :ids")
    Page<Order> findAllCustomize(Pageable pageable, Set<Long> ids);


    /**
     *
     * @param pageable
     * @return
     */
    @Query("select o from Order o where o.staffAccount.id is null")
    Page<Order> findAllWait(Pageable pageable);

    /**
     *
     * @param order
     * @return
     */
    boolean existsOrderById(Order order);

    /**
     *
     * @param staffAccount
     * @param status
     * @return
     */
    int countOrderByStaffAccountAndStatus(StaffAccount staffAccount, Status status);
}
