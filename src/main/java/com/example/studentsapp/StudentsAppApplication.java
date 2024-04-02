package com.example.studentsapp;

import com.example.studentsapp.entities.Product;
import com.example.studentsapp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class StudentsAppApplication implements CommandLineRunner {
    private final ProductRepository productRepository;

    @Autowired
    public StudentsAppApplication(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(StudentsAppApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Uncomment below to save products initially
        productRepository.save(new Product(null, "Computer", 6700, 3));
        productRepository.save(new Product(null, "Printer", 5400, 6));
        productRepository.save(new Product(null, "Smart Phone", 1900, 52));

        List<Product> products = productRepository.findAll();
        products.forEach(System.out::println);

        System.out.println("---------------------");
        System.out.println("Test findById : ");
        // Check if value is present before getting
        productRepository.findById(1L).ifPresent(product -> {
            System.out.println("**********");
            System.out.println(product.getId());
            System.out.println(product.getName());
            System.out.println(product.getQuantity());
            System.out.println("**********");
        });

        System.out.println("---------------------");
        System.out.println("Test findByNameContains : ");
        List<Product> productList = productRepository.findByNameContains("t");
        productList.forEach(System.out::println);

        System.out.println("---------------------");
        System.out.println("Test search : ");
        List<Product> productList2 = productRepository.search("%C%");
        productList2.forEach(System.out::println);

        System.out.println("---------------------");
        System.out.println("Test findByPriceGreaterThan : ");
        List<Product> productList3 = productRepository.findByPriceGreaterThan(2000);
        productList3.forEach(System.out::println);
    }
}
