package customerlist.Controller;
import customerlist.Repository.CustomerListRepository;
import customerlist.Model.Customers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.ui.Model;


import java.util.List;

@Controller
public class CustomerListController {
    private final CustomerListRepository customerListRepository;

    @Autowired
    public CustomerListController(CustomerListRepository customerListRepository) {
        this.customerListRepository = customerListRepository;
    }

    @GetMapping(value = "/customerlist")
    public String customerList(  Model model) {
        List<Customers> customerList = customerListRepository.findAll();
        if (customerList != null) {
            model.addAttribute("customers", customerList);
        }
        return "customerList";
    }

    @PostMapping(value = "/customerlist")
    public String addToCustomerList( String firstName, Customers customers) {
        customers.setFirstName(firstName);
        customerListRepository.save(customers);
        return "redirect:/customerlist";
    }

    @GetMapping(value = "/")
    public String ind(){
        return "redirect:/customerlist";
    }

}