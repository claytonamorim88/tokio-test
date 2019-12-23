package com.example.api.web.rest;

import com.example.api.domain.Endereco;
import com.example.api.service.CustomerService;
import com.example.api.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/enderecos")
public class EnderecoController {

    private EnderecoService enderecoService;
    private CustomerService customerService;

    @Autowired
    public EnderecoController(CustomerService customerService, EnderecoService enderecoService){
        this.customerService = customerService;
        this.enderecoService = enderecoService;
    }

    @GetMapping("/{cep}")
    public Endereco getEnderecoFromApi(@PathVariable String cep){
        final String uri = "https://viacep.com.br/ws/" + cep + "/json/";
        RestTemplate restTemplate = new RestTemplate();
        Endereco endereco = restTemplate.getForObject(uri, Endereco.class);

        return endereco;
    }

    @GetMapping
    public List<Endereco> getEnderecos(){
        return enderecoService.findAll();
    }


    @PostMapping()
    public ResponseEntity<String> insertEndereco(@RequestParam String cep,
                               @RequestParam String numero,
                               @RequestParam long idCustomer){

        if(! customerService.isCustomerExist(idCustomer))
            return new ResponseEntity<>("", HttpStatus.OK);

        final String uri = "https://viacep.com.br/ws/" + cep + "/json/";
        RestTemplate restTemplate = new RestTemplate();
        Endereco endereco = restTemplate.getForObject(uri, Endereco.class);

        if(endereco == null)
            return new ResponseEntity<>("", HttpStatus.OK);

        endereco.setNumero(numero);
        endereco.setCustomer(customerService.findById(idCustomer).get());
        enderecoService.insertEndereco(endereco);
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }


}
