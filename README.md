# springboot-template
springboot模板工程
### mybatis-type-handler-demo
这个工程展示了mybatis的TypeHandler用法，最经典的场景是，你想将数据库中Date类型转成你想要的时间类型或字符串



### wsc-quartz-demo
场景：开启三个Quartz服务，在某个挂掉情况下，其他两个能平均消费任务


### mybatis-multi-datasource-demo
场景1： 我们一般测试springboot的时候都要自己先启动一个mysql，不过一般来说高手都是用h2数据库来玩，这个案例中有如何启动h2，并将数据源设置为h2的案例

> 浏览器可以启动h2的客户端 http://localhost:8888/h2-database/

场景2： mybatis-plus连接h2自动生成代码


场景3：两个数据源的配置，最经典的场景是某个目录下的mapper使用第一个数据源，另外一个目录下的mapper使用第二个数据源