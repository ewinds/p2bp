package io.github.ewinds.service.mapper;

import io.github.ewinds.domain.Product;
import io.github.ewinds.service.dto.ProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper( ProductMapper.class );

    ProductDTO productToProductDto(Product product);
}
