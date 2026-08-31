# JDK 内置函数式接口

> 配套课程：`learning_points/lambda`（Lambda 表达式学习系列第 5 课）
> 学习前提：函数式接口（只有一个抽象方法、可被 Lambda 实现）

## 一、为什么需要有内置的

"行为参数化"（把一段行为当参数传给方法）的需求，翻来覆去就那么几种固定**形状**：

- 对每个元素做点事（不需要返回值）
- 判断某个值满不满足条件
- 把一个值转换成另一个值
- 生产/提供一个值

Java 将这些形状预先定义好，放在 `java.util.function` 包中，**我们无需再自己声明函数式接口**。

## 二、四大核心接口

| 接口 | 抽象方法 | 形状 | 含义 |
|------|----------|------|------|
| `Consumer<T>` | `void accept(T t)` | `T -> void` | 收一个值，不返回（消费） |
| `Predicate<T>` | `boolean test(T t)` | `T -> boolean` | 收一个值，判断真假 |
| `Function<T,R>` | `R apply(T t)` | `T -> R` | 收一个值，返回一个新值（转换） |
| `Supplier<T>` | `T get()` | `() -> T` | 不收值，产出一个值（生产） |

**记忆口诀：看箭头 `->` 左右两端**

```
Consumer   T      ->  void      吃完不留痕
Predicate  T      ->  boolean   判断真假
Function   T      ->  R         输入变形金刚
Supplier   ()     ->  T         凭空产一个
```

## 三、用法示例

以下为最小完整示例（对应课程 `Course05.java`）：

```java
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Function;
import java.util.function.Supplier;

// 1. Consumer<T>：遍历时发射给消费函数
static void each(List<Integer> nums, Consumer<Integer> c) {
    for (int n : nums) c.accept(n);
}
each(nums, n -> System.out.print("[" + n + "] "));

// 2. Predicate<T>：过滤/判断（我们自写的 Filter 就是它）
static void printFiltered(List<Integer> nums, Predicate<Integer> p) {
    for (int n : nums) if (p.test(n)) System.out.print(n + " ");
}
printFiltered(nums, n -> n % 2 == 0);

// 3. Function<T,R>：映射，输入输出类型可以不同
static List<String> map(List<Integer> nums, Function<Integer, String> f) {
    List<String> result = new ArrayList<>();
    for (int n : nums) result.add(f.apply(n));
    return result;
}
map(nums, n -> "v" + n);   // -> [v1, v2, v3, v4, v5]

// 4. Supplier<T>：需要时才调用（惰性求值/默认值）
static int firstOr(List<Integer> nums, Supplier<Integer> s) {
    return nums.isEmpty() ? s.get() : nums.get(0);
}
firstOr(List.of(), () -> 999);   // -> 999
```

## 四、常用变体接口

### 双参版本

| 接口 | 抽象方法 | 形状 |
|------|----------|------|
| `BiConsumer<T,U>` | `void accept(T t, U u)` | `(T,U) -> void` |
| `BiPredicate<T,U>` | `boolean test(T t, U u)` | `(T,U) -> boolean` |
| `BiFunction<T,U,R>` | `R apply(T t, U u)` | `(T,U) -> R` |

### 同类型特化（省一个泛型参数）

| 接口 | 抽象方法 | 形状 | 说明 |
|------|----------|------|------|
| `UnaryOperator<T>` | `T apply(T t)` | `T -> T` | 输入输出同类型，如取反、放大 |
| `BinaryOperator<T>` | `T apply(T t, T u)` | `(T,T) -> T` | 两个同类合成一个，如求和 |

### 原始类型特化（避免装箱，性能更好）

常见：`IntConsumer`、`IntPredicate`、`IntFunction`、`IntSupplier`、`LongFunction`、`IntUnaryOperator` 等。
Stream API 内部大量使用这些，例如 `IntStream` 的 `forEach` 接收的是 `IntConsumer` 而非 `Consumer<Integer>`。

## 五、接口自带的组合方法（default 方法）

函数式接口除了抽象方法，还带一些"装配"用的默认方法，让行为可以组合：

| 接口 | 方法 | 作用 |
|------|------|------|
| `Consumer<T>` | `andThen(Consumer)` | 执行完自己再执行下一个 |
| `Function<T,R>` | `andThen(Function)` | 先应用自己，结果再交给下一个 |
| `Function<T,R>` | `compose(Function)` | 先执行传入的，再把结果交给自己 |
| `Predicate<T>` | `and(Predicate)` / `or(Predicate)` / `negate()` | 逻辑与 / 或 / 非 |

示例：

```java
Function<String, String> trimAndUpper = String::trim
        .andThen(String::toUpperCase);

Predicate<Integer> between = n -> n >= 0;
Predicate<Integer> big     = n -> n > 10;
Predicate<Integer> pick    = between.and(big);   // n >= 0 且 n > 10
```

## 六、回到 `trackTotalHits`

```java
request.trackTotalHits(track -> track.enabled(true));
```

`trackTotalHits` 的参数类型本质上就是 **`Consumer<TrackTotalHits>`**：

- 形状 = "收一个 `TrackTotalHits`，不返回"
- `track` 是 `accept(TrackTotalHits)` 的入参
- 方法体 `track.enabled(true)` 是对这个对象做的配置
- 执行时机：由 `trackTotalHits` 内部调用 `consumer.accept(obj)` 时触发

这就是 **Builder + Consumer** 配置器模式（详见第 6 课）。

## 七、如何判断方法期望哪种函数式接口

看到方法签名里有一个函数式接口参数时，不急着翻文档，按三步推断：

1. **参数几个**：1 个 → 上述单参接口；2 个 → 双参版本；0 个 → `Supplier`。
2. **返回 void 还是值**：void → `Consumer`；boolean → `Predicate`；任意值 → `Function`。
3. **`this` 是否也参与**：比如 `forEach` 让元素自己调用方法，属于"拿到值就消费"，所以是 `Consumer`。

## 八、练习

1. 用 `Consumer` 写一行代码打印列表中每个数的两倍。
2. 用 `Predicate` 写一个"能被 3 整除"的判断，并在 `printFiltered` 中使用。
3. 用 `Function` 把 `List<Integer>` 转成字符串 `"1", "2", ...`。
4. 在仓库里搜索 `Stream` / `Optional` 中用到 `Supplier` 的方法（如 `orElseGet(Supplier)`），体会惰性求值的好处。