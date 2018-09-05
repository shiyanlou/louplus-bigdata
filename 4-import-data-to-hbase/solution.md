# 参考答案

1. 在终端执行：

 ```
 $ su hadoop
 $ cd ~
 $ wget http://labfile.oss.aliyuncs.com/courses/567/log.csv
 $ hdfs namenode -format
 $ start-dfs.sh
 $ start-yarn.sh
 $ hdfs dfs -mkdir -p /user/hadoop/
 $ hdfs dfs -put /home/hadoop/log.csv /user/hadoop/log.csv
 ```

2. 然后在终端执行：

 ```
 $ start-hbase.sh
 $ hbase shell
 hbase(main):001:0> create 'access_log', 'cf1', 'cf2'
 hbase(main):002:0> exit
 ```

3. 在终端执行：

 ```
 $ hbase org.apache.hadoop.hbase.mapreduce.ImportTsv -Dimporttsv.separator="," -Dimporttsv.columns=HBASE_ROW_KEY,cf1:date,cf1:id,cf2:url,cf2:pre_url,cf2:ip,cf2:country access_log /user/hadoop/log.csv
 ```

