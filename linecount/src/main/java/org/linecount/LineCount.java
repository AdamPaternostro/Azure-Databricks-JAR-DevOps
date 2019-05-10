package org.linecount;

import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.Dataset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LineCount {
  public static void main(String[] args) {
    String arg1_StopSparkContext = args[0];
    String arg2_InputFile = args[1];  // "dbfs:/databricks-datasets/README.md"; 
    String arg3_OutputPath = args[2]; // "dbfs:/linecount"; 

    Date date = new Date();
    DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd-hh-mm-ss");
    String outputPath = arg3_OutputPath + "/" + dateFormat.format(date) + "/";
 
    SparkSession sparkSession = SparkSession.builder().appName("Simple Application").getOrCreate();
    Dataset<String> logData = sparkSession.read().textFile(arg2_InputFile).cache();
    long cnt = logData.count();

    String result = "LineCount (" + arg2_InputFile + ") has " + cnt + " lines.";
    System.out.println(result);

    logData.write().text(outputPath);

    // Do not stop the session when running as a Databrick job
    boolean stopSparkContext = Boolean.parseBoolean(arg1_StopSparkContext);
    if (stopSparkContext)
    {
      sparkSession.stop();
    }
  }
}