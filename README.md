# NFA-DFA-transition-tool
a tool automating the transition from NFA to DFA

---

输入格式如下：

假设 NFA 有n个节点，m条边，则输入为 m+1 行。第一行为节点个数n，接下来m行为节点之间的边，若为空转移 $\varepsilon$ 则输入两个节点，空格隔开；若不是空转移，则输入格式为 `node1 char node2` ，其中，char 为输入字符。

规定，NFA中节点编号从 1 开始顺序递增，即node只可能取值为 1,2,3,4...n，并且编号为 1 的节点为起始节点。下面给出示例：

![image-20221115174706484](https://20220923img.oss-cn-hangzhou.aliyuncs.com/markdown/image-20221115174706484.png)

![image-20220919194843297](https://20220923img.oss-cn-hangzhou.aliyuncs.com/markdown/image-20220919194843297.png)

上面两张图分别是 NFA 与确定化后的 DFA，对应输入输出如下：

input：

```java
4
1 a 2
2 a 2
2 b 4
1 4
1 a 3
3 c 3
3 c 4
```

output：

```java
NFA Graph generated!
DFA Graph begin to generate...
DFA Graph generated!
------------------------Nodes-----------------------
Node0: [1, 4]
Node1: [2, 3]
Node2: [2]
Node3: [4]
Node4: [3, 4]
------------------------Edges-----------------------
Node0 a Node1
Node1 a Node2
Node1 b Node3
Node1 c Node4
Node2 a Node2
Node2 b Node3
Node4 c Node4

```

Nodes为确定化后的各个节点，与NFA各节点的对应关系如上面所示。Edges为各个节点在不同输入时的状态迁移。与上图结果一致。

> PS：由于测试数据不足可能有少数bug，可以 issue 告知
