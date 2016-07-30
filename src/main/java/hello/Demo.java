package hello; /**
 * Created by tgregston on 7/8/16.
 */


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
public class Demo {

    ArrayList<Person> nameList = new ArrayList<Person>();

    @PostConstruct
    public void init() {
        // ...
        nameList.add(new Person("Taylor"));
    }

    @RequestMapping(method=RequestMethod.GET, value="/")
    List<Person> getPersons() {
        return nameList;
    }

    @RequestMapping(method=RequestMethod.PUT, value="/")
    Person putPerson(@RequestParam(value="name") String name) {
        Person p = new Person(name);
        nameList.add(p);
        return p;
    }

    @RequestMapping(method=RequestMethod.DELETE, value="/")
    boolean deletePerson(@RequestParam(value="name") String name) {
        for(Iterator<Person> it = nameList.iterator(); it.hasNext();) {
            if(it.next().getName().equals(name)) {
                it.remove();
                return true;
            }
        }
        return false;
    }


}
