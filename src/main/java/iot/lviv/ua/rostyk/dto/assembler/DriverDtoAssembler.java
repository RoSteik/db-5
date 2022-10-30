/**
 * @author RoSteik (Telegram: @RoSteik)
 * Project: rostyk
 * Package: iot.lviv.ua.rostyk.dto.assembler
 * Class: DriverDtoAssembler
 */

package iot.lviv.ua.rostyk.dto.assembler;

import iot.lviv.ua.rostyk.controller.DriverController;
import iot.lviv.ua.rostyk.domain.Driver;
import iot.lviv.ua.rostyk.dto.DriverDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class DriverDtoAssembler implements RepresentationModelAssembler<Driver, DriverDto> {
    @Override
    public DriverDto toModel(Driver entity) {
        DriverDto driverDto = DriverDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .rating(entity.getRating())
                .completedOrders(entity.getCompletedOrders())
                .isVacant(entity.getIsVacant())
                .build();
        Link selfLink = linkTo(methodOn(DriverController.class).getDriver(Math.toIntExact(driverDto.getId()))).withSelfRel();
        driverDto.add(selfLink);
        Link carLink = linkTo(methodOn(DriverController.class).getAllCarsForDriver(Math.toIntExact(entity.getId()))).withRel("cars");
        driverDto.add(carLink);
        return driverDto;
    }

    @Override
    public CollectionModel<DriverDto> toCollectionModel(Iterable<? extends Driver> entities) {
        CollectionModel<DriverDto> driverDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(DriverController.class).getAllDrivers()).withSelfRel();
        driverDtos.add(selfLink);
        return driverDtos;
    }

    public CollectionModel<DriverDto> toCollectionModel(Iterable<? extends Driver> entities, Link link) {
        CollectionModel<DriverDto> driverDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        driverDtos.add(link);
        return driverDtos;
    }
}