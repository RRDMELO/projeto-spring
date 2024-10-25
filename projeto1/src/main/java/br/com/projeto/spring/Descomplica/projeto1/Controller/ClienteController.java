package br.com.projeto.spring.Descomplica.projeto1.Controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import br.com.projeto.spring.Descomplica.projeto1.Repository.ClienteRepository;
import br.com.projeto.spring.Descomplica.projeto1.models.ClienteModel;
import br.com.projeto.spring.Descomplica.projeto1.models.ClienteRequestBody;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
public class ClienteController {
    @Autowired
    ClienteRepository clienteRepository;

    @Operation(summary = "Listar clientes")
    @GetMapping("/clientes")
    public ResponseEntity<List<ClienteModel>> getAllClientes() {
        List<ClienteModel> clienteModelList = clienteRepository.findAll();
        if (!clienteModelList.isEmpty()){
            for (ClienteModel cliente : clienteModelList) {
                Long id = cliente.getId();
                cliente.add(linkTo(methodOn(ClienteController.class).getClientById(id)).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(clienteModelList);
    }

    @Operation(summary = "Encontrar Cliente pelo Id")
    @GetMapping("/clientes/{id}")
    public ResponseEntity<Object> getClientById(@PathVariable(value = "id") Long id) {
        Optional<ClienteModel> cliente0 = clienteRepository.findById(id);
        if (cliente0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado.");
        }
        cliente0.get().add(linkTo(methodOn(ClienteController.class).getAllClientes()).withSelfRel());
        return ResponseEntity.status(HttpStatus.OK).body(cliente0.get());
    }

    @Operation(summary = "Criar Cliente")
    @PostMapping("/clientes")
    public ResponseEntity<ClienteModel> createCliente(@RequestBody @Valid ClienteRequestBody clienteRequestBody) {
        var ClienteModel = new ClienteModel();
        BeanUtils.copyProperties(clienteRequestBody, ClienteModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteRepository.save(ClienteModel));
    }

    @Operation(summary = "Atualizar um cliente")
    @PutMapping("/usuarios/{id}")
    public ResponseEntity<Object> updateUsuario(@PathVariable(value = "id") long id,
                                                @RequestBody ClienteRequestBody clienteRequestBody) {
        Optional<ClienteModel> cliente0 = clienteRepository.findById(id);
        if (cliente0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário " + id + " não encontrado.");
        }
        ClienteModel clienteModel = cliente0.get();
        BeanUtils.copyProperties(clienteRequestBody, clienteModel, getNullPropertyNames(clienteRequestBody));
       
        return ResponseEntity.status(HttpStatus.OK).body(clienteRepository.save(clienteModel));
    }

    private String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }


    @Operation(summary = "Deletar um cliente")
    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<Object> deleteUsuario(@PathVariable(value = "id") Long id) {
        Optional<ClienteModel> cliente0 = clienteRepository.findById(id);
        if (cliente0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
        }
        clienteRepository.delete(cliente0.get());
        return ResponseEntity.status(HttpStatus.OK).body("Usuário deletado.");
    }
}
