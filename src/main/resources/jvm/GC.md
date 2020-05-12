# JVN   

![Garbage Collectors](src/main/resources/jvm/image/garbage.png)

> 打印java 运行时的参数 `java -XX:+PrintCommandLineFlags -version` 
> 
> -XX:InitialHeapSize=268435456   
> -XX:MaxHeapSize=4294967296   
> -XX:+PrintCommandLineFlags   
> -XX:+UseCompressedClassPointers  
> -XX:+UseCompressedOops   
> -XX:+UseParallelGC  

 java1.8 默认 使用的GC收集器为 `ParallelGC `
 
垃圾回收器的发展路线是，是随着内存越来越大的过程而演进，从分代算法到不分代算法  
- serial ：几十兆  
- ParNow ： 是Serial 垃圾收集器的多线程版本 
- Parallel ：几个G  
- CMS ：几十个G    ---承上启下，开始并行回收   
    * 三色标记
- G1 (Garbage First)：上百G   ----逻辑分代，物理不分代  
    * 三色标记 + SATB    
- ZGC : 4T  ---逻辑物理都不分代 
    *  ColoredPointers（颜色指针、着色指针）
- Shenandoah : 4T   ----逻辑物理都不分代   
- Epsilon ：Release 11 ，HotSpot虚拟机提供的GC，是一个处理内存分配但不实现任何内存回收机制的GC，一但可用的java堆用尽，jvm关闭。  


###### HotSpot参数部分  
> 标准 ：  `-` 开头 所有 HotSpot都支持  
> 非标准： `-X` 开头 特定HotSpot支持特定命令  
> 不稳定： `-XX` 开头 下个版本可能取消    

##### GC日志设置 
 
    -Xloggc:/Users/yuanls/Downloads/logs/gc-%t.log   //指定GC日志文件输出位置
    -XX:+UseGCLogFileRotation    //开启滚动日志
    -XX:NumberOfGCLogFiles=5     //设置GC日志的个数
    -XX:GCLogFileSize=10k        //设置日志多大时写入下一个GC日志文件
    -XX:+PrintGCDetails          //输出GC详情
    -XX:+PrintGCDateStamps  
    -XX:+PrintGCCause 
 

## jmap
> `jmap -histo pid  | head 20`  查找有多少对象产生  
> `jmap  -dump:format=b,file=xxx pid` dump heap内存， ！！！ 谨慎操作若堆很大则会卡死，影响服务  





