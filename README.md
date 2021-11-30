# SeataProject
a simple sample for seata's AT mode transaction

本实例网关采用gateway，服务配置采用config+bus，服务注册中心采用eureka，负载均衡采用ribbon，服务接口采用openfeign，
服务降级采用hystrix，数据库采用mysql，处理数据采用mybatis，数据库连接池采用Druid。

这里我们会创建三个服务，一个订单服务，一个库存服务，一个账户服务。

- 当用户下单时，会在订单服务中创建一个订单，然后通过远程调用库存服务来扣减下单商品的库存；
- 再通过远程调用账户服务来扣减用户账户里面的余额；
- 最后在订单服务中修改订单状态为已完成。

成功实现服务降级、服务出现运行时错误、库存或账户余量不足等情况时进行全局事务回滚！
![image](https://user-images.githubusercontent.com/86956144/144003691-6d833052-543b-4957-94fa-9580041f75f7.png)
