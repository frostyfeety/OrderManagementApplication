package org.example.config;

import org.example.model.Customer;
import org.example.model.Order;
import org.example.model.OrderItem;
import org.example.model.Product;
import org.example.repository.CustomerRepository;
import org.example.repository.OrderRepository;
import org.example.repository.OrderItemRepository;
import org.example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public void run(String... args) throws Exception {
        // Проверяем, есть ли уже данные
        if (customerRepository.count() > 0) {
            System.out.println("Данные уже существуют, пропускаем инициализацию.");
            return;
        }

        System.out.println("Загрузка тестовых данных в MariaDB...");

        // Создание тестовых клиентов
        Customer customer1 = customerRepository.save(new Customer("Алексей Алексеев", "alex@mail.ru", "+71112223334"));
        Customer customer2 = customerRepository.save(new Customer("Егор Штраух", "shtrauh@gmail.com", "+74333222111"));
        Customer customer3 = customerRepository.save(new Customer("Никита Горлов", "gorlov@bk.ru", "+74561237891"));

        // Создание тестовых товаров
        Product product1 = productRepository.save(new Product("Смартфон", "Телефон", new BigDecimal("3000.00")));
        Product product2 = productRepository.save(new Product("Наушники", "Беспроводные наушники", new BigDecimal("150.00")));
        Product product3 = productRepository.save(new Product("Смарт-часы", "Умные часы", new BigDecimal("1000.00")));
        Product product4 = productRepository.save(new Product("Компьютерное кресло", "Игровое компьютерное кресло", new BigDecimal("1500.00")));

        // Создание тестовых заказов с customerId (int)
        Order order1 = new Order(customer1.getId(), LocalDate.of(2025, 10, 11), "COMPLETED");
        Order order2 = new Order(customer2.getId(), LocalDate.of(2025, 10, 12), "PROCESSING");

        order1 = orderRepository.save(order1);
        order2 = orderRepository.save(order2);

        // Создание тестовых позиций заказов
        OrderItem orderItem1 = new OrderItem(order1.getId(), product1.getId(), 1);
        OrderItem orderItem2 = new OrderItem(order1.getId(), product2.getId(), 1);
        OrderItem orderItem3 = new OrderItem(order2.getId(), product3.getId(), 2);
        OrderItem orderItem4 = new OrderItem(order2.getId(), product4.getId(), 1);

        orderItemRepository.save(orderItem1);
        orderItemRepository.save(orderItem2);
        orderItemRepository.save(orderItem3);
        orderItemRepository.save(orderItem4);

        System.out.println("Тестовые данные успешно загружены в MariaDB!");
    }
}