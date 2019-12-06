package com.sisyphuswxg.spring.tx;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

public class SpringTransactionTest {

    private ApplicationContext ctx = null;
    private BookShopDao bookShopDao = null;
    private BookShopService bookShopService;
    private Cashier cashier;

    {
        ctx = new ClassPathXmlApplicationContext("jdbc-tx.xml");
        bookShopDao = ctx.getBean(BookShopDao.class);
        bookShopService = ctx.getBean(BookShopService.class);
        cashier = ctx.getBean(Cashier.class);
    }

    @Test
    public void testTransactionalPropagation() throws Exception {
        cashier.checkout("AA", Arrays.asList("1001", "1002"));
    }

    @Test
    public void testBookShopService() throws Exception {
        bookShopService.purchase("AA", "1001");
    }

    @Test
    public void testBookShopDaoUpdateUserAccount(){
        bookShopDao.updateUserAccount("AA", 190);
    }

    @Test
    public void testBookShopDaoUpdateBookStock() throws Exception {
        bookShopDao.updateBookStock("1001");
    }

    @Test
    public void testBookShopDaoFindPriceByIsbn(){
        System.out.println(bookShopDao.findBookPriceByIsbn("1001"));
    }


}
