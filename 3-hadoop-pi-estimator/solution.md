# 参考答案


在终端中执行以下命令：

```
$ su hadoop
$ cd ~
$ hdfs namenode -format
$ start-dfs.sh
$ start-yarn.sh
$ hadoop jar /opt/hadoop-2.6.1/share/hadoop/mapreduce/hadoop-mapreduce-examples-2.6.1.jar pi 10 100 > /home/hadoop/pi_result
```

