package top.uaian.springbootdemo.service;

/**
 * description:  <br>
 * date: 2020/4/7 10:37 <br>
 * @author: xukainan <br>
 * version: 1.0 <br>
 */
public interface OrderService {
    boolean addOrder(String productID);

    Integer queryStock(String productID);
}
