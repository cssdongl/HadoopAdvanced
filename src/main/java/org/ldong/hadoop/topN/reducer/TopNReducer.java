package org.ldong.hadoop.topN.reducer;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author cssdongl@gmail.com
 * @version V1.0
 * @date 2017/3/24 11:02
 */
public class TopNReducer extends Reducer<NullWritable, Text, Text, IntWritable> {

    private int N = 10;

    private SortedMap<Integer, String> finaTopNMap = new TreeMap<>();

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        if (StringUtils.isNotEmpty(context.getConfiguration().get("topn"))) {
            N = Integer.parseInt(context.getConfiguration().get("topn"));
        }
    }

    @Override
    protected void reduce(NullWritable key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        for (Text value : values) {
            String catDetail = value.toString().trim();
            String[] catTokens = catDetail.split(",");
            int weight = Integer.parseInt(catTokens[1]);
            String catName = catTokens[0];
            finaTopNMap.put(weight, catName);
            if (finaTopNMap.size() > N) {
                finaTopNMap.remove(finaTopNMap.firstKey());
            }
        }

        List<Integer> keys = new ArrayList<>(finaTopNMap.keySet());

        for (int i = keys.size() - 1; i >= 0; i--) {
            context.write(new Text(finaTopNMap.get(keys.get(i))), new IntWritable(keys.get(i)));
        }

//        for(Map.Entry<Integer,String> entry : finaTopNMap.entrySet()){
//            context.write(new Text(entry.getValue()),new IntWritable(entry.getKey()));
//        }
    }

}
