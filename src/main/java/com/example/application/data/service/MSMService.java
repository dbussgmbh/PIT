package com.example.application.data.service;

import com.example.application.data.entity.ProductHierarchie;
import com.example.application.data.repository.ProductHierarchieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MSMService {
    @Autowired
    private JdbcTemplate template;

    private final ProductHierarchieRepository productHierarchieRepository;

    public MSMService(ProductHierarchieRepository productHierarchieRepository) {


        this.productHierarchieRepository = productHierarchieRepository;
    }

    public List<ProductHierarchie> findAllProducts(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()) {


            return productHierarchieRepository.findAll();
        } else {
            return productHierarchieRepository.search(stringFilter);
        }
    }

    public long countProducts() {
        return productHierarchieRepository.count();
    }

    public void deleteProduct(ProductHierarchie product) {
        productHierarchieRepository.delete(product);
    }

    public void saveProduct(ProductHierarchie product) {
        if (product == null) {

            System.err.println("Product is null. Are you sure you have connected your form to the application?");
            return;
        }

        if (product.getId() == null) {
            product.setId(countProducts() + 1);
        }

        productHierarchieRepository.save(product);
    }

    public String startJob(String jobname ){

        try {
            template.execute("msdb.dbo.sp_start_job @job_name=" + jobname);
        }
        catch (Exception e)
        {
            return e.getMessage();
        }

        return "OK";

    }

}
