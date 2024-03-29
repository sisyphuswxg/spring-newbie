未引入AOP时：
* 代码混乱：越来越多的非业务需求(日志和验证等)加入后,原有的业务方法急剧膨胀.每个方法在处理核心逻辑的同时还必须兼顾其他多个关注点. 
* 代码分散: 以日志需求为例,只是为了满足这个单一需求,就不得不在多个模块（方法）里多次重复相同的日志代码. 如果日志需求发生变化, 必须修改所有模块.

=> 使用动态代理解决上述问题
* 代理设计模式的原理: 使用一个代理将对象包装起来, 然后用该代理对象取代原始对象. 任何对原始对象的调用都要通过代理. 代理对象决定是否以及何时将方法调用转到原始对象上.

=> AOP
* AOP(Aspect-Oriented Programming, 面向切面编程): 是一种新的方法论, 是对传统 OOP(Object-Oriented Programming, 面向对象编程) 的补充.
* AOP 的主要编程对象是切面(aspect), 而切面模块化横切关注点.
* 在应用 AOP 编程时, 仍然需要定义公共功能, 但可以明确的定义这个功能在哪里, 以什么方式应用, 并且不必修改受影响的类. 这样一来横切关注点就被模块化到特殊的对象(切面)里
* AOP 的好处:
    * 每个事物逻辑位于一个位置, 代码不分散, 便于维护和升级
    * 业务模块更简洁, 只包含核心业务代码
* AOP术语：
    * 切面(Aspect):  横切关注点(跨越应用程序多个模块的功能)被模块化的特殊对象
    * 通知(Advice):  切面必须要完成的工作
    * 目标(Target): 被通知的对象
    * 代理(Proxy): 向目标对象应用通知之后创建的对象
    * 连接点（Joinpoint）：程序执行的某个特定位置：如类某个方法调用前、调用后、方法抛出异常后等。连接点由两个信息确定：方法表示的程序执行点；相对点表示的方位。例如 ArithmethicCalculator#add() 方法执行前的连接点，执行点为 ArithmethicCalculator#add()； 方位为该方法执行前的位置
    * 切点（pointcut）：每个类都拥有多个连接点：例如 ArithmethicCalculator 的所有方法实际上都是连接点，即连接点是程序类中客观存在的事务。AOP 通过切点定位到特定的连接点。类比：连接点相当于数据库中的记录，切点相当于查询条件。切点和连接点不是一对一的关系，一个切点匹配多个连接点，切点通过 org.springframework.aop.Pointcut 接口进行描述，它使用类和方法作为连接点的查询条件。


    
