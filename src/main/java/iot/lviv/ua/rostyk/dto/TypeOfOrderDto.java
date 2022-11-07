/**
 * @author RoSteik (Telegram: @RoSteik)
 * Project: rostyk
 * Package: iot.lviv.ua.rostyk.dto
 * Class: TypeOfOrderDto
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
@Relation(itemRelation = "typeOfOrder", collectionRelation = "typeOfOrders")
public class TypeOfOrderDto extends RepresentationModel<TypeOfOrderDto> {
    private final Integer id;
    private final String name;
}
