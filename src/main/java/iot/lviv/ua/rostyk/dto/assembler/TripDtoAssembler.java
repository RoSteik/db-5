/**
 * @author RoSteik (Telegram: @RoSteik)
 * Project: rostyk
 * Package: iot.lviv.ua.rostyk.dto.assembler
 * Class: TripDtoAssembler
 */

package iot.lviv.ua.rostyk.dto.assembler;

import iot.lviv.ua.rostyk.controller.TripController;
import iot.lviv.ua.rostyk.domain.Trip;
import iot.lviv.ua.rostyk.dto.TripDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class TripDtoAssembler implements RepresentationModelAssembler<Trip, TripDto> {
    @Override
    public TripDto toModel(Trip entity) {
        TripDto tripDto = TripDto.builder()
                .id(entity.getId())
                .startPoint(entity.getStartPoint())
                .endPoint(entity.getEndPoint())
                .build();
        Link selfLink = linkTo(methodOn(TripController.class).getTrip(Math.toIntExact(tripDto.getId()))).withSelfRel();
        tripDto.add(selfLink);
        return tripDto;
    }

    @Override
    public CollectionModel<TripDto> toCollectionModel(Iterable<? extends Trip> entities) {
        CollectionModel<TripDto> tripDto = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(TripController.class).getAllTrips()).withSelfRel();
        tripDto.add(selfLink);
        return tripDto;
    }
}
