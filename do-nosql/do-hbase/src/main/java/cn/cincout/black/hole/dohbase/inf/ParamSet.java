package cn.cincout.black.hole.dohbase.inf;

/**
 * Created by zhaoyu on 17-7-19.
 *
 * @author zhaoyu
 * @date 17-7-19
 * @sine 1.8
 */
public class ParamSet<T, S, E> {
    public T table;
    public S scan;
    public E extractor;

    public ParamSet(T table, S scan, E extractor) {
        this.table = table;
        this.scan = scan;
        this.extractor = extractor;
    }
}
