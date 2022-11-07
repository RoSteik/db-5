/**
 * @author RoSteik (Telegram: @RoSteik)
 * Project: rostyk
 * Package: iot.lviv.ua.rostyk.service.impl
 * Class: TypeOfOrderServiceImpl
 */

package iot.lviv.ua.rostyk.service.impl;

import iot.lviv.ua.rostyk.domain.TypeOfOrder;
import iot.lviv.ua.rostyk.exception.TypeOfOrderNotFoundException;
import iot.lviv.ua.rostyk.repository.TypeOfOrderRepository;
import iot.lviv.ua.rostyk.service.TypeOfOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeOfOrderServiceImpl implements TypeOfOrderService {

    @Autowired
    private TypeOfOrderRepository typeOfOrderRepository;

    @Override
    public List<TypeOfOrder> findAll() {
        return typeOfOrderRepository.findAll();
    }

    @Override
    public TypeOfOrder findById(Integer id) {
        return typeOfOrderRepository.findById(id)
                .orElseThrow(() -> new TypeOfOrderNotFoundException(id));
    }

    @Override
    public TypeOfOrder create(TypeOfOrder entity) {
        return typeOfOrderRepository.save(entity);
    }

    @Override
    public void update(Integer id, TypeOfOrder newTypeOfOrder) {
        TypeOfOrder typeOfOrder = typeOfOrderRepository.findById(id)
                .orElseThrow(() -> new TypeOfOrderNotFoundException(id));
        typeOfOrder.setName(newTypeOfOrder.getName());
        typeOfOrderRepository.save(typeOfOrder);
    }

    @Override
    public void delete(Integer id) {
        TypeOfOrder typeOfOrder = typeOfOrderRepository.findById(id)
                .orElseThrow(() -> new TypeOfOrderNotFoundException(id));
        typeOfOrderRepository.delete(typeOfOrder);
    }
}
