package lab.first.controllers;

import lab.first.dto.ProductGroup;
import lab.first.repos.ProductGroupRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class WebController {
    private final ProductGroupRepo productGroupRepo;

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public ResponseEntity<Object> pr(){
        ProductGroup group = new ProductGroup("name");
        group.setDescription("descr");
        productGroupRepo.save(group);
        return ResponseEntity.ok().build();
    }
}
