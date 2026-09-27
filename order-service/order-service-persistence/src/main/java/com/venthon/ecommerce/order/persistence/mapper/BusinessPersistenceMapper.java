package com.venthon.ecommerce.order.persistence.mapper;


import com.example.ecommerce.domain.valueobject.BusinessId;
import com.example.ecommerce.domain.valueobject.Money;
import com.example.ecommerce.domain.valueobject.ProductId;
import com.venthon.ecommerce.domain.entity.Business;
import com.venthon.ecommerce.domain.entity.Product;
import com.venthon.ecommerce.order.persistence.entity.BusinessEntity;
import com.venthon.ecommerce.persistence.business.exception.BusinessPersistenceException;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface BusinessPersistenceMapper {
    default List<UUID> businessToBusinessProducts(Business business) {
        return business.getProducts().stream()
                .map(product -> product.getId().value())
                .toList();
    }

    default Business businessEntityToBusiness(List<BusinessEntity> businessEntities) {
        BusinessEntity businessEntity = businessEntities.stream()
                .findFirst()
                .orElseThrow(() -> new BusinessPersistenceException("Business could not be found"));

        List<Product> businessProducts = businessEntities.stream()
                .map(entity -> Product.builder()
                        .id(new ProductId(entity.getProductId()))
                        .name(entity.getProductName())
                        .price(new Money(entity.getProductPrice()))
                        .build())
                .toList();

        return Business.builder()
                .id(new BusinessId(businessEntity.getBusinessId()))
                .products(businessProducts)
                .active(businessEntity.getBusinessActive())
                .build();
    }
}
