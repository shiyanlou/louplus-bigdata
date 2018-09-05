# 参考答案

根据系统启动的时候的报错信息判断可能出现的问题，并找到对应的配置文件进行错误配置的修复。

1. 将 `/opt/hadoop-2.6.1/etc/hadoop/core-site.xml` 文件中，约第 21 行的 `<name>fs:defaultFS</name>` 修改为 `<name>fs.defaultFS</name>` （注意 fs 与 defaultFS 之间的符号）。
2. 保存上述文件后，在终端中输入以下命令：

```
# 切换到 hadoop 用户，密码也是 hadoop
$ su hadoop

# 执行 format 操作
$ hdfs namenode -format

# 启动
# 首次启动可能需要输入 yes 确认
$ start-dfs.sh
$ start-yarn.sh
```

