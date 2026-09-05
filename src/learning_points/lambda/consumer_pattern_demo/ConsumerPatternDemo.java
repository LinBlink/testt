package learning_points.lambda.consumer_pattern_demo;

import java.util.function.Consumer;

public class ConsumerPatternDemo {

    public static void main(String[] args) {


        // 旧写法：手动 new 配置对象，逐项 set
        TrackTotalHits t1 = new TrackTotalHits();
        t1.enabled(true);
        t1.minScore(10.0);
        System.out.println("t1 = " + t1);



        // 新写法：把配置逻辑内联成一段代码(lambda)
        TrackTotalHits t2 = new TrackTotalHits();
        configureTrackTotalHits(t2, track -> {
            track.enabled(true);
            track.minScore(10.0);
        });
        System.out.println("t2 = " + t2);

        // 更像真实库的写法：trackTotalHits(...) 内部自己创建对象
        SearchRequest request = new SearchRequest();
        request.trackTotalHits(track -> track.enabled(true).minScore(10.0));
        System.out.println("request = " + request);
    }

    // 方式一：消费者 + 外部传入对象
    private static void configureTrackTotalHits(TrackTotalHits target, Consumer<TrackTotalHits> fn) {
        fn.accept(target);
    }
}

class TrackTotalHits {
    private boolean enabled;
    private double minScore;

    public TrackTotalHits enabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public TrackTotalHits minScore(double minScore) {
        this.minScore = minScore;
        return this;
    }

    @Override
    public String toString() {
        return "TrackTotalHits(enabled=" + enabled + ", minScore=" + minScore + ")";
    }
}

class SearchRequest {
    private final TrackTotalHits trackTotalHits = new TrackTotalHits();

    // 方式二：对象由方法内部持有，把配置代码传进来执行
    public SearchRequest trackTotalHits(Consumer<TrackTotalHits> fn) {
        fn.accept(this.trackTotalHits);
        return this;
    }

    @Override
    public String toString() {
        return "SearchRequest(" + trackTotalHits + ")";
    }
}