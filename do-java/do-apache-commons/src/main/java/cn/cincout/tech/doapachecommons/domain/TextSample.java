package cn.cincout.tech.doapachecommons.domain;

import org.apache.commons.text.similarity.*;

import java.util.Locale;

/**
 * Created by zhaoyu on 17-9-22.
 *
 * @author zhaoyu
 * @sine 1.8
 */
public class TextSample {
    public static void main(String[] args) {

        String one = "fads zhang dxc";
        String two = "x zhangx xx";
        String three = "zhangzz";

        CosineDistance cosineDistance = new CosineDistance();
        System.out.println(cosineDistance.apply(one, two));
        System.out.println(cosineDistance.apply(one, three));
        System.out.println(cosineDistance.apply(two, three));

        FuzzyScore fuzzyScore = new FuzzyScore(Locale.CHINA);
        System.out.println(fuzzyScore.fuzzyScore(one, two));
        System.out.println(fuzzyScore.fuzzyScore(one, three));

        JaccardDistance jaccardDistance = new JaccardDistance();
        System.out.println("JaccardDistance : " + jaccardDistance.apply(one, two));
        System.out.println("JaccardDistance : " + jaccardDistance.apply(one, three));

        JaroWinklerDistance jaroWinklerDistance = new JaroWinklerDistance();
        System.out.println(jaroWinklerDistance.apply(one, two));
        System.out.println(jaroWinklerDistance.apply(one, three));

        LevenshteinDetailedDistance levenshteinDetailedDistance = new LevenshteinDetailedDistance();
        System.out.println(levenshteinDetailedDistance.apply(one, two));
        System.out.println(levenshteinDetailedDistance.apply(one, three));

        LevenshteinDistance levenshteinDistance = new LevenshteinDistance();
        System.out.println(levenshteinDistance.apply(one, two));
        System.out.println(levenshteinDistance.apply(one, three));

        LongestCommonSubsequenceDistance longestCommonSubsequenceDistance = new LongestCommonSubsequenceDistance();

        System.out.println(longestCommonSubsequenceDistance.apply(one, two));
        System.out.println(longestCommonSubsequenceDistance.apply(one, three));

    }
}
