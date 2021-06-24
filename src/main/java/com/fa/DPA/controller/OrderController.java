package com.fa.DPA.controller;

import com.fa.DPA.constant.Constant;
import com.fa.DPA.dto.OrderDTO;
import com.fa.DPA.model.CustomerAccount;
import com.fa.DPA.model.CustomerContact;
import com.fa.DPA.model.Order;
import com.fa.DPA.model.Status;
import com.fa.DPA.service.OrderService;
import com.fa.DPA.service.StaffAccountService;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {
    private OrderService orderService;
    private StaffAccountService staffAccountService;

    @Autowired
    public OrderController(OrderService orderService, StaffAccountService staffAccountService) {
        this.orderService = orderService;
        this.staffAccountService = staffAccountService;
    }


    /**
     * @param page
     * @param type
     * @return
     */
    public ResponseEntity<Map<String, Object>> process(int page, int type) {
        Map<String, Object> response = new HashMap<>();
        List<Order> orderList;
        List<OrderDTO> orderDTOS = new ArrayList<>();
        Pageable pageable = PageRequest.of(page, Constant.DEFAULT_PAGE_SIZE);
        try {
            Page<Order> orderPage = null;
            switch (type) {
                case 0:
                    orderPage = orderService.getAll(pageable);
                    break;
                case 1:
                    orderPage = orderService.getAllOrderPage(pageable);
                    break;
                case 2:
                    orderPage = orderService.getAllOrderHistory(pageable);
                    break;
                case 3:
                    orderPage = orderService.getAllWaitOrder(pageable);
                    break;
            }

            orderList = orderPage.getContent();
            for (Order o : orderList) {
                OrderDTO orderDTO = new OrderDTO(o);
                orderDTOS.add(orderDTO);
            }
            response.put("orderList", orderDTOS);
            response.put("currentPage", orderPage.getNumber());
            response.put("totalPage", orderPage.getTotalPages());
            response.put("totalItem", orderPage.getTotalElements());
        } catch (Exception ex) {
            System.out.println(ex);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    /**
     * @param page
     * @return
     */
    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> getAll(@RequestParam(defaultValue = Constant.DEFAULT_NUM_PAGE) int page) {
        return process(page, 0);
    }


    /**
     * @param page
     * @return
     */
    @GetMapping("/queue")
    public ResponseEntity<Map<String, Object>> getOrderQueue(@RequestParam(defaultValue = Constant.DEFAULT_NUM_PAGE) int page) {
        return process(page, 1);
    }


    /**
     * @param id
     * @return
     */
    @DeleteMapping("/cancel/{id}")
    public ResponseEntity<Object> cancelOrder(@PathVariable("id") Long id) {
        try {
            orderService.softDelete(id);
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        HttpHeaders httpHeaders = new HttpHeaders();
        try {
            httpHeaders.setLocation(new URI("/order"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(httpHeaders, HttpStatus.OK);
    }


    /**
     * @param id
     * @return
     */
    @GetMapping("/detail/{id}")
    public ResponseEntity<OrderDTO> getDetail(@PathVariable("id") Long id) {
        Order order;
        try {
            order = orderService.findOrderById(id);
        } catch (EntityNotFoundException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        if (order.getTotalPrice() == null || order.getTotalPrice().trim().length() == 0) {
            order.setTotalPrice("Under processing");
        }
        OrderDTO orderDTO = new OrderDTO(order);
        return new ResponseEntity<>(orderDTO, HttpStatus.OK);
    }

    /**
     * @return
     */
    @GetMapping("/history")
    public ResponseEntity<Map<String, Object>> viewHistory(@RequestParam(defaultValue = Constant.DEFAULT_NUM_PAGE) int page) {
        return process(page, 2);
    }

    HttpHeaders httpHeaders = new HttpHeaders();

    @PutMapping("/change-status/{id}")
    public ResponseEntity<Object> changeOrderStatus(@PathVariable("id") Long id, @RequestParam("status") Long idStatus) {
        Order order;
        try {
            order = orderService.findOrderById(id);
        } catch (EntityNotFoundException | NullPointerException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        Status status = new Status();
        status.setId(idStatus);
        order.setStatus(status);
        Order orderReturn = orderService.save(order);
        if (orderReturn == null) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        try {
            httpHeaders.setLocation(new URI("/all"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(httpHeaders, HttpStatus.OK);
    }


    /**
     * @param page
     * @return
     */
    @GetMapping("/all-wait-confirm")
    public ResponseEntity<Map<String, Object>> getAlWaitConfirmOrder(
            @RequestParam(defaultValue = Constant.DEFAULT_NUM_PAGE) int page) {
        return process(page, 3);
    }


    /**
     * @param idOrder
     * @return
     */
    @PostMapping("/confirm")
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> confirmOrder(@RequestParam("id") Long idOrder,
                                               @RequestParam("id_staff") Long idStaff) {

        if (orderService.checkExistByID(idOrder) == 1 && staffAccountService.checkExistByID(idStaff) == 1) {
            int countProcess = orderService.countOrderProcessingWithStaff(idStaff);
            System.out.println("Number of count processing: " + countProcess);
            if (countProcess == -1) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            } else {
                if (countProcess > Constant.LIMIT_ORDER_PROCESS) {
                    return new ResponseEntity<>(Constant.LIMIT_ORDER_PROCESS_MESSAGE, HttpStatus.OK);
                }

                LocalDate confirmDate = LocalDate.now();

            }
        }

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


}
