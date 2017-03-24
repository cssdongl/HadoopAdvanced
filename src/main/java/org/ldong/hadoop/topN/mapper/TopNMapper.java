package org.ldong.hadoop.topN.mapper;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author cssdongl@gmail.com
 * @version V1.0
 * @date 2017/3/24 10:48
 * @description do a topN sort first in the mappers
 */
public class TopNMapper extends Mapper<LongWritable, Text, NullWritable, Text> {
    private int TOP_N = 10;

    private SortedMap<Integer, String> topNMap = new TreeMap<>();

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {

        //initialize the N,by default it is 10
        if (StringUtils.isNotEmpty(context.getConfiguration().get("topn"))) {
            TOP_N = Integer.parseInt(context.getConfiguration().get("topn"));
        }
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] parts = value.toString().split(",");

        int weight = Integer.parseInt(parts[1]);
        String catDetail = value.toString();

        topNMap.put(weight, catDetail);

        if (topNMap.size() > TOP_N) {
            topNMap.remove(topNMap.firstKey());
        }
    }

    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {
        for (Map.Entry<Integer, String> entry : topNMap.entrySet()) {
            context.write(NullWritable.get(), new Text(entry.getValue()));
        }
    }
}
