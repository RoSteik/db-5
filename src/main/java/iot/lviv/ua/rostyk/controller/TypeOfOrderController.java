/**
 * @author RoSteik (Telegram: @RoSteik)
 * Project: rostyk
 * Package: iot.lviv.ua.rostyk.controller
 * Class: TypeOfOrderController
 */

package iot.lviv.ua.rostyk.controller;

import iot.lviv.ua.rostyk.domain.TypeOfOrder;
import iot.lviv.ua.rostyk.dto.TypeOfOrderDto;
import iot.lviv.ua.rostyk.dto.assembler.TypeOfOrderDtoAssembler;
import iot.lviv.ua.rostyk.service.TypeOfOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/typeOfOrders")
public class TypeOfOrderController {

    @Autowired
    private TypeOfOrderService typeOfOrderService;
    @Autowired
    private TypeOfOrderDtoAssembler typeOfOrderDtoAssembler;

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<TypeOfOrderDto>> getAllTypeOfOrders() {
        List<TypeOfOrder> typeOfOrders = typeOfOrderService.findAll();
        CollectionModel<TypeOfOrderDto> typeOfOrderDtos = typeOfOrderDtoAssembler.toCollectionModel(typeOfOrders);
        return new ResponseEntity<>(typeOfOrderDtos, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TypeOfOrderDto> getTypeOfOrder(@PathVariable Integer id) {
        TypeOfOrder typeOfOrder = typeOfOrderService.findById(id);
        TypeOfOrderDto typeOfOrderDto = typeOfOrderDtoAssembler.toModel(typeOfOrder);
        return new ResponseEntity<>(typeOfOrderDto, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<TypeOfOrderDto> addTypeOfOrder(@RequestBody TypeOfOrder typeOfOrder) {
        TypeOfOrder newTypeOfOrder = typeOfOrderService.create(typeOfOrder);
        TypeOfOrderDto typeOfOrderDto = typeOfOrderDtoAssembler.toModel(newTypeOfOrder);
        return new ResponseEntity<>(typeOfOrderDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateTypeOfOrder(@RequestBody TypeOfOrder uTypeOfOrder, @PathVariable Integer id) {
        typeOfOrderService.update(id, uTypeOfOrder);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteTypeOfOrder(@PathVariable Integer id) {
        typeOfOrderService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
