package cn.cincout.black.hole.donetty.async;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * Created by zhaoyu on 16-9-14.
 *
 * @author zhaoyu
 * @date 17-7-5
 * @sine 1.8
 */
public class CallBack {
    public static void main(String[] args) {
        new Worker().doWork();
    }
}

class Worker {
    public void doWork() {
        Fetcher fetcher = new MyFetcher(new Data(1, 2));
        fetcher.fetchData(new FetcherCallBack() {
            @Override
            public void onData(Data data) {
                System.out.println("data is " + data.toString());
            }

            @Override
            public void onError(Throwable cause) {
                cause.printStackTrace();
            }
        });
    }
}


interface FetcherCallBack {
    void onData(Data data);
    void onError(Throwable cause);
}

interface Fetcher {
    void fetchData(FetcherCallBack fetcherCallBack);
}

class MyFetcher implements Fetcher {
    final Data data;

    public MyFetcher(Data data) {
        this.data = data;
    }

    @Override
    public void fetchData(FetcherCallBack fetcherCallBack) {
        try {
            fetcherCallBack.onData(data);
        } catch (Exception e) {
            fetcherCallBack.onError(e);
        }
    }
}

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
class Data {
    private int n;
    private int m;
}
