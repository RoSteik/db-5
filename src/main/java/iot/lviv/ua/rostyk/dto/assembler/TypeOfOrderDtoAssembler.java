/**
 * @author RoSteik (Telegram: @RoSteik)
 * Project: rostyk
 * Package: iot.lviv.ua.rostyk.dto.assembler
 * Class: TypeOfOrderDtoAssembler
 */

package iot.lviv.ua.rostyk.dto.assembler;

import iot.lviv.ua.rostyk.controller.TypeOfOrderController;
import iot.lviv.ua.rostyk.domain.TypeOfOrder;
import iot.lviv.ua.rostyk.dto.TypeOfOrderDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class TypeOfOrderDtoAssembler implements RepresentationModelAssembler<TypeOfOrder, TypeOfOrderDto> {

    @Override
    public TypeOfOrderDto toModel(TypeOfOrder entity) {
        TypeOfOrderDto typeOfOrderDto = TypeOfOrderDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
        Link selfLink = linkTo(methodOn(TypeOfOrderController.class).getTypeOfOrder(typeOfOrderDto.getId())).withSelfRel();
        typeOfOrderDto.add(selfLink);
        return typeOfOrderDto;
    }

    @Override
    public CollectionModel<TypeOfOrderDto> toCollectionModel(Iterable<? extends TypeOfOrder> entities) {
        CollectionModel<TypeOfOrderDto> typeOfOrderDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(TypeOfOrderController.class).getAllTypeOfOrders()).withSelfRel();
        typeOfOrderDtos.add(selfLink);
        return typeOfOrderDtos;
    }
}
