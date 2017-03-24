package org.ldong.hadoop.topN.driver;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.ldong.hadoop.topN.mapper.TopNMapper;
import org.ldong.hadoop.topN.reducer.TopNReducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author cssdongl@gmail.com
 * @version V1.0
 * @date 2017/3/24 10:23
 * @descripion calcuate the topN weight of Cats
 * Sample data:name,weight
 * cat1,1
 * cat2,2
 * cat5,5
 * cat100,100
 * cat50,50
 * cat14,14
 * cat15,15
 * cat22,22
 * cat23,23
 * cat55,55
 * cat200,200
 * cat1000,1000
 * cat2000,2000
 */
public class TopNDriver extends Configured implements Tool {
    private final static Logger logger = LoggerFactory.getLogger(TopNDriver.class);

    @Override
    public int run(String[] args) throws Exception {
        Configuration conf = HBaseConfiguration.create();

        Job topNJob = Job.getInstance(conf, "topN job driver");
        topNJob.setJarByClass(TopNDriver.class);

        FileInputFormat.setInputPaths(topNJob, new Path(args[0]));
        FileOutputFormat.setOutputPath(topNJob, new Path(args[1]));
        topNJob.getConfiguration().set("topn",args[2]);
        FileSystem fs = FileSystem.get(conf);
        if (fs.exists(new Path(args[1]))) {
            fs.delete(new Path(args[1]), true);
        }

        topNJob.setMapOutputKeyClass(NullWritable.class);
        topNJob.setMapOutputValueClass(Text.class);

        topNJob.setMapperClass(TopNMapper.class);
        topNJob.setReducerClass(TopNReducer.class);
        topNJob.setNumReduceTasks(1);

        boolean b = topNJob.waitForCompletion(true);

        return b ? 0 : 1;
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 3) {
            logger.warn("TopN use:input output N");
            System.exit(1);
        }

        int result = ToolRunner.run(new TopNDriver(), args);
        System.exit(result);
    }
}
