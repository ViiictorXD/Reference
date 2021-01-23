
<p align="center">
<h1>Reference</h1>
🚀 A lightweight and fast in-memory reference cache for high data throughput.
</p>

<h2>Quick example</h2>
<h3>Installing with Maven</h3>

```xml
<dependency>
		<groupId>com.github.viiictorxd</groupId>
		<artifactId>reference</artifactId>
		<version>-SNAPSHOT</version>
</dependency>
```

```xml
<repository>
		<id>jitpack.io</id>
		<url>https://jitpack.io</url>
</repository>
```

<h2>Samples</h2>
Basic examples of use and existing methods with their functions.

<h3>Creating cache with Builder</h3>

```java
Cache<String, Integer> cache = new CacheBuilder<String, Integer>()
    .maximumSize(1000)
    .expireAfterWrite(2, TimeUnit.SECONDS)
    .applyLoader(new CacheLoader<String, Integer>() {
        @Override
        public Integer load(String key) {
            return 0;
        }
    })
    .build();
```

<h2>Issues</h2>
Please make sure to read the Issue Reporting Checklist before opening an issue. Issues not conforming to the guidelines may be closed immediately.

<h2>Contributors</h2>
<table>
  <tr>
    <td align="center"><a href="https://github.com/ViiictorXD">
<img src="https://avatars3.githubusercontent.com/u/38568440?v=4" width="100px;" alt=""/><br /><sub><b>ViiictorXD</b></sub></a><br /><a href="https://github.com/ViiictorXD/fast-cache/commits?author=ViiictorXD" title="Code">💻</a></td>
  </tr>
</table>
