package basic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

public class CollectionTest{
	public static void main(String[] args) {
		List list = new ArrayList();
		// List는 동적 배열, index로 참조한다.
		list.add(33);
		list.add("dfsd");
		
		list.size();
		String str = (String) list.get(1);
		
		List<String> list2 = new ArrayList<String>(); // String 타입으로 지정
		list2.add("Hello");
		String str2 = list2.get(0);
		
		// Map name & value로 관리 
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", "YJ");
		map.put("pw", "root");
		map.get("name");
		String name = (String)map.get("name");
//		map.put("list", list2);
		
		// set: 수학의 set : 중복되지 않은 요소들을 가지고 있는 콜렉션
		Set<String> keySet = map.keySet();
	}

}
