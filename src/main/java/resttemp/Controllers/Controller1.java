package resttemp.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import resttemp.DataElement.Student;

import java.util.Arrays;


@RestController
public class Controller1 {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody String home(){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity <String> entity = new HttpEntity<String>(headers);

        ResponseEntity<String> result=restTemplate
                .exchange("http://localhost:3000", HttpMethod.GET,entity,String.class);
        return result.getBody();
    }

    @GetMapping("/findAll")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody Student[] findAll() throws Exception{
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity <String> entity = new HttpEntity<String>(headers);

        ResponseEntity<Student[]> result=restTemplate
                .exchange("http://localhost:3000/findAll", HttpMethod.GET,entity,Student[].class);

        return result.getBody();
    }

    @GetMapping("/find/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody Student find(@PathVariable int id){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity <String> entity = new HttpEntity<String>(headers);

        ResponseEntity<Student> result=restTemplate
                .exchange("http://localhost:3000/find/"+id, HttpMethod.GET,entity,Student.class);

        return result.getBody();
    }


    @PostMapping("/insert")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody String insert(@RequestBody Student std) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Student> entity = new HttpEntity<Student>(std,headers);

        ResponseEntity<String> result = restTemplate
                .exchange("http://localhost:3000/insert/", HttpMethod.POST, entity, String.class);

        return result.getBody();
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody String update(@PathVariable int id,@RequestBody Student std) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Student> entity = new HttpEntity<Student>(std,headers);

        ResponseEntity<String> result = restTemplate
                .exchange("http://localhost:3000/update/"+id, HttpMethod.PUT, entity, String.class);

        return result.getBody();
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody String insert(@PathVariable int id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        ResponseEntity<String> result = restTemplate
                .exchange("http://localhost:3000/delete/"+id, HttpMethod.DELETE, entity, String.class);

        return result.getBody();
    }
}
