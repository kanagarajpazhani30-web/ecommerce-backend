package com.e_commerce.product_api_service.service;
import com.e_commerce.product_api_service.dto.ProductSearchCriteria;
import com.e_commerce.product_api_service.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class ProductSearchService {
    @Autowired
    private R2dbcEntityTemplate template;
    public Flux<Product> search(ProductSearchCriteria criteria) {

        Criteria c = Criteria.empty();

        if (criteria.getName() != null) {
            c = c.and("name").like("%" + criteria.getName() + "%");
        }

        if (criteria.getCategory() != null) {
            c = c.and("category").is(criteria.getCategory());
        }

        if (criteria.getMinPrice() != null) {
            c = c.and("price").greaterThanOrEquals(criteria.getMinPrice());
        }

        if (criteria.getMaxPrice() != null) {
            c = c.and("price").lessThanOrEquals(criteria.getMaxPrice());
        }

        if (criteria.getSeller() != null) {
            c = c.and("seller").is(criteria.getSeller());
        }

        if (criteria.getMinRating() != null) {
            c = c.and("ratings").greaterThanOrEquals(criteria.getMinRating());
        }

        if (criteria.getInStock() != null && criteria.getInStock()) {
            c = c.and("stock").greaterThan(0);
        }

        Query query = Query.query(c);
        return template.select(Product.class)
                .matching(query)
                .all();


    }


}

