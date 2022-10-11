数据库脚本执行步骤：
1、先执行druid-jta-atomikos/src/main/resources/db目录下的create_db.sql
2、然后执行druid-jta-atomikos/src/main/resources/db目录下的data-refill-center-credit.sql、data-refill-center-lottery.sql

druid、jta、atomikos整合步骤：
1、application.yml中使用com.alibaba.druid.pool.xa.DruidXADataSource作为数据源，并配置jta xa的日志配置输出
2、xxxDataSourceConfig中使用AtomikosDataSourceBean作为数据源
3、在CreditDataSourceConfig数据源配置中创建名称为xatx的JtaTransactionManager的一个Bean作为全局事务管理器
4、在TxServiceImpl中指定第三步配置的事务管理器，@Transactional(transactionManager = "xatx",rollbackFor = Exception.class)