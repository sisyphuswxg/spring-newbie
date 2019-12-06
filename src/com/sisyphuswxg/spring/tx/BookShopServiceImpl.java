package com.sisyphuswxg.spring.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.CustomEditorConfigurer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("bookShopService")
public class BookShopServiceImpl implements BookShopService {

    @Autowired
    private BookShopDao bookShopDao;

    //添加事务注解:使用propagation属性定义
    //Spring支持的事务传播行为
    // 1. REQUIRED: 如果有事务在运行，当前的方法就在这个事务内运行，否则，就启动一个新的事务，并在自己的事务内运行
    // 2. REQUIRED_NEW： 当前的事务必须启动新事务，并在它自己的事务内运行，如果有事务正在运行，应该将它挂起
    //事务的隔离：
    // - 从理论上来说, 事务应该彼此完全隔离, 以避免并发事务所导致的问题. 然而, 那样会对性能产生极大的影响, 因为事务必须按顺序运行.
    // - 在实际开发中, 为了提升性能, 事务会以较低的隔离级别运行
    // - 事务的隔离级别可以通过隔离事务属性指定
    // 注意：事务的隔离级别需要底层数据库引擎的支持，而不是应用程序或者框架的支持
    // - 在@Transactional的isolation属性中设置隔离级别，最常用的取值是READ_COMMITTED
    // 回滚：默认情况下，Spring的声明式事务对所有的运行时异常进行回滚，也可以通过对应的属性进行设置，通常情况下取默认值即可。
    // 只读：可以使用readOnly指定事务为只读，表示这个事务只读取数据但不更新数据
    // 使用timeout指定强制回滚之前事务可以占用的时间
    @Transactional(Propagation=Propagation.REQUIRED,
        isolation= Isolation.READ_COMMITTED)
    @Override
    public void purchase(String username, String isbn) throws Exception {

        //1. 获取书的单价
        int price = bookShopDao.findBookPriceByIsbn(isbn);

        //2. 更新书的库存
        bookShopDao.updateBookStock(isbn);

        //3. 更新用户余额
        bookShopDao.updateUserAccount(username, price);
    }
}
