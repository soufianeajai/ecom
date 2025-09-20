package com.codewithmosh.store.mappers;

import com.codewithmosh.store.dtos.product.ProductDto;
import com.codewithmosh.store.entities.Product;
import com.codewithmosh.store.utils.CategoryMapperHelper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {CategoryMapperHelper.class})
public interface ProductMapper {
    @Mapping(source = "category.id", target = "categoryId")
    ProductDto toDto(Product product);
    @Mapping(source = "categoryId", target = "category")
    Product toEntity(ProductDto productDto);
    @Mapping(source = "categoryId", target = "category")
    @Mapping(target = "id", ignore = true)
    void updateProductFromDto(ProductDto productDto, @MappingTarget Product product);
}



