# 一个小Demo

## Dom4j 解析 pdm 文件

### 运行步骤

1. 拷贝一份 pdm 文件到 `/src/main/resources/`目录下，并将文件后缀名改为`xml`
2. 修改示例代码：`/src/main/java/org/shiloh/Dom4jExample.java`
   ```java
    // 将文件名替换成自己的即可
    final URL url = Dom4jExample.class
            .getClassLoader()
            .getResource("your filename");
    ```
3. 运行程序，查看输出内容

----
#### :)