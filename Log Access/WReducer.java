import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;
import java.util.*;
public class WReducer extends Reducer<Text,IntWritable,Text,IntWritable>{
	HashMap<String,Integer> map=new HashMap<>();
	@Override
	public void reduce(Text key,Iterable<IntWritable> values,Context con) {
		int sum=0;
		for(IntWritable val:values) sum+=val.get();
		map.put(key.toString(),sum);
	}
	@Override
	public void cleanup(Context con) throws IOException,InterruptedException{
		ArrayList<Map.Entry<String,Integer>> list=new ArrayList<>(map.entrySet());
		Collections.sort(list,Comparator.comparingInt((Map.Entry<String,Integer> x)->x.getValue()).reversed());
		int count=0;
		for(Map.Entry<String, Integer> entry:list) {
			con.write(new Text(entry.getKey()), new IntWritable(entry.getValue()));
			count++;
			if(count>5) break;
		}
		
	}
}
