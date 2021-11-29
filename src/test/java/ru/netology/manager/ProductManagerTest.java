package ru.netology.manager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;

class ProductManagerTest {
    private ProductRepository repository = new ProductRepository();
    private ProductManager prod = new ProductManager(repository);
    private Product book1 = new Book(1, "b1", 100, "a1");
    private Product book2 = new Book(2, "b2", 100, "a1");
    private Product smartPhone1 = new Smartphone(3, "sp1", 1000, "Samsung");
    private Product smartPhone2 = new Smartphone(4, "sp2", 1000, "Samsung");
    private Product book3 = new Book(5, "sameName", 200, "a3");
    private Product smartPhone3 = new Smartphone(6, "sameName", 2000, "Apple");

    @Test
    void shouldSearchBookByName() {
        prod.add(book1);
        Product[] expected = new Product[] {book1};
        Product[] actual = prod.searchBy("b1");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchBookByAuthor() {
        prod.add(book1);
        Product[] expected = new Product[] {book1};
        Product[] actual = prod.searchBy("a1");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchBookBySameAuthor() {
        prod.add(book1);
        prod.add(book2);
        Product[] expected = new Product[] {book1, book2};
        Product[] actual = prod.searchBy("a1");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchSmartphoneByName() {
        prod.add(smartPhone1);
        Product[] expected = new Product[] {smartPhone1};
        Product[] actual = prod.searchBy("sp1");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchSmartphoneByManufacturer() {
        prod.add(smartPhone1);
        Product[] expected = new Product[] {smartPhone1};
        Product[] actual = prod.searchBy("Samsung");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchSmartphoneBySameManufacturer() {
        prod.add(smartPhone1);
        prod.add(smartPhone2);
        Product[] expected = new Product[] {smartPhone1, smartPhone2};
        Product[] actual = prod.searchBy("Samsung");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchSmartphoneAndBookBySameName() {
        prod.add(book3);
        prod.add(smartPhone3);
        Product[] expected = new Product[] {book3, smartPhone3};
        Product[] actual = prod.searchBy("sameName");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchNothing() {
        prod.add(book3);
        prod.add(smartPhone3);
        Product[] expected = new Product[0];
        Product[] actual = prod.searchBy("nonexistentName");
        Assertions.assertArrayEquals(expected, actual);
    }
}