/**
 * @author RoSteik (Telegram: @RoSteik)
 * Project: rostyk
 * Package: iot.lviv.ua.rostyk.dto.assembler
 * Class: CarDtoAssembler
 */

package iot.lviv.ua.rostyk.dto.assembler;

import iot.lviv.ua.rostyk.controller.CarController;
import iot.lviv.ua.rostyk.domain.Car;
import iot.lviv.ua.rostyk.dto.CarDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CarDtoAssembler implements RepresentationModelAssembler<Car, CarDto> {
    @Override
    public CarDto toModel(Car entity) {
        CarDto carDto = CarDto.builder()
                .id(entity.getId())
                .brand(entity.getBrand())
                .clas(entity.getClas())
                .build();
        Link selfLink = linkTo(methodOn(CarController.class).getCar(Math.toIntExact(carDto.getId()))).withSelfRel();
        carDto.add(selfLink);
        return carDto;
    }

    @Override
    public CollectionModel<CarDto> toCollectionModel(Iterable<? extends Car> entities) {
        CollectionModel<CarDto> carDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(CarController.class).getAllCars()).withSelfRel();
        carDtos.add(selfLink);
        return carDtos;
    }

    public CollectionModel<CarDto> toCollectionModel(Iterable<? extends Car> entities, Link link) {
        CollectionModel<CarDto> carDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        carDtos.add(link);
        return carDtos;
    }
}
