

package cs235.mindreader;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class Factory {

    /**
     * Creates and returns an object that implements the MindReader interface
     *
     * @return A new object that implements the MindReader interface
     */
    public static MindReader createMindReader() {
    	Map map = new HashMap();
    	List list = new LinkedList();
    	MindReader mindReader = new MindReaderImpl (map, list, 0, 0);
	return mindReader;
    }
}


