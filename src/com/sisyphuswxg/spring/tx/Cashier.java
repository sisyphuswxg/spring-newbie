package com.sisyphuswxg.spring.tx;

import java.util.List;

public interface Cashier {

    public void checkout(String username, List<String> isbns) throws Exception;
}
