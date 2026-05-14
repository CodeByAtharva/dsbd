import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Mapper;

public class WMapper extends Mapper<LongWritable,Text,Text,IntWritable> {
	@Override
	public void map(LongWritable key,Text value,Context con) throws IOException,InterruptedException{
		String[] parts=value.toString().split(" ");
		if(parts.length>0) {
			con.write(new Text(parts[0]), new IntWritable(1));
		}
	}
}
