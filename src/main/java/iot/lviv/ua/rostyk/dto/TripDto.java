/**
 * @author RoSteik (Telegram: @RoSteik)
 * Project: rostyk
 * Package: iot.lviv.ua.rostyk.dto
 * Class: TripDto
 */

package iot.lviv.ua.rostyk.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;


@Builder
@Getter
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Relation(itemRelation = "trip", collectionRelation = "trips")
public class TripDto extends RepresentationModel<TripDto> {
    private Integer id;
    private String startPoint;
    private String endPoint;
    private Long driverId;
    private Long userId;
}
