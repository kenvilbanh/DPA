package com.fa.DPA.service;

import com.fa.DPA.constant.Constant;
import com.fa.DPA.model.Order;
import com.fa.DPA.model.StaffAccount;
import com.fa.DPA.model.Status;
import com.fa.DPA.repos.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
public class OrderService {
    private OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    /**
     *
     * @param pageable
     * @return
     */
    public Page<Order> getAllOrderPage(Pageable pageable){
        try {
            return orderRepository.findAllCustomize(pageable, new HashSet<Long>(Arrays.asList((long)3,(long)4)));
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return null;
    }


    /**
     *
     * @param pageable
     * @return
     */
    public Page<Order> getAllOrderHistory(Pageable pageable){
        try {
            return orderRepository.findAllCustomize(pageable, new HashSet<>(Arrays.asList((long)1,(long)2)));
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return null;
    }


    /**
     *
     * @param id
     * @return
     */
    public Order findOrderById(Long id){
        return orderRepository.findById(id).
                orElseThrow(()-> new EntityNotFoundException("Order with this id: " + id + " is not found"));
    }


    /**
     *
     * @param id
     * @throws EntityNotFoundException
     */
    public void softDelete(Long id) throws EntityNotFoundException{
        Order order = this.findOrderById(id);
        System.out.println(order.toString());
        if (order.getStatus().getStatus().equals(Constant.PENDING)) {
            Status status = new Status();
            status.setId((long) 2);
            order.setStatus(status);
            orderRepository.save(order);
        }
    }

    /**
     *
     * @param pageable
     * @return
     */
    public Page<Order> getAll(Pageable pageable) {
        try {
            return orderRepository.findAll(pageable);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return null;
    }

    /**
     *
     * @param order
     * @return
     */
    public Order save(Order order){
        try{
           return orderRepository.save(order);
        }catch (Exception ex){
            System.out.println(ex);
        }

        return null;
    }

    /**
     *
     * @param pageable
     * @return
     */
    public Page<Order> getAllWaitOrder(Pageable pageable) {
        try{
            return orderRepository.findAllWait(pageable);
        }catch (Exception ex){
            System.out.println(ex);
        }
        return null;
    }


    /**
     *
     * @param staffId
     * @return
     */
    public int countOrderProcessingWithStaff(Long staffId){
        StaffAccount staffAccount = new StaffAccount();
        staffAccount.setId(staffId);
        Status status = new Status();
        status.setId(Constant.ID_PROCESS);
        try{
            return orderRepository.countOrderByStaffAccountAndStatus(staffAccount, status);
        }catch (Exception ex){
            System.out.println(ex);
        }
        return -1;
    }

    /**
     *
     * @param id
     * @return
     */
    public int checkExistByID(Long id){
        Order order = new Order();
        order.setId(id);
        try{
            return orderRepository.existsOrderById(order) == true ? 1 : 0;
        }catch (Exception ex){
            System.out.println(ex);
        }
        return -1;
    }
}
