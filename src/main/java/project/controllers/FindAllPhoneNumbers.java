
package project.controllers;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class FindAllPhoneNumbers {

//    private CityRepository cityRepository;
//
//    @Autowired
//    public FindCities(CityRepository cityRepository) {
//        this.cityRepository = cityRepository;
//    }
//
//    @RequestMapping(value = "/findAllCities")
//    public @ResponseBody String findCities() {
//        List<City> all = this.cityRepository.findAll();
//        StringBuilder sb = new StringBuilder();
//        for (City city : all) {
//            sb.append(city).append(System.lineSeparator());
//        }
//        return sb.toString();
//    }
//    //     http://localhost:8080/findAllCities
//    @RequestMapping(value = "/findCities",params = {"id"})
//    public @ResponseBody String findAllCitiesById(@RequestParam(value = "id") long id) {
//        Optional<City> byId = this.cityRepository.findById(id);
//        if (byId.isPresent()) {
//            return byId.get().toString();
//        }
//        return "City with this id is not present";
//    }
//
//
//    //    http://localhost:8080/findCities?id=1
//
//    @RequestMapping(value = "/findCities",params = {"name"})
//    public @ResponseBody String findAllCitiesByName(@RequestParam(value = "name") String name) {
//        Optional<City> byName = this.cityRepository.findByName(name.trim());
//        if (byName.isPresent()) {
//            return byName.get().toString();
//        }
//        return "City with this id is not present";
//    }
//
//    //    http://localhost:8080/findCities?name= Plovdiv
}

