package lab.first.controllers;

import lab.first.repos.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequiredArgsConstructor
public class WebController {
    private final ProductGroupRepo productGroupRepo;
    private final ProductRepo productRepo;
    private final OrderRepo orderRepo;
    private final BookingRepo bookingRepo;
    private final WarehouseRepo warehouseRepo;


    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public ResponseEntity<Object> pr(){
//        productGroupRepo.delete(productGroupRepo.getById(3));
        orderRepo.delete(orderRepo.getById(1));
//        System.out.println(orderRepo.getById(1).getOrderProductWarehouses());
        return ResponseEntity.ok().build();
    }
}
